package es.emretuerto.solgestion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.emretuerto.solgestion.auxiliares.AuxiliarDatos;
import es.emretuerto.solgestion.auxiliares.PageRender;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Fototipo;
import es.emretuerto.solgestion.modelo.TipoCliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;
import es.emretuerto.solgestion.servicios.FototipoServicioInterface;
import es.emretuerto.solgestion.servicios.TipoClienteServicioInterface;
import es.emretuerto.solgestion.validaciones.ClienteValidator;

@Controller
@SessionAttributes({ "cliente" })
@RequestMapping("/cliente")
public class ClienteController {

	final Logger LOG = Logger.getLogger("ClienteController.class");

	@Autowired
	ClienteServicioInterface clienteServicio;

	@Autowired
	TipoClienteServicioInterface tipoClienteServicio;

	@Autowired
	FototipoServicioInterface fototipoServicio;

	@Autowired
	private ClienteValidator validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		if (binder.getObjectName().equals("cliente")) {
			binder.addValidators(validador);
		}
	}

	@GetMapping("/altainicial/{codigoBarras}")
	public String alta(@PathVariable String codigoBarras, Model model) {

		Cliente cliente = new Cliente();
		cliente.setCodigoBarras(codigoBarras);
		List<Fototipo> listadoFototipos = new ArrayList<>();
		listadoFototipos = fototipoServicio.listadoFototipos();
		model.addAttribute("listadoFototipos", listadoFototipos);

		List<TipoCliente> listadoTipoClientes = new ArrayList<>();
		listadoTipoClientes = tipoClienteServicio.listarTiposClienteActivos(true);
		model.addAttribute("listadoTipoClientes", listadoTipoClientes);
		model.addAttribute("cliente", cliente);
		LOG.info("Salimos del controlador de alta inicial GET");
		LOG.info("El cliente es: " + cliente.toString());

		return "cliente/altainicial";
	}

	@PostMapping("/altainicial")
	public String procesarAlta(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {

		LOG.info("Entramos en el controlador formulario alta");

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "cliente/altainicial";
		}

		LOG.info(model.toString());
		cliente.setActivo(true);
		clienteServicio.insertar(cliente);
		status.setComplete();
		return "redirect:/cliente/listado";
	}

	@GetMapping("/alta")
	public String altaInicial(AuxiliarDatos auxiliarDatos, Model model) {

		LOG.info("Parece que ha llegado bien");
		model.addAttribute("auxiliarDatos", auxiliarDatos);
		LOG.info(auxiliarDatos.toString());
		LOG.info(model.toString());

		return "cliente/alta";
	}

	@PostMapping("/alta")
	public String altaInicialEnvio(@Valid AuxiliarDatos auxiliarDatos, BindingResult result, Model model,
			SessionStatus status) {

		Optional<Cliente> cliente;
		LOG.info("hARÁ LO QUE TIENEN QUE HACER???");
		LOG.info(model.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "cliente/alta";
		}

		cliente = clienteServicio.buscarPorCodigoBarras(auxiliarDatos.getDato1());

		if (cliente.isPresent()) {
			Cliente cliente2 = new Cliente();
			cliente2 = cliente.get();
			model.addAttribute("cliente", cliente2);

		} else if (cliente.isEmpty()) {
			LOG.info("No ha encontrado en cliente");
			Cliente cliente2 = new Cliente();
			cliente2.setCodigoBarras(auxiliarDatos.getDato1());
			model.addAttribute("cliente", cliente2);
			return "redirect:/cliente/altainicial/" + auxiliarDatos.getDato1();

		}

		status.setComplete();
		return "cliente/actualizar";
	}

	@GetMapping("/listado")
	public String listarClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			SessionStatus status) {

		List<Cliente> listadoClientes = new ArrayList<>();
		listadoClientes = clienteServicio.listadoOrdenadoPorNombre();
		// listadoClientes = clienteServicio.listado();
		model.addAttribute("listadoClientes", listadoClientes);

		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Cliente> clientes = clienteServicio.listadoOrdenadoPorNombreClientes(pageRequest);
		// Page<Cliente> clientes = clienteServicio.listadoClientes(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/cliente/listado", clientes);

		model.addAttribute("listadoClientes", clientes);
		model.addAttribute("page", pageRender);
		status.setComplete();
		return "/cliente/listado";

	}

	@GetMapping("ver/{id}")
	public String editarCliente(@PathVariable int id, Model model) {

		LOG.info("Busqueda de cliente por el id");
		Cliente cliente = clienteServicio.findById(id);
		LOG.info(cliente.toString());
		LOG.info("TIENE SESIONES ESTE CLIENTE?" + cliente.getSesionesCliente().toString());
		model.addAttribute("cliente", cliente);
		return "cliente/actualizar";
	}

	@GetMapping("eliminar/{id}")
	public String eliminarTipoCliente(@PathVariable int id, Model model) {
		Cliente cliente = clienteServicio.findById(id);
		clienteServicio.borrar(cliente);
		return "redirect:/cliente/listado";
	}

	@PostMapping("/actualizar")
	public String actualizar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {

		LOG.info("Entramos en el controlador para actualizar cliente");

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "cliente/actualizar";
		}

		LOG.info(model.toString());
		cliente.setActivo(true);
		clienteServicio.insertar(cliente);
		status.setComplete();
		return "redirect:/cliente/listado";
	}

	@GetMapping("ver/recargar/{id}")
	public String RecargarBonoCliente(@PathVariable int id, Model model) {

		return "forward:/bono/recarga/" + id;
	}

	@GetMapping("/buscar")
	public String buscarCliente(Model model) {

		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		LOG.info("ANTES DE BUSCAR EL MODEL ES " + model.toString());
		return "/cliente/buscar";
	}

	@PostMapping("/buscar")
	public String buscarClienteBBDD(Cliente cliente, BindingResult result, Model model) {

		LOG.info("EL MODEL ANTES DE LA SEGUNDA PARTE DE LA BUSQUEDA ES" + model.toString());
		LOG.info("EL MODEL ANTES DE LA SEGUNDA PARTE cliente - " + cliente.toString());
		LOG.info("LOS ERRORES SON" + result.toString());

		/*
		 * if (result.hasErrors()) {
		 * 
		 * LOG.info(result.toString()); return "cliente/buscar"; }
		 */

		Optional<Cliente> clienteOptional;

		try {

			clienteOptional = clienteServicio.buscarPorCodigoBarras(cliente.getCodigoBarras());
			cliente = clienteOptional.get();

		} catch (Exception e) {
			try {

				clienteOptional = clienteServicio.buscaClienteNif(cliente.getNif());
				cliente = clienteOptional.get();
			} catch (Exception ex) {
				return "/cliente/altainicial2";
			}
		}
		model.addAttribute("cliente", cliente);
		return "/cliente/actualizar";
	}

}
