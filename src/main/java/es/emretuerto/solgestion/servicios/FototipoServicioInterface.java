package es.emretuerto.solgestion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import es.emretuerto.solgestion.modelo.Fototipo;

@Service
public interface FototipoServicioInterface {

	public List<Fototipo> listadoFototipos();
	
}
