/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.emretuerto.solgestion.modelo.Maquina;

/**
 *
 * @author eduardo
 */
public interface MaquinaServicioInterface {
    
    public void incrementaContadorTotal(Maquina maquina, Integer minutos);
    
    public void incrementaContadorParcial(Maquina maquina, Integer minutos);
    
    public void incrementarContadores(Maquina maquina, Integer minutosTotal, Integer minutosParcial);
    
    public void insertar(Maquina maquina);
    
    public List<Maquina> listado();
    
    public Page<Maquina> listado(Pageable pageRequest);
    
    public Maquina findById(Integer id);
    
    public void instalarLampara(Maquina maquina, String codigoLampara);
    
    public void incrementarContadores (Maquina maquina, Integer duracionSesion);
    
    
    
}
