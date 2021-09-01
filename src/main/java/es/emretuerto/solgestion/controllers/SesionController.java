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
import es.emretuerto.solgestion.dao.SesionRepository;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.modelo.Sesion;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;
import es.emretuerto.solgestion.servicios.MaquinaServicioInterface;
import es.emretuerto.solgestion.servicios.SesionServicioInterface;
import es.emretuerto.solgestion.validaciones.ClienteValidator;

@Controller
@SessionAttributes({ "sesion", "cliente", "maquina" })
@RequestMapping("/sesion")
public class SesionController {

	final Logger LOG = Logger.getLogger("SesionController.class");

	@Autowired
	ClienteServicioInterface clienteServicio;

	@Autowired
	SesionServicioInterface sesionServicio;

	@Autowired
	private ClienteValidator validador;

	@Autowired
	private MaquinaServicioInterface maquinaServicio;
	
	@Autowired
	SesionRepository sesionDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		if (binder.getObjectName().equals("cliente")) {
			binder.addValidators(validador);
		}
	}

	@GetMapping("/alta")
	public String altaInicialSesion(AuxiliarDatos auxiliarDatos, Model model) {

		LOG.info("Parece que ha llegado bien, vamos a inicial el alta de sesion de bronceado");
		model.addAttribute("auxiliarDatos", auxiliarDatos);
		LOG.info(auxiliarDatos.toString());
		LOG.info(model.toString());

		return "sesion/alta";
	}

	@PostMapping("/alta")
	public String altaInicialEnvioSesion(@Valid AuxiliarDatos auxiliarDatos, BindingResult result, Model model) {

		Cliente cliente;
		LOG.info("hARÁ LO QUE TIENEN QUE HACER???");
		LOG.info(model.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "sesion/alta";
		}
		try {

			LOG.info("Entra a buscar el cliente por código de barras");
			cliente = clienteServicio.buscarPorCodigoBarras(auxiliarDatos.getDato1());
			model.addAttribute("cliente", cliente);

			LOG.info(cliente.toString());
			model.addAttribute("cliente", cliente);
		} catch (Exception e) {
			LOG.info("No ha encontrado en cliente");
			cliente = new Cliente();
			cliente.setCodigoBarras(auxiliarDatos.getDato1());
			model.addAttribute("cliente", cliente);
			return "redirect:/cliente/altainicial/" + auxiliarDatos.getDato1();

		}

		List<Maquina> maquinas = maquinaServicio.listado();
		model.addAttribute("maquinas", maquinas);
		LOG.info("EL MODEL DEL ALTA DE LA SESION ES: " + model.toString());
		return "sesion/altafase2";
	}

	@GetMapping("registrarsesion/{id}/{id2}")
	public String registroSesionClienteMaquina(@PathVariable int id, @PathVariable int id2, Model model) {

		LOG.info("ULTIMA FASE DEL REGISTRO DE LA SESION Y EL MODEL ES" + model.toString());
		Sesion sesion = new Sesion();

		Maquina maquina = maquinaServicio.findById(id2);
		LOG.info("LA SESION CREADA ES " + sesion.toString());
		LOG.info("EL CLIENTE DE LA SESION ES: " + ((Cliente) model.getAttribute("cliente")).toString());

		sesion.setDuracion(maquina.getDuracionSesion());

		model.addAttribute("maquina", maquina);
		model.addAttribute("sesion", sesion);

		return "sesion/altafase3";
	}

	@PostMapping("registrarsesion")
	public String registroFinalSesion(Model model, SessionStatus status) {

		LOG.info("ULTIMA FASE DEL REGISTRO Y DEFINITIVA DE LA SESION Y EL MODEL ES" + model.toString());
		LOG.info("LA MAQUINA DE LA SESION ES " + ((Maquina) model.getAttribute("maquina")).toString());
		LOG.info("EL CLIENTE DE LA SESION ES: " + ((Cliente) model.getAttribute("cliente")).toString());
		LOG.info("LA SESION ES" + ((Sesion) model.getAttribute("sesion")).toString());

		Cliente cliente = (Cliente) model.getAttribute("cliente");
		Maquina maquina = (Maquina) model.getAttribute("maquina");
		Sesion sesion = (Sesion) model.getAttribute("sesion");
		sesionServicio.registraSesion(cliente, maquina, sesion);
		LOG.info("SE SUPONE QUE YA DEBERIA ESTAR TODO HECHO" + sesion.toString());
		LOG.info("SE SUPONE QUE YA DEBERIA ESTAR TODO HECHO" + sesion.getMaquina().toString());
		LOG.info("SE SUPONE QUE YA DEBERIA ESTAR TODO HECHO" + sesion.getMaquina().getLampara().toString());
		LOG.info("SE SUPONE QUE YA DEBERIA ESTAR TODO HECHO" + sesion.getCliente().toString());
		sesionDAO.save(sesion);
		status.setComplete();
		return "redirect:alta";
	}

	@GetMapping("/listado")
	public String listarSesiones(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			SessionStatus status) {

		List<Sesion> listadoSesiones = new ArrayList<>();
		listadoSesiones = sesionServicio.listadoSesionesFechaInversa();
		model.addAttribute("listadoSesiones", listadoSesiones);

		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Sesion> sesiones = sesionServicio.listadoSesionesFechaInversa(pageRequest);
		PageRender<Sesion> pageRender = new PageRender<>("/sesion/listado", sesiones);

		model.addAttribute("listadoSesiones", sesiones);
		model.addAttribute("page", pageRender);
		status.setComplete();
		return "/sesion/listado";

	}

}
