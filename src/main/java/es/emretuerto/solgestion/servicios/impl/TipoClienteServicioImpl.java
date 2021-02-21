package es.emretuerto.solgestion.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.emretuerto.solgestion.dao.TipoClienteRepository;
import es.emretuerto.solgestion.modelo.TipoCliente;
import es.emretuerto.solgestion.servicios.TipoClienteServicioInterface;



@Service
public class TipoClienteServicioImpl implements TipoClienteServicioInterface{

	@Autowired
	TipoClienteRepository tipoClienteRepository;
	
	@Override
	public List<TipoCliente> listarTiposCliente() {
		List<TipoCliente> listadoTipoClientes = new ArrayList<>();
		listadoTipoClientes = tipoClienteRepository.findAll();
		return listadoTipoClientes;
	}

	@Override
	@Transactional
	public void insertar(TipoCliente tipoCliente) {
		tipoClienteRepository.save(tipoCliente);
		
	}

	@Override
	public boolean existeTipoCliente(String codigo) {
		
		return (tipoClienteRepository.findByCodigo(codigo)!=null);
	}

	@Override
	public TipoCliente findById(int id) {
		
		return tipoClienteRepository.getOne(id);
	}


	@Transactional
	public void actualizar(TipoCliente tipoCliente) {
		tipoClienteRepository.save(tipoCliente);
		
	}

	@Override
	public List<TipoCliente> listarTiposClienteActivos(boolean activo) {
		return tipoClienteRepository.findByActivo(activo);
	}

	@Override
	@Transactional
	public void eliminaTipoCiente(TipoCliente tipoCliente) {
		
		tipoClienteRepository.delete(tipoCliente);
	}

	@Override
	@Transactional
	public void desactivaTipoCiente(TipoCliente tipoCliente) {
		
		tipoCliente.setActivo(false);
		tipoClienteRepository.save(tipoCliente);
		
	}

	@Override
	public Page<TipoCliente> listadoActivos(Pageable pageable) {
	
		return tipoClienteRepository.findByActivoTrue(pageable);
	}


	


}
