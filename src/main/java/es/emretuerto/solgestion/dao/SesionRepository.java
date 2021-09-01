
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Sesion;

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
	
	
    
}
