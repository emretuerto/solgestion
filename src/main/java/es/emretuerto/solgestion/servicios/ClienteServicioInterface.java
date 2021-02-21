package es.emretuerto.solgestion.servicios;

import es.emretuerto.solgestion.dto.ClienteDTO;

/**
 *
 * @author eduardo
 */
public interface ClienteServicioInterface {

    
    public void altaCliente(ClienteDTO clienteDTO);
    
    public ClienteDTO findByCodigo(String codigo);
    
}
