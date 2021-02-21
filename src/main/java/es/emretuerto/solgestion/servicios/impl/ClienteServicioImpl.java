package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.auxiliares.MapperFactory;
import es.emretuerto.solgestion.auxiliares.MapperInterface;
import es.emretuerto.solgestion.dao.ClienteRepository;
import es.emretuerto.solgestion.dto.ClienteDTO;
import es.emretuerto.solgestion.excepciones.MappingException;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eduardo
 */
@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicioInterface {

    private final MapperInterface MAPPER = MapperFactory.getInstance(Locale.GERMAN);
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServicioImpl.class);

    @Autowired
    ClienteRepository clienteDao;

    @Override
    public void altaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        try {
            cliente = MAPPER.map(Cliente.class, clienteDTO);

        } catch (MappingException e) {
        }
        clienteDao.save(cliente);
    }

    @Override
    public ClienteDTO findByCodigo(String codigo) {
        ClienteDTO clienteDTO = new ClienteDTO();

        try {
            Cliente cliente = clienteDao.findByCodigoCliente(codigo);
            clienteDTO = MAPPER.map(ClienteDTO.class, cliente);
        } catch (MappingException e) {
        }
        return clienteDTO;
    }

}
