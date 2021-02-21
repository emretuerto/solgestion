/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dao;

import es.emretuerto.solgestion.modelo.TipoCliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author eduardo
 */
public interface TipoClienteRepository  extends JpaRepository<TipoCliente, Integer>{
    
    public TipoCliente findByCodigo(String codigo);
    
    public List<TipoCliente> findByActivo(boolean existe);
    
    public Page<TipoCliente> findByActivoTrue(Pageable pageable);
    
    
    
}
