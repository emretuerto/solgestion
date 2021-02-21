package es.emretuerto.solgestion.auxiliares.anotaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indica al mapper que el campo string marcado
 * con esta anotacion debe convertirse en un objeto
 * fecha siguiendo el patron indicado
 * 
 * @author atraspuesto
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingDateFormat {
	
	String pattern();
}
