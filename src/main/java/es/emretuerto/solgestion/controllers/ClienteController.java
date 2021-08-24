package es.emretuerto.solgestion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Fototipo;
import es.emretuerto.solgestion.modelo.TipoCliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;
import es.emretuerto.solgestion.servicios.FototipoServicioInterface;
import es.emretuerto.solgestion.servicios.TipoClienteServicioInterface;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	final Logger LOG = Logger.getLogger("ClienteController.class");
	
	@Autowired
	ClienteServicioInterface clienteServicio;
	
	@Autowired
	TipoClienteServicioInterface tipoClienteServicio;

	@Autowired
	FototipoServicioInterface fototipoServicio;
	
	
	@GetMapping("/alta")
	public String alta(Model model) {
		LOG.info("Método para el formulario de alta de cliente");
		Cliente cliente= new Cliente();
		int idtipo=0,idfototipo=0;
		List<Fototipo> listadoFototipos = new ArrayList<>();
		listadoFototipos = fototipoServicio.listadoFototipos();
		model.addAttribute("listadoFototipos", listadoFototipos);
		
		List<TipoCliente> listadoTipoClientes = new ArrayList<>();
		listadoTipoClientes = tipoClienteServicio.listarTiposClienteActivos(true);
		model.addAttribute("listadoTipoClientes", listadoTipoClientes);
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("idtipo", idtipo);
		model.addAttribute("idfototipo", idfototipo);

		return "cliente/alta";
	}
	
	
	@PostMapping("/alta")
	public String procesarAlta( @RequestParam(value = "idtipo", required = false) Integer idtipo, @Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {

		LOG.info("Entramos en el controlador formulario alta");

		/*
		 * bono.Validator.validate(tipoCliente, result); if (result.hasErrors()) {
		 * 
		 * LOG.info(result.toString()); return "bono/alta";
		 * 
		 * }
		 */
		
		LOG.info(model.toString());
		
		clienteServicio.insertar(cliente);
		status.setComplete();
		return "redirect:/cliente/alta";
	}
	
}
