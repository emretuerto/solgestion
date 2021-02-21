package es.emretuerto.solgestion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.emretuerto.solgestion.auxiliares.PageRender;
import es.emretuerto.solgestion.modelo.Fototipo;
import es.emretuerto.solgestion.modelo.TipoCliente;
import es.emretuerto.solgestion.servicios.FototipoServicioInterface;
import es.emretuerto.solgestion.servicios.TipoClienteServicioInterface;
import es.emretuerto.solgestion.validaciones.TipoClienteValidator;

@Controller
@SessionAttributes("tipoCliente")
@RequestMapping("/tipoclientes")
public class TipoClienteController {

	final Logger LOG = Logger.getLogger("TipoClienteController.class");

	@Autowired
	TipoClienteServicioInterface tipoClienteServicio;

	@Autowired
	FototipoServicioInterface fototipoServicio;

	@Autowired
	TipoClienteValidator tipoClienteValidator;

	@GetMapping("/listado")
	public String listarTipoClientes(@RequestParam(name="page", defaultValue = "0") int page,Model model) {

//List<TipoCliente> listadoTipoClientes = new ArrayList<>();
//listadoTipoClientes = tipoClienteServicio.listarTiposClienteActivos(true);
		
		
		List<Fototipo> listadoFototipos = new ArrayList<>();
		listadoFototipos = fototipoServicio.listadoFototipos();
		model.addAttribute("listadoFototipos", listadoFototipos);
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<TipoCliente> clientes = tipoClienteServicio.listadoActivos(pageRequest);
		PageRender<TipoCliente> pageRender= new PageRender<>("/tipoclientes/listado", clientes); 
		
		model.addAttribute("listadoTipoClientes", clientes);
		model.addAttribute("page", pageRender);

		LOG.info(clientes.toString());
		LOG.info(pageRender.toString());
		
		return "/tipoclientes/listado";

	}

	@GetMapping("/alta")
	public String alta(Model model) {
		LOG.info("Método para el formulario de alta de tipo cliente");
		TipoCliente tipoCiente = new TipoCliente();
		model.addAttribute("tipoCliente", tipoCiente);

		return "tipoclientes/alta";
	}

	@PostMapping("/alta")
	public String procesarAlta(@Valid TipoCliente tipoCliente, BindingResult result, Model model,
			SessionStatus status) {

		LOG.info("Entramos en el controlador formulario alta");

		tipoClienteValidator.validate(tipoCliente, result);
		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "tipoclientes/alta";

		}
		tipoCliente.setActivo(true);
		tipoClienteServicio.insertar(tipoCliente);
		status.setComplete();
		return "redirect:/tipoclientes/listado";
	}

	@GetMapping("ver/{id}")
	public String editarTipoCliente(@PathVariable int id, Model model) {
		TipoCliente tipocliente = tipoClienteServicio.findById(id);
		model.addAttribute("tipoCliente", tipocliente);
		return "tipoclientes/editar";
	}

	@PostMapping("/actualizar")
	public String modificarTipoCliente(TipoCliente tipoCliente, BindingResult result, Model model,
			SessionStatus status) {

	
		if (result.hasErrors()) {
			model.addAttribute("tipoCliente", tipoCliente);
			System.out.println(tipoCliente);
			LOG.info(result.toString());
			return "tipoclientes/editar";

		}
		tipoClienteServicio.actualizar(tipoCliente);
		status.setComplete();

		return "redirect:/tipoclientes/listado";
	}

	@GetMapping("eliminar/{id}")
	public String eliminarTipoCliente(@PathVariable int id, Model model) {
		TipoCliente tipoCliente = tipoClienteServicio.findById(id);
		tipoClienteServicio.desactivaTipoCiente(tipoCliente);
		return "redirect:/tipoclientes/listado";
	}
	
	
}
