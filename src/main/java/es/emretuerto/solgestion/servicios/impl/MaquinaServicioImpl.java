/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.servicios.impl;

import es.emretuerto.solgestion.modelo.Maquina;
import es.emretuerto.solgestion.servicios.MaquinaServicioInterface;

/**
 *
 * @author eduardo
 */
public class MaquinaServicioImpl implements MaquinaServicioInterface {

    @Override
    public void incrementaContadorTotal(Maquina maquina, Integer minutos) {

        maquina.setContadorTotal(minutos + maquina.getContadorTotal());

    }

    @Override
    public void incrementaContadorParcial(Maquina maquina, Integer minutos) {

        maquina.setContadorParcial(minutos + maquina.getContadorParcial());

    }

    @Override
    public void incrementarContadores(Maquina maquina, Integer minutosTotal, Integer minutosParcial) {

        this.incrementaContadorParcial(maquina, minutosParcial);
        this.incrementaContadorTotal(maquina, minutosTotal);

    }

}
