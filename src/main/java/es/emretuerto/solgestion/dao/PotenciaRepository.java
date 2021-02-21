/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.Potencia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author emretuerto
 */
public interface PotenciaRepository extends  JpaRepository<Potencia, Integer>{
    
}
