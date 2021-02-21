/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import es.emretuerto.solgestion.modelo.Maquina;

/**
 *
 * @author eduardo
 */
public interface MaquinaServicioInterface {
    
    public void incrementaContadorTotal(Maquina maquina, Integer minutos);
    
    public void incrementaContadorParcial(Maquina maquina, Integer minutos);
    
    public void incrementarContadores(Maquina maquina, Integer minutosTotal, Integer minutosParcial);
    
}
