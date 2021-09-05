package es.emretuerto.solgestion.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.emretuerto.solgestion.modelo.Sesion;

@Controller
@SessionAttributes({ "sesion", "cliente", "maquina" })
@RequestMapping("/prueba")
public class pruebaController {







	final Logger LOG = Logger.getLogger("SesionController.class");



	

	@GetMapping("/alta")
	public String altaInicialSesion(Model model) {

	Sesion sesion = new Sesion();
	model.addAttribute(sesion);
	LOG.info("El contenido del model es: " + model.toString());
	


		return "pruebas/altafase3_33";
	}

	@PostMapping("/alta")
	public String viene(Model model) {

		LOG.info("El contenido del model es: " + model.toString());
	
	
		return "sesion/altafase3_33";
	}


}
