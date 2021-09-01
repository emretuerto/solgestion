package es.emretuerto.solgestion.servicios.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.emretuerto.solgestion.dao.LamparaRepository;
import es.emretuerto.solgestion.modelo.Lampara;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.servicios.LamparaServicioInterface;

@Service
@Transactional
public class LamparaServicioImpl implements LamparaServicioInterface {

	@Autowired
	LamparaRepository lamparaDAO;
	
	final Logger LOG = Logger.getLogger("LamparaServicioImpl.class");

	@Override
	public void insertar(Lampara lampara) {

		lamparaDAO.save(lampara);

	}

	@Override
	public Page<Lampara> listadoLamparas(Pageable pageable) {

		return lamparaDAO.findAll(pageable);
	}

	@Override
	public List<Lampara> listadoLamparas() {

		return lamparaDAO.findAll();
	}

	@Override
	public boolean existe(String codigo) {

		return lamparaDAO.existsByCodigo(codigo);
	}

	@Override
	public Lampara buscaPorCodigo(String codigo) {

		return lamparaDAO.findByCodigo(codigo);
	}

	@Override
	public void instalarLampara(Maquina maquina, String codigoLampara) {
		
		Lampara lampara = lamparaDAO.findByCodigo(codigoLampara);
		lampara.setMaquina(maquina);
		LOG.info("VOY A INTENTAR INSTALAR LA MAQUINA EN LA LAMPARA" + lampara.toString());
		LOG.info("VOY A INTENTAR INSTALAR LA MAQUINA EN LA LAMPARA" + lampara.getMaquina().toString());
		lamparaDAO.save(lampara);
		
	}

	@Override
	public void restarSesionLampara(Lampara lampara, Integer duracionSesion) {
		
		lampara.setDuracion(lampara.getDuracion()-duracionSesion);
		lamparaDAO.save(lampara);
	}

}
