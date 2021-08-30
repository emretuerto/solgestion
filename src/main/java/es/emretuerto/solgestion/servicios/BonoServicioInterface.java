/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios;

import java.util.List;

import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.modelo.Cliente;

/**
 *
 * @author eduardo
 */
public interface BonoServicioInterface {
    
   // public void recargaSesiones(Bono bono, Integer sesiones);

    public void recargaMinutos(Bono bono, Integer minutos);
    
    public void restarMinutosBono (Bono bono, Integer minutos);
    
    // public void restarSesionesBono (Bono bono, Integer sesiones);
    
    public void asocia (Bono bono, Cliente cliente);
    
    public List<Cliente> listadoClientesBono(Bono bono);
    
    public Boolean exiteBono(String codigo);
    
}
