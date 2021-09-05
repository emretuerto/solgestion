package es.emretuerto.solgestion.controllers;

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
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;
import es.emretuerto.solgestion.servicios.impl.BonoServicioImpl;
import es.emretuerto.solgestion.validaciones.BonoValidator;

@Controller
@SessionAttributes({ "bono", "cliente" })
@RequestMapping("/bono")
public class BonoController {

	final Logger LOG = Logger.getLogger("BonoController.class");

	@Autowired
	BonoServicioImpl bonoServicio;

	@Autowired
	ClienteServicioInterface clienteServicio;

	@Autowired
	private BonoValidator validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		if (binder.getObjectName().equals("bono")) {
			binder.addValidators(validador);
		}
	}

	@GetMapping("/listado")
	public String listarBonos(@RequestParam(name = "page", defaultValue = "0") int page, Model model, SessionStatus status) {

		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Bono> bonos = bonoServicio.listado(pageRequest);
		PageRender<Bono> pageRender = new PageRender<>("/bono/listado", bonos);

		model.addAttribute("listadoBono", bonos);
		model.addAttribute("page", pageRender);

		LOG.info(bonos.toString());
		LOG.info(pageRender.toString());

		status.setComplete();
		return "/bono/listado";

	}

	@GetMapping("/alta")
	public String alta(Model model) {
		LOG.info("Método para el formulario de alta de bono");
		Bono bono = new Bono();
		model.addAttribute("bono", bono);

		return "bono/alta";
	}

	@PostMapping("/alta")
	public String procesarAlta(@Valid Bono bono, BindingResult result, Model model, SessionStatus status) {

		LOG.info("Entramos en el controlador formulario alta");
		LOG.info("LOS ERRORES QUE LLEGAN SON: " + result.toString());
		LOG.info(bono.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			LOG.info("La longitud del campo codigo barras es: " + bono.getCodigoBarras().length());
			bono.setCodigoBarras(null);
			return "bono/alta";
		}

		if (bono.getCodigoBarras().length() == 0) {
			bono.setCodigoBarras(null);
		}
		bonoServicio.insertar(bono);
		status.setComplete();
		return "redirect:/bono/listado";
	}

	@GetMapping("ver/{id}")
	public String editarBono(@PathVariable int id, Model model) {

		Optional<Bono> bonoRecibido = bonoServicio.findById(id);
		Bono bono = bonoRecibido.get();
		List<Cliente> clientesBono = bonoServicio.listadoClientesBono(bono);
		LOG.info("LOS CLIENTES DEL BONO SON: " + clientesBono.toString());
		bono.setClientesBono(clientesBono);
		LOG.info(bono.toString());
		model.addAttribute("bono", bono);
		return "bono/editar";
	}

	@PostMapping("/actualizar")
	public String modificarBono(Bono bono, BindingResult result, Model model, SessionStatus status) {

		LOG.info("VAMOS A ACTUALIZAR EL BONO, LOS ERRORES SON" + result.toString());
		LOG.info("VAMOS A ACTUALIZAR EL BONO, LOS ERRORES SON" + model.toString());
		if (result.hasErrors()) {
			model.addAttribute("bono", bono);
			LOG.info(result.toString());
			return "bono/editar";

		}
		bonoServicio.actualizar(bono);
		status.setComplete();
		bono = null;
		return "redirect:/bono/listado";
	}

	@GetMapping("eliminar/{id}")
	public String eliminarBono(@PathVariable int id, Model model) {
		Optional<Bono> bonoOptional = bonoServicio.findById(id);
		Bono bono = bonoOptional.get();
		bonoServicio.borrar(bono);
		return "redirect:/bono/listado";
	}

	@GetMapping("recarga")
	public String cargarBonoId(Model model) {
		Bono bono = new Bono();
		model.addAttribute("bono", bono);
		return "bono/cargaid";
	}

	@PostMapping("cargaid")
	public String cargarBono(Bono bono, Model model) {

		Bono bonoCarga = new Bono();
		LOG.info("EL BONO RECIBIDO ES " + bono);
		try {
			if (bono.getIdentificadorBono() != null) {

				bonoCarga = bonoServicio.findByIdentificadorBono(bono.getIdentificadorBono());
				return "redirect:/bono/recarga/" + bonoCarga.getId();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (bono.getCodigoBarras() != null && bono.getCodigoBarras().length() != 0) {

				bonoCarga = bonoServicio.findByCodigoBarras(bono.getCodigoBarras());
				return "redirect:/bono/recarga/" + bonoCarga.getId();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/bono/recarga/";
	}

	@GetMapping("recarga/{id}")
	public String recargaBono(@PathVariable int id, Model model) {
		Optional<Bono> bonoOptional = bonoServicio.findById(id);
		Bono bono = bonoOptional.get();
		model.addAttribute("bono", bono);
		return "/bono/recargabono";
	}

	@PostMapping("recargabono")
	public String recargarBono(Bono bono, SessionStatus status) {

		LOG.info("HAS LLEGADO EL BONO" + bono.toString());
		if (bono.getCodigoBarras().length() == 0) {
			bono.setCodigoBarras(null);
		}
		bonoServicio.recargaMinutos(bono, bono.getSesiones());
		status.setComplete();

		return "redirect:/bono/listado";
	}

	@GetMapping("asocia")
	public String asociarBono(Bono bono, Model model) {

		model.addAttribute("bono", bono);
		return "bono/asociaid";
	}

	@GetMapping("asocia/{id}")
	public String asociaBono(@PathVariable int id, Model model) {
		Optional<Bono> bonoOptional = bonoServicio.findById(id);
		AuxiliarDatos auxiliarDatos = new AuxiliarDatos();
		Bono bono = bonoOptional.get();
		model.addAttribute("bono", bono);
		model.addAttribute("auxiliarDatos", auxiliarDatos);
		LOG.info("El contenido del model BONOCLIENTE ES: " + model.toString());
		return "/bono/asociabono";
	}

	@PostMapping("asociaid")
	public String asociaIdBono(Bono bono, Model model) {

		Bono bonoCarga = new Bono();
		LOG.info("EL BONO RECIBIDO ES " + bono);
		try {
			if (bono.getIdentificadorBono() != null) {

				bonoCarga = bonoServicio.findByIdentificadorBono(bono.getIdentificadorBono());
				return "redirect:/bono/asocia/" + bonoCarga.getId();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (bono.getCodigoBarras() != null) {

				bonoCarga = bonoServicio.findByCodigoBarras(bono.getCodigoBarras());
				return "redirect:/bono/asocia/" + bonoCarga.getId();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/bono/asocia/";
	}

	@PostMapping("clienteasocia")
	public String bonoAsociaCliente(@Valid AuxiliarDatos auxiliarDatos, BindingResult result, Model model) {

		Bono bono = (Bono) model.getAttribute("bono");
		Cliente cliente;
		LOG.info("hARÁ LO QUE TIENEN QUE HACER???");
		LOG.info(model.toString());
		LOG.info(result.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "bono/asocia";
		}
		try {

			LOG.info("Entra a buscar el cliente por código de barras");
			Optional<Cliente> clienteOptional = clienteServicio.buscarPorCodigoBarras(auxiliarDatos.getDato1());
			
			cliente = clienteOptional.get();
			model.addAttribute("cliente", cliente);
			LOG.info(cliente.toString());
			model.addAttribute("cliente", cliente);
		} catch (Exception e) {
			LOG.info("No ha encontrado en cliente");
			cliente = new Cliente();
			cliente.setCodigoBarras(auxiliarDatos.getDato1());
			model.addAttribute("cliente", cliente);
			model.addAttribute("Mensaje", "El cliente no existe");
			return "redirect:/bono/asocia/" + bono.getId();

		}

		model.addAttribute("cliente", cliente);
		LOG.info("EL MODEL JUSTO ANTES DE ASOCIAR BONO-CLIENTE ES: " + model.toString());
		return "redirect:/bono/asociar";
	}

	@GetMapping("asociar")
	public String bonoAsociaClienteFinal(Model model, SessionStatus status) {

		LOG.info("El model cuando vamos a asocial el bono al cliente: " + model.toString());

		Bono bono = (Bono) model.getAttribute("bono");
		Cliente cliente = (Cliente) model.getAttribute("cliente");
		LOG.info("El Bono es: " + bono.toString());
		LOG.info("Elcliente es: " + cliente.toString());
		try {
			if (cliente.getBono().getId() != bono.getId()) {
				bonoServicio.asocia(bono, cliente);
			}
		} catch (Exception e) {
			bonoServicio.asocia(bono, cliente);
		}
		status.setComplete();
		return "redirect:/bono/listado";
	}

	@GetMapping("ver/desasociar/{id}")
	public String desasociaBono(@PathVariable int id, Model model, SessionStatus status) {

		LOG.info("Vamos a desasociar, el model es " + model.toString());
		Bono bono = (Bono) model.getAttribute("bono");
		Cliente cliente = clienteServicio.findById(id);
		clienteServicio.desasociaBono(cliente);
		status.setComplete();

		return "redirect:/bono/ver/" + bono.getId();
	}

}
