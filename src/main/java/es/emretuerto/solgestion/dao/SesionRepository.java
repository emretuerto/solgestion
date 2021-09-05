
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.modelo.Sesion;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eduardo
 */
public interface SesionRepository extends JpaRepository<Sesion, Long>{
	
	List<Sesion> findAllByOrderByFechaDesc();
	
	public Page<Sesion> findAllByOrderByFechaDesc(Pageable pageable);
	
	public Page<Sesion> findAllByMaquinaBetween(Pageable pageable, Maquina maquina, LocalDateTime inicio, LocalDateTime fin);
	
//	public Page<Sesion> findByMaquinaIdByOrderByFechaDesc(Integer id,Pageable pageable);
	
//	public List<Sesion> findByMaquinaIdByOrderByFechaDesc(Integer id);
	public Page<Sesion> findByMaquinaIdOrderByFechaDesc(Integer id, Pageable pageable);
    
}
