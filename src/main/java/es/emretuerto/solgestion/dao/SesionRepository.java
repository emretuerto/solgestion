
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eduardo
 */
public interface SesionRepository extends JpaRepository<Sesion, Long>{
    
}
