package es.emretuerto.solgestion.servicios;

import javax.validation.Valid;

import es.emretuerto.solgestion.dto.ClienteDTO;
import es.emretuerto.solgestion.modelo.Cliente;

/**
 *
 * @author eduardo
 */
public interface ClienteServicioInterface {

    
    public void altaCliente(ClienteDTO clienteDTO);

	public void insertar(Cliente cliente);
    
}
