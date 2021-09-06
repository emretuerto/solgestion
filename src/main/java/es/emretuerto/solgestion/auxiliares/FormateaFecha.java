package es.emretuerto.solgestion.auxiliares;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class FormateaFecha {

	    public static String formatDateAndTime(LocalDateTime date) {
	        return date.format(DateTimeFormatter
	                .ofPattern("dd/MM/yyyy--HH:mm"));
	    }
	}
