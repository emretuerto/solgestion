/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.modelo.Bono;
import es.emretuerto.solgestion.servicios.BonoServicioInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eduardo
 */
@Service
@Transactional
public class BonoServicioImpl implements BonoServicioInterface {

    @Override
    public void recargaSesiones(Bono bono, Double sesiones) {
        bono.setSesiones((bono.getSesiones() + sesiones));
    }

    @Override
    public void recargaMinutos(Bono bono, Integer minutos) {
        bono.setMinutos(bono.getMinutos() + minutos);
    }

    @Override
    public void restarMinutosBono(Bono bono, Integer minutos) {
        bono.setMinutos(bono.getMinutos() - minutos);
    }

    @Override
    public void restarSesionesBono(Bono bono, Double sesiones) {
        bono.setSesiones((bono.getSesiones() - sesiones));
    }

}
