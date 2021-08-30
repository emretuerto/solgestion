/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.dao.BonoRepository;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.servicios.BonoServicioInterface;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;
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
public class BonoServicioImpl implements BonoServicioInterface {

	@Autowired
	BonoRepository bonoRepository;

	@Autowired
	ClienteServicioImpl clienteServicio;

	final Logger LOG = Logger.getLogger("BonoServicioImpl.class");

	@Override
	public void recargaMinutos(Bono bono, Integer minutos) {

		try {
			bono.setMinutos(bono.getMinutos() + minutos);
		}

		catch (Exception e) {
			bono.setMinutos(minutos);
		}
		bono.setSesiones(null);
		bonoRepository.save(bono);
	}

	@Override
	public void restarMinutosBono(Bono bono, Integer minutos) {
		bono.setMinutos(bono.getMinutos() - minutos);
		bono.setSesiones(null);
		bonoRepository.save(bono);
	}

	/*
	 * @Override public void restarSesionesBono(Bono bono, Double sesiones) {
	 * bono.setSesiones((bono.getSesiones() - sesiones)); }
	 */

	public void insertar(@Valid Bono bono) {
		bonoRepository.save(bono);

	}

	public Page<Bono> listado(Pageable pageRequest) {

		return bonoRepository.findAll(pageRequest);
	}

	public Optional<Bono> findById(Integer id) {

		return bonoRepository.findById(id);
	}

	public void actualizar(Bono bono) {

		bonoRepository.save(bono);

	}

	public void borrar(Bono bono) {

		bonoRepository.delete(bono);

	}

	public Bono findByIdentificadorBono(String identificadorBono) {

		return bonoRepository.findByIdentificadorBono(identificadorBono);
	}

	public Bono findByCodigoBarras(String codigoBarras) {

		return bonoRepository.findByCodigoBarras(codigoBarras);
	}

	@Override
	public void asocia(Bono bono, Cliente cliente) {
		LOG.info("BONOSERVICIOIMPL - ASIGNA - " + bono.toString());
		LOG.info("BONOSERVICIOIMPL - ASIGNA - " + cliente.toString());
		cliente.setBono(bono);
		LOG.info("BONOSERVICIOIMPL - ASIGNA - " + cliente.toString());
		LOG.info("BONOSERVICIOIMPL - ASIGNA - " + cliente.getBono().toString());
		clienteServicio.insertar(cliente);
	}

	@Override
	public List<Cliente> listadoClientesBono(Bono bono) {

		return clienteServicio.listadoClientesBono(bono);

	}

	@Override
	public Boolean exiteBono(String codigo) {
		return bonoRepository.existsByIdentificadorBono(codigo);
	}

}
