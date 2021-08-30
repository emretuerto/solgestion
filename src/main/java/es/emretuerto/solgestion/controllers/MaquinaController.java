package es.emretuerto.solgestion.controllers;

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

import es.emretuerto.solgestion.auxiliares.AuxiliarDatos2;
import es.emretuerto.solgestion.auxiliares.PageRender;
import es.emretuerto.solgestion.modelo.Lampara;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.servicios.MaquinaServicioInterface;

@Controller
@SessionAttributes({ "maquina", "datos" })
@RequestMapping("/maquina")
public class MaquinaController {
	
	final Logger LOG = Logger.getLogger("MaquinaController.class");
	
	
	@Autowired
	MaquinaServicioInterface maquinaServicio;
	

	@GetMapping("/alta")
	public String altaMaquina(Model model) {

		Maquina maquina = new Maquina();
		model.addAttribute("maquina", maquina);
		LOG.info("EL CONTENIDO DEL MODEL EN EL METODO GET DE ALTA ES: " + model.toString());
		LOG.info("Y EL DE LA MAQUINA: " + maquina.toString());

		return "maquina/alta";
	}

	@PostMapping("/alta")
	public String altaInicialEnvio(@Valid Maquina maquina, BindingResult result, Model model,
			SessionStatus status) {



		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "maquina/alta";
		}
		
		
		maquinaServicio.insertar(maquina);
		status.setComplete();
		return "redirect:/maquina/listado";
	}
	
	@GetMapping("/listado")
	public String listarMaquinas(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Maquina> maquinas = maquinaServicio.listado(pageRequest);
		PageRender<Maquina> pageRender = new PageRender<>("/maquina/listado", maquinas);

		model.addAttribute("listadoMaquinas", maquinas);
		model.addAttribute("page", pageRender);

		LOG.info("DATOS DEL LIST DE MAQUINAS" + maquinas.toString());
		LOG.info("DATOS DEL LIST DE MAQUINAS" + pageRequest.toString());


		return "/maquina/listado";

	}
	
	@GetMapping("ver/{id}")
	public String editarMaquina(@PathVariable int id, Model model) {

		LOG.info("Busqueda de maquina por el id");
		Maquina maquina = maquinaServicio.findById(id);
		LOG.info(maquina.toString());
		model.addAttribute("maquina", maquina);
		return "maquina/ver";
	}

	@GetMapping("instalar/{id}")
	public String editarCliente(@PathVariable int id, Model model) {

		LOG.info("Busqueda de maquina por el id");
		Maquina maquina = maquinaServicio.findById(id);
		LOG.info(maquina.toString());
		AuxiliarDatos2 datos = new AuxiliarDatos2();
		model.addAttribute("maquina", maquina);
		model.addAttribute("datos", datos);
		LOG.info("LOS DATOS QUE SALEN DE INSTALAR/ID SON: " + model.toString());
		return "maquina/instalar";
	}
	
	@PostMapping("/instalar")
	public String instalarLampara(@Valid AuxiliarDatos2 datos, BindingResult result, Model model,
			SessionStatus status) {

		LOG.info("LOS DATOS QUE LLEGAN DE INSTALAR/ID SON: " + model.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			LOG.info("LOS DATOS QUE VAN A REENVIARSE INSTALAR/ID SON: " + model.toString());
			return "maquina/instalar";
		}
		
		
	//	maquinaServicio.insertar(maquina);
		status.setComplete();
		return "redirect:/maquina/listado";
	}
	
}
