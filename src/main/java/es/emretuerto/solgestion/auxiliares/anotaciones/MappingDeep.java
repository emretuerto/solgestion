package es.emretuerto.solgestion.auxiliares.anotaciones;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Determina la profundidad de mapeo del atributo anotado
 * deep = 1 ==> que solo se mapearan los atributos de tipos simples 
 * 
 * @author atraspuesto
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingDeep {
	int deep();

}
