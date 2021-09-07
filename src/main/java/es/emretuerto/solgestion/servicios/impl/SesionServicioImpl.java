/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.dao.BonoRepository;
import es.emretuerto.solgestion.dao.SesionRepository;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Sesion;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.servicios.BonoServicioInterface;
import es.emretuerto.solgestion.servicios.LamparaServicioInterface;
import es.emretuerto.solgestion.servicios.SesionServicioInterface;


import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

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
public class SesionServicioImpl implements SesionServicioInterface {

	final Logger LOG = Logger.getLogger("SesionServicioImpl.class");

	@Autowired
	SesionRepository sesionDao;

	@Autowired
	BonoServicioInterface bonoServicio;

	@Autowired
	BonoRepository bonoDAO;

	@Autowired
	MaquinaServicioImpl maquinaServicio;

	@Autowired
	LamparaServicioInterface lamparaServicio;

	/*
	 * @Override
	 * 
	 * @Transactional public void insertarSesion(Cliente cliente, Maquina solarium,
	 * Double sesionesConsumidasBono, Integer minutosConsumidos) {
	 * 
	 * Sesion sesion = new Sesion(cliente, solarium, sesionesConsumidasBono,
	 * minutosConsumidos);
	 * 
	 * Bono bono = cliente.getBono(); if (bono != null) { if (bono.getEsDeMinutos())
	 * { bonoServicio.restarMinutosBono(bono, minutosConsumidos); } else { //
	 * bonoServicio.restarSesionesBono(bono, sesionesConsumidasBono); } }
	 * 
	 * sesionDao.save(sesion); }
	 */
	@Override
	public List<Sesion> listadoSesionesFechaInversa() {

		return sesionDao.findAllByOrderByFechaDesc();

	}

	@Override
	public Page<Sesion> listadoSesionesFechaInversa(Pageable pageable) {

		return sesionDao.findAllByOrderByFechaDesc(pageable);
	}

	@Override
	@Transactional
	public void registraSesion(Cliente cliente, Maquina maquina, Sesion sesion) {

		maquinaServicio.incrementarContadores(maquina, sesion.getDuracion());

		lamparaServicio.restarSesionLampara(maquina.getLampara(), sesion.getDuracion());

		try {
			LOG.info("VAMOS A INTENTAR RESTAR LA SESION DEL BONO");
			// cliente.getBono().setMinutos(cliente.getBono().getMinutos()-sesion.getDuracion());
			Bono bono = cliente.getBono();
			bono.setMinutos(bono.getMinutos() - sesion.getDuracion());
			LOG.info("¿LO HABREMOS LOGRADO?" + bono.getMinutos());
			bonoDAO.save(bono);

		} catch (Exception e) {

		}

		sesion.setCliente(cliente);

		sesion.setMaquina(maquina);

	}

	@Override
	public Page<Sesion> listadoSesionesMaquina(Pageable pageable, Maquina maquina, LocalDateTime inicio,
			LocalDateTime fin) {

		return sesionDao.findAllByMaquinaBetween(pageable, maquina, inicio, fin);
	}

	@Override
	public Page<Sesion> listadoMaquina(Integer id,Pageable pageable) {

		//return sesionDao.findByMaquinaIdByOrderByFechaDesc(id);
		
		return sesionDao.findByMaquinaIdOrderByFechaDesc(id,pageable);
	}

	@Override
	public List<Sesion> listadoMaquinaFechas(Integer id, LocalDateTime desde, LocalDateTime hasta) {
		
		return sesionDao.findByMaquinaIdAndFechaBetweenOrderByFecha(id, desde, hasta);
	}

	@Override
	public Page<Sesion> listadoSesionesMaquinaFechas(Pageable pageable, Integer id, LocalDateTime inicio,
			LocalDateTime fin) {
	return sesionDao.findByMaquinaIdAndFechaBetweenOrderByFecha(id, inicio, fin, pageable);
	}


}
