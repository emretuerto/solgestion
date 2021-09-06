package es.emretuerto.solgestion.vistas.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.emretuerto.solgestion.auxiliares.FormateaFecha;
import es.emretuerto.solgestion.modelo.Sesion;

public class GenerarReportePDF {

	private static final Logger LOG = Logger.getLogger("GenerarReportePDF.class");

	public static ByteArrayInputStream sesionesReport(List<Sesion> sesiones) {

		Document documento = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();


		try {

			PdfPTable table = new PdfPTable(4);
			
			table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 2, 4,1});
			Font fuenteCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Fecha", fuenteCabecera));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Nombre", fuenteCabecera));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Apellidos", fuenteCabecera));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Duración", fuenteCabecera));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Sesion sesion : sesiones) {

				PdfPCell cell;

			
				
				cell = new PdfPCell(new Phrase(FormateaFecha.formatDateAndTime(sesion.getFecha())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(sesion.getCliente().getNombre()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(sesion.getCliente().getApellidos()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(sesion.getDuracion().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}

			PdfWriter.getInstance(documento, out);
			documento.open();
			documento.add(table);

			documento.close();

		} catch (DocumentException ex) {


			LOG.info("Error occurred: {0}" + ex.toString());;
			
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

}
