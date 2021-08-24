/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.dto.ClienteDTO;
import es.emretuerto.solgestion.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
    
    Cliente findClienteByNif(String nif);
    
    
    ClienteDTO findClienteDTOByNif(String nif);
    
    
    
    
}
