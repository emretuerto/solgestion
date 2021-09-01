package es.emretuerto.solgestion.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import es.emretuerto.solgestion.auxiliares.PageRender;
import es.emretuerto.solgestion.modelo.Lampara;
import es.emretuerto.solgestion.servicios.LamparaServicioInterface;

@Controller
@RequestMapping("/lampara")
public class LamparaController {

	final Logger LOG = Logger.getLogger("LamparaController.class");

	@Autowired
	LamparaServicioInterface lamparaServicio;

	@GetMapping("/alta")
	public String alta(Model model) {
		LOG.info("Método para el formulario de alta lampara");
		Lampara lampara = new Lampara();
		model.addAttribute("lampara", lampara);

		return "lampara/alta";
	}

	@PostMapping("/alta")
	public String procesarAlta(@Valid Lampara lampara, BindingResult result, Model model, SessionStatus status) {

		LOG.info("Entramos en el controlador formulario POST alta de lámparas ");

		// tipoClienteValidator.validate(tipoCliente, result);
		if (result.hasErrors()) {

			LOG.info(result.toString());
			LOG.info(model.toString());
			return "lampara/alta";

		}
		lamparaServicio.insertar(lampara);
		status.setComplete();
		return "redirect:/lampara/listado";
	}
	
	@GetMapping("/listado")
	public String listarLamparas(@RequestParam(name="page", defaultValue = "0") int page,Model model, SessionStatus status) {


		
		
		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Lampara> lamparas = lamparaServicio.listadoLamparas(pageRequest);
		PageRender<Lampara> pageRender= new PageRender<>("/lampara/listado", lamparas); 
		
		List<Lampara> listadoLampara = lamparaServicio.listadoLamparas();
		LOG.info("Las lámparas sin paginar son " + listadoLampara.toString());
		
		
		LOG.info("El listado de lámparas es: " + pageRender.toString());
		
		model.addAttribute("listadoLamparas", lamparas);
		model.addAttribute("page", pageRender);

		LOG.info(lamparas.toString());
		LOG.info(pageRender.toString());
		
		status.setComplete();
		return "/lampara/listado";

	}

}
