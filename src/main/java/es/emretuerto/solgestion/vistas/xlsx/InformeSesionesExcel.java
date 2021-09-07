package es.emretuerto.solgestion.vistas.xlsx;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.emretuerto.solgestion.auxiliares.FormateaFecha;
import es.emretuerto.solgestion.modelo.Sesion;
 
public class InformeSesionesExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Sesion> sesiones;
     
    public InformeSesionesExcel(List<Sesion> sesiones) {
        this.sesiones = sesiones;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Sesiones");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Fecha", style);      
        createCell(row, 1, "Nombre", style);       
        createCell(row, 2, "Apellidos", style);    
        createCell(row, 3, "Duración", style);
     //   createCell(row, 4, "Enabled", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Sesion sesion : sesiones) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, FormateaFecha.formatDateAndTime(sesion.getFecha()), style);
            createCell(row, columnCount++, sesion.getCliente().getNombre(), style);
            createCell(row, columnCount++, sesion.getCliente().getApellidos(), style);
            createCell(row, columnCount++, sesion.getDuracion().toString(), style);
   //         createCell(row, columnCount++, user.isEnabled(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}