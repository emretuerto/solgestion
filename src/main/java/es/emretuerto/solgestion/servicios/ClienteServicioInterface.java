package es.emretuerto.solgestion.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;

/**
 *
 * @author eduardo
 */
public interface ClienteServicioInterface {

    


	public void insertar(Cliente cliente);
	
	public Cliente buscarPorCodigoBarras(String codigo);

	public List<Cliente> listado();
	
	public Page<Cliente> listadoClientes(Pageable pageable);

	public Cliente findById(int id);
	
	public void borrar(Cliente cliente);
	
	public Page<Cliente> listadoOrdenadoPorNombreClientes(Pageable pageable);
	
	public List<Cliente> listadoOrdenadoPorNombre();
	
	public boolean existeCliente(String codigo);
	
	public List<Cliente> listadoClientesBono(Bono bono);
	
	public void desasociaBono(Cliente cliente);
	
    
}
