package es.emretuerto.solgestion.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.emretuerto.solgestion.modelo.TipoCliente;

public interface TipoClienteServicioInterface {
	
	public List<TipoCliente> listarTiposCliente();
	
	public void insertar(TipoCliente tipoCliente);
	
	public boolean existeTipoCliente(String codigo);
	
	public TipoCliente findById(int id);
	
	public void actualizar(TipoCliente tipoCliente);
	
	public List<TipoCliente> listarTiposClienteActivos(boolean activo);
	
	public void eliminaTipoCiente(TipoCliente tipoCliente);
	
	public void desactivaTipoCiente(TipoCliente tipoCliente);
	
	public Page<TipoCliente> listadoActivos(Pageable pageable);
	

}
