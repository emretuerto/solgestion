package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.dao.ClienteRepository;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.servicios.ClienteServicioInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eduardo
 */
@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicioInterface {


	@Autowired
	ClienteRepository clienteDao;


	@Override
	public void insertar(Cliente cliente) {
		clienteDao.saveAndFlush(cliente);

	}

	@Override
	public Optional<Cliente> buscarPorCodigoBarras(String codigo) {

		return clienteDao.findClienteByCodigoBarras(codigo);

	}

	@Override
	public List<Cliente> listado() {
		List<Cliente> listadoClientes = clienteDao.findAll();
		return listadoClientes;
	}

	@Override
	public Page<Cliente> listadoClientes(Pageable pageable) {

		Page<Cliente> listadoClientes = clienteDao.findAll(pageable);
		return listadoClientes;
	}

	@Override
	public Cliente findById(int id) {
		Cliente cliente = clienteDao.findById(id);
		return cliente;
	}

	@Override
	public void borrar(Cliente cliente) {
		clienteDao.delete(cliente);

	}

	@Override
	public Page<Cliente> listadoOrdenadoPorNombreClientes(Pageable pageable) {
		Page<Cliente> listadoClientesOrdenadoPorNombre = clienteDao.findAllByOrderByNombre(pageable);
		return listadoClientesOrdenadoPorNombre;
	}

	@Override
	public List<Cliente> listadoOrdenadoPorNombre() {
		List<Cliente> listadoClientes = clienteDao.findAllByOrderByNombre();
		return listadoClientes;
	}
	
	@Override
	public boolean existeCliente(String nif) {

		Optional<Cliente> cliente = clienteDao.findByNif(nif);
		return cliente.isPresent();
	}

	public Optional<Cliente> buscaClienteNif(String nif){
		
		return clienteDao.findByNif(nif);
	}
	
	
	@Override
	public List<Cliente> listadoClientesBono(Bono bono) {
	return clienteDao.findByBono(bono);
	}

	@Override
	public void desasociaBono(Cliente cliente) {
	
		cliente.setBono(null);
		
	}
	
	
}
