/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import es.emretuerto.solgestion.modelo.Bono;

/**
 *
 * @author eduardo
 */
public interface BonoServicioInterface {
    
    public void recargaSesiones(Bono bono, Double sesiones);

    public void recargaMinutos(Bono bono, Integer minutos);
    
    public void restarMinutosBono (Bono bono, Integer minutos);
    
    public void restarSesionesBono (Bono bono, Double sesiones);
}
