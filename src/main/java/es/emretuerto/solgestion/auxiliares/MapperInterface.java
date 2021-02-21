package es.emretuerto.solgestion.auxiliares;

import es.emretuerto.solgestion.excepciones.MappingException;




/**
 * Las clasess que implementan esta interfaz
 * devuelven objetos de la clase indicada
 * construidos a partir de la instancia que se pasa como
 * parametro, mapeando los campos que aparecen en ambas clases
 * que se llamen igual
 * 
 * 
 * @author atraspuesto
 *
 */
public interface MapperInterface {
	
	/**
	 * Mapea el objeto src a otro de la clase dstClass
	 * @param dstClass clase destino
	 * @param src objeto origen
	 * @return
	 * @throws MappingException
	 */
	public <R, P> R map(Class<R> dstClass, P src) throws MappingException;
}
