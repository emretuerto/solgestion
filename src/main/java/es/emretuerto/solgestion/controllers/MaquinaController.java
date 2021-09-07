package es.emretuerto.solgestion.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.emretuerto.solgestion.auxiliares.AuxiliarDatos2;
import es.emretuerto.solgestion.auxiliares.PageRender;
import es.emretuerto.solgestion.auxiliares.RangoFechas;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.modelo.Sesion;
import es.emretuerto.solgestion.servicios.LamparaServicioInterface;
import es.emretuerto.solgestion.servicios.MaquinaServicioInterface;
import es.emretuerto.solgestion.servicios.SesionServicioInterface;
import es.emretuerto.solgestion.validaciones.AuxiliarDatos2Validator;
import es.emretuerto.solgestion.vistas.pdf.GenerarReportePDF;
import es.emretuerto.solgestion.vistas.xlsx.InformeSesionesExcel;

@Controller
@SessionAttributes({ "maquina", "datos", "fechas", "pdf" })
@RequestMapping("/maquina")
public class MaquinaController {

	final Logger LOG = Logger.getLogger("MaquinaController.class");

	@Autowired
	MaquinaServicioInterface maquinaServicio;

	@Autowired
	LamparaServicioInterface lamparaServicio;

	@Autowired
	SesionServicioInterface sesionServicio;

