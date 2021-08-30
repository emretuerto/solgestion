package es.emretuerto.solgestion.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.emretuerto.solgestion.dao.FototipoRepository;
import es.emretuerto.solgestion.modelo.Fototipo;
import es.emretuerto.solgestion.servicios.FototipoServicioInterface;

@Service
@Transactional
public class FototipoServicioImpl implements FototipoServicioInterface {

	
	@Autowired
	FototipoRepository fototipoRepository;
	
	@Override
	public List<Fototipo> listadoFototipos() {

		return fototipoRepository.findAll();

		
	}

}
