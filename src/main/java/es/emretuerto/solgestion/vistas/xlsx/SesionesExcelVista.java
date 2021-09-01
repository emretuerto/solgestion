package es.emretuerto.solgestion.vistas.xlsx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import es.emretuerto.solgestion.modelo.Sesion;

@Component("sesiones/excel")
public class SesionesExcelVista extends AbstractXlsView{
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"listado_sesiones.xlsx\"");
		List<Sesion> sesiones = (List<Sesion>)model.get("listaSesiones");
		
		Sheet sheet = workbook.createSheet("Listado de sesiones");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("LISTADO DE SESIONES");
		Row header = sheet.createRow(2);
		header.createCell(0).setCellValue("Fecha");
		header.createCell(1).setCellValue("Nombre");
		header.createCell(2).setCellValue("Apellidos");
		header.createCell(3).setCellValue("Maquina");
		header.createCell(3).setCellValue("Sesión");

		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		int fila = 4;
		for (Sesion sesion : sesiones) {
			Row filaDetalle = sheet.createRow(2);
			filaDetalle.createCell(0).setCellValue(sesion.getFecha().toString());
			filaDetalle.createCell(1).setCellValue(sesion.getCliente().getNombre());
			filaDetalle.createCell(2).setCellValue(sesion.getCliente().getApellidos());
			filaDetalle.createCell(3).setCellValue(sesion.getMaquina().getNombre());
			filaDetalle.createCell(3).setCellValue(sesion.getDuracion().toString());
			fila++;
			
		}
	}

}
