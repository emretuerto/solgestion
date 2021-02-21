/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dto;

import java.util.Date;

/**
 *
 * @author eduardo
 */
public class SesionDTO {

    private Date fecha;
    private ClienteDTO clienteDTO;
    private SolariumDTO solariumDTO;
    private Integer duracion;

    public SesionDTO() {
    }

    public SesionDTO(Date fecha, ClienteDTO clienteDTO, SolariumDTO solariumDTO, Integer duracion) {
        this.fecha = fecha;
        this.clienteDTO = clienteDTO;
        this.solariumDTO = solariumDTO;
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public SolariumDTO getSolariumDTO() {
        return solariumDTO;
    }

    public void setSolariumDTO(SolariumDTO solariumDTO) {
        this.solariumDTO = solariumDTO;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    
    

}
