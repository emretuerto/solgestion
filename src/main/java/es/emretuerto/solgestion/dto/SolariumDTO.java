/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.dto;

import java.util.List;

/**
 *
 * @author eduardo
 */
public class SolariumDTO {

    private String nombre;
    private String marca;
    private String modelo;
    private List<SesionDTO> sesiones;

    public SolariumDTO() {
    }

    public SolariumDTO(String nombre, String marca, String modelo, List<SesionDTO> sesiones) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.sesiones = sesiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<SesionDTO> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionDTO> sesiones) {
        this.sesiones = sesiones;
    }
    
    

}
