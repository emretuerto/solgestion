/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.emretuerto.solgestion.dao.MaquinaRepository;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.servicios.MaquinaServicioInterface;

/**
 *
 * @author eduardo
 */

@Service
@Transactional
public class MaquinaServicioImpl implements MaquinaServicioInterface {

	@Autowired
	MaquinaRepository maquinaDAO;

	@Override
	public void incrementaContadorTotal(Maquina maquina, Integer minutos) {

		maquina.setContadorTotal(minutos + maquina.getContadorTotal());

	}

	@Override
	public void incrementaContadorParcial(Maquina maquina, Integer minutos) {

		maquina.setContadorParcial(minutos + maquina.getContadorParcial());

	}

	@Override
	public void incrementarContadores(Maquina maquina, Integer minutosTotal, Integer minutosParcial) {

		this.incrementaContadorParcial(maquina, minutosParcial);
		this.incrementaContadorTotal(maquina, minutosTotal);

	}

	@Override
	public void insertar(Maquina maquina) {

		maquinaDAO.save(maquina);

	}

	@Override
	public List<Maquina> listado() {

		return maquinaDAO.findAll();
	}

	public Page<Maquina> listado(Pageable pageRequest) {

		return maquinaDAO.findAll(pageRequest);
	}

	@Override
	public Maquina findById(Integer id) {

		Optional<Maquina> maquina = maquinaDAO.findById(id);
		return maquina.get();
	}

}
