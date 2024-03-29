/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Lampara;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author emretuerto
 */
public interface LamparaRepository extends JpaRepository<Lampara, Integer>{
	
	
	public Page<Lampara> findAllByOrderByMarca(Pageable pageable);
	
	public boolean existsByCodigo(String codigo);
	
	public Lampara findByCodigo(String codigo);
	
	
      
}