	@Autowired
	private AuxiliarDatos2Validator validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		if (binder.getObjectName().equals("auxiliarDatos2")) {
			binder.addValidators(validador);
		}
	}

	@GetMapping("/alta")
	public String altaMaquina(Model model) {

		Maquina maquina = new Maquina();
		model.addAttribute("maquina", maquina);
		LOG.info("EL CONTENIDO DEL MODEL EN EL METODO GET DE ALTA ES: " + model.toString());
		LOG.info("Y EL DE LA MAQUINA: " + maquina.toString());

		return "maquina/alta";
	}

	@PostMapping("/alta")
	public String altaInicialEnvio(@Valid Maquina maquina, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {

			LOG.info(result.toString());
			return "maquina/alta";
		}

		maquinaServicio.insertar(maquina);
		status.setComplete();
		return "redirect:/maquina/listado";
	}

	@GetMapping("/listado")
	public String listarMaquinas(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			SessionStatus status) {

		Pageable pageRequest = PageRequest.of(page, 9);
		Page<Maquina> maquinas = maquinaServicio.listado(pageRequest);
		PageRender<Maquina> pageRender = new PageRender<>("/maquina/listado", maquinas);

		model.addAttribute("listadoMaquinas", maquinas);
		model.addAttribute("page", pageRender);

		LOG.info("DATOS DEL LIST DE MAQUINAS" + maquinas.toString());
		LOG.info("DATOS DEL LIST DE MAQUINAS" + pageRequest.toString());
		status.setComplete();
		return "/maquina/listado";

	}

	@GetMapping("ver/{id}")
	public String editarMaquina(@PathVariable int id, Model model) {

		LOG.info("Busqueda de maquina por el id");
		Maquina maquina = maquinaServicio.findById(id);
		LOG.info("LA MAQUINA A VISUALIZAR ES: " + maquina.toString());
		LOG.info("LA LAMPARA INSTALADA ES: " + maquina.getLampara());
		model.addAttribute("maquina", maquina);
		return "maquina/ver";
	}

	@GetMapping("instalar/{id}")
	public String editarCliente(@PathVariable int id, Model model) {

		LOG.info("Busqueda de maquina por el id");
		Maquina maquina = maquinaServicio.findById(id);
		LOG.info(maquina.toString());
		AuxiliarDatos2 auxiliarDatos2 = new AuxiliarDatos2();
		model.addAttribute("maquina", maquina);
		model.addAttribute("auxiliarDatos2", auxiliarDatos2);
		LOG.info("LOS DATOS QUE SALEN DE INSTALAR/ID SON: " + model.toString());
		return "maquina/instalar";
	}

	@PostMapping("/instalar")
	public String instalarLampara(@Valid AuxiliarDatos2 auxiliarDatos2, BindingResult result, Model model,
			SessionStatus status) {

		LOG.info("LOS DATOS QUE LLEGAN DE INSTALAR/ID SON: " + model.toString());

		if (result.hasErrors()) {

			LOG.info(result.toString());
			LOG.info("LOS DATOS QUE VAN A REENVIARSE INSTALAR/ID SON: " + model.toString());
			return "maquina/instalar";
		}

		Maquina maquina = (Maquina) model.getAttribute("maquina");
		maquinaServicio.instalarLampara(maquina, auxiliarDatos2.getDato2());
		// lamparaServicio.instalarLampara(maquina, auxiliarDatos2.getDato2());
		status.setComplete();
		return "redirect:/maquina/listado";
	}

	@GetMapping("/listado/{id}")
	public String listarSesionesMaquinas(@PathVariable Integer id, Model model) {

		/*
		 * Pageable pageRequest = PageRequest.of(page, 11); Page<Sesion> sesiones =
		 * sesionServicio.listadoMaquina(id, pageRequest); PageRender<Sesion> pageRender
		 * = new PageRender<>("", sesiones);
		 */

		RangoFechas fechas = new RangoFechas();
		Maquina maquina = maquinaServicio.findById(id);

		model.addAttribute("fechas", fechas);
		model.addAttribute("maquina", maquina);

		// model.addAttribute("page", pageRender);

		LOG.info("PREPARANDO LISTADO DE SESIONES POR MÁQUINA");
		return "/maquina/listadosesionesmaquina";

	}

	@PostMapping("/listadosesiones")
	public String listarSesionesMaquinasFechas(RangoFechas fechas,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		LocalDateTime inicio, fin;

		inicio = fechas.getInicio().atStartOfDay();
		fin = fechas.getFin().plusDays(1).atStartOfDay();

		Maquina maquina = (Maquina) model.getAttribute("maquina");
		LOG.info("LAS FECHAS QUE LLEGAN SON " + fechas.toString());

		Pageable pageRequest = PageRequest.of(page, 11);
		Page<Sesion> sesiones = sesionServicio.listadoSesionesMaquinaFechas(pageRequest, maquina.getId(), inicio, fin);
		List<Sesion> aver = sesionServicio.listadoMaquinaFechas(maquina.getId(), inicio, fin);

		LOG.info("EN EL LISTADO ANTERIOR LAS SESIONES SON" + aver.toString());
		LOG.info("EN EL LISTADO ANTERIOR LAS SESIONES SON" + maquina.getId());
		LOG.info("EN EL LISTADO ANTERIOR LAS SESIONES SON" + inicio);
		LOG.info("EN EL LISTADO ANTERIOR LAS SESIONES SON" + fin);

		PageRender<Sesion> pageRender = new PageRender<>("listadosesiones", sesiones);

		model.addAttribute("listadoSesiones", sesiones);
		model.addAttribute("page", pageRender);
		model.addAttribute("pdf", aver);

		LOG.info("PREPARANDO LISTADO DE SESIONES POR MÁQUINA");
		return "/maquina/listadosesiones";

	}

	@RequestMapping(value = "/listado/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> listadoPdf(Model model) {


		Maquina maquina = (Maquina) model.getAttribute("maquina");
		RangoFechas fechas = (RangoFechas) model.getAttribute("fechas");
		@SuppressWarnings("unchecked")
		List<Sesion> sesionesPDF = (List<Sesion>) model.getAttribute("pdf");

		LocalDateTime inicio, fin;
		inicio = fechas.getInicio().atStartOfDay();
		fin = fechas.getFin().plusDays(1).atStartOfDay();

		LOG.info("VAMOS A VER SI PASAMOS EL LISTADO A PDF" + model.toString());
		LOG.info("VAMOS A VER SI PASAMOS EL LISTADO A PDF" + maquina.toString());
		LOG.info("VAMOS A VER SI PASAMOS EL LISTADO A PDF" + fechas.toString());
		LOG.info("EN EL LISTADO POSTERIOR LAS SESIONES SON" + sesionesPDF.toString());


		List<Sesion> aver = sesionServicio.listadoMaquinaFechas(maquina.getId(), inicio, fin);

		LOG.info("QUE SESIONES LLEGAN" + aver.toString());

		ByteArrayInputStream bis = GenerarReportePDF.sesionesReport(sesionesPDF);

		var headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=sesiones.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	 @GetMapping("/listado/excel")
	    public void exportToExcel(HttpServletResponse response, Model model) throws IOException {
	        response.setContentType("application/octet-stream");
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=sesiones_listado.xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        @SuppressWarnings("unchecked")
			List<Sesion> sesionesPDF = (List<Sesion>) model.getAttribute("pdf");
	         
	        InformeSesionesExcel excelExporter = new InformeSesionesExcel(sesionesPDF);
	         
	        excelExporter.export(response);    
	    }   
}
