/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.modelo.Sesion;

/**
 *
 * @author eduardo
 */
public interface SesionServicioInterface {
    
 //   public void insertarSesion(Cliente cliente, Maquina solarium, Double sesionesConsumidas,Integer minutos);
    
    List<Sesion> listadoSesionesFechaInversa();
    
    public Page<Sesion> listadoSesionesFechaInversa(Pageable pageable);
    
    public void registraSesion(Cliente cliente, Maquina maquina, Sesion sesion);
    
    public Page<Sesion> listadoSesionesMaquina(Pageable pageable, Maquina maquina, LocalDateTime inicio, LocalDateTime fin);
    
    public Page<Sesion> listadoMaquina(Integer id, Pageable plageable);
    
}
