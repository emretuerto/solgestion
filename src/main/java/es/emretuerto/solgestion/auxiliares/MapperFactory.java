package es.emretuerto.solgestion.auxiliares;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Factoria de objetos que implementan MapperInterface
 * 
 * @author atraspuesto
 *
 */
public class MapperFactory {
	
	private static Map<Locale, MapperInterface> instances = new HashMap<>();
	
	/**
	 * Devuelve una instancia que implementa MapperInterface y usa el
	 * locale indicado para hacer las transformaciones de string a fechas
	 * y numeros
	 * 
	 * @param locale
	 * @return
	 */
	public static MapperInterface getInstance(Locale locale){
		MapperInterface mapper = null;
		synchronized (MapperFactory.class) {
			mapper = instances.get(locale);
			if(mapper == null){
				mapper = new MapperImpl(locale);
				instances.put(locale, mapper);
			}
		}
		return mapper;
	}
}
