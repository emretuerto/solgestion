package es.emretuerto.solgestion.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.emretuerto.solgestion.modelo.Lampara;
import es.emretuerto.solgestion.modelo.Maquina;

public interface LamparaServicioInterface {
	
	
	public void insertar(Lampara lampara);
	
	public Page<Lampara> listadoLamparas(Pageable pageable);
	
	public List<Lampara> listadoLamparas();

	public boolean existe(String codigo);
	
	public Lampara buscaPorCodigo(String codigo);
	
	public void instalarLampara(Maquina maquina, String codigoLampara);
	
	public void restarSesionLampara(Lampara lampara, Integer duracionSesion);
	
}
