package es.emretuerto.solgestion.auxiliares.anotaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Determina el nombre que usara el mapeador para 
 * referirse al atributo anotado
 * @author atraspuesto
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingName {
	String value();
}
