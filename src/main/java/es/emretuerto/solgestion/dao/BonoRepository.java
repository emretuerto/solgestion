/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Bono;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eduardo
 */
public interface BonoRepository extends JpaRepository<Bono, Integer>{
    
    Bono findByIdentificadorBono(String identificador);

	Bono findByCodigoBarras(String codigoBarras);
	
	public Boolean existsByIdentificadorBono(String codigo); 
 
    
}
