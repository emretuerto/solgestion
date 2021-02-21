package es.emretuerto.solgestion.auxiliares.anotaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indica al Mapper el formato numerico que se debe
 * dar al transformar un numero a String
 * 
 * @author atraspuesto
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingNumberFormat {
	
	String pattern();
	
	
}
