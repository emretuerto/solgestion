/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.dto.ClienteDTO;
import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
    
    Cliente findClienteByNif(String nif);
    
    
    ClienteDTO findClienteDTOByNif(String nif);
    
    Optional<Cliente> findClienteByCodigoBarras(String codigo);
    
    public Page<Cliente> findAll(Pageable pageable);
    
    Cliente findById(int id);
    
    public Page<Cliente> findAllByOrderByNombre(Pageable pageable);
    
    public List<Cliente> findAllByOrderByNombre();
    
  //  public Cliente findByNif(String nif);
    
    public List<Cliente> findByBono(Bono bono);
    
    public Optional<Cliente>  findByNif(String nif);
    
    
}
