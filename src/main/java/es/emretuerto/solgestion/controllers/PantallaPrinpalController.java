package es.emretuerto.solgestion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PantallaPrinpalController {

	@RequestMapping({"/","/menu","/principal"})
	public String pantallaPrincipal() {
		
		return "principal/principal";
		
	}
	
	
}
