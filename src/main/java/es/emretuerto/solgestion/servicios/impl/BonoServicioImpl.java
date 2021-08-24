/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.dao.BonoRepository;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.servicios.BonoServicioInterface;

import java.util.Optional;

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

	/*
	@Override
	public void recargaSesiones(Bono bono, Double sesiones) {
		bono.setSesiones((bono.getSesiones() + sesiones));
	}*/

	@Override
	public void recargaMinutos(Bono bono, Integer minutos) {
		bono.setMinutos(bono.getMinutos() + minutos);
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
	@Override
	public void restarSesionesBono(Bono bono, Double sesiones) {
		bono.setSesiones((bono.getSesiones() - sesiones));
	}*/

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
}
