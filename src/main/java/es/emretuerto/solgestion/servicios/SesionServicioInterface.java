/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import es.emretuerto.solgestion.modelo.Cliente;
import es.emretuerto.solgestion.modelo.Maquina;

/**
 *
 * @author eduardo
 */
public interface SesionServicioInterface {
    
    public void insertarSesion(Cliente cliente, Maquina solarium, Double sesionesConsumidas,Integer minutos);
    
}
