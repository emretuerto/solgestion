/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "MAQUINAS")
public class Maquina implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8562075715286770034L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "MARCA", length = 50, nullable = true, unique = false)
    private String marca;

    @Column(name = "MODELO", length = 50, nullable = true, unique = false)
    private String modelo;

    @Column(name = "CONTADOR_TOTAL", nullable = false)
    private Integer contadorTotal;

    @Column(name = "CONTADOR_PARCIAL", nullable = true)
    private Integer contadorParcial;

    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "SESION_ID")
    private List<Sesion> sesiones = new ArrayList<Sesion>();

    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "LAMPARAINSTALADA_ID")
    private List<LamparaInstalada> lamparasInstaladas = new ArrayList<LamparaInstalada>();

    public Maquina(String nombre, String marca, String modelo) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Maquina(String nombre, String marca, String modelo, Integer contadorTotal) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.contadorTotal = contadorTotal;
    }

    public Maquina(String nombre, String marca, String modelo, Integer contadorTotal, Integer contadorParcial) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.contadorTotal = contadorTotal;
        this.contadorParcial = contadorParcial;
    }

    public Maquina() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getContadorTotal() {
        return contadorTotal;
    }

    public void setContadorTotal(Integer contadorTotal) {
        this.contadorTotal = contadorTotal;
    }

    public Integer getContadorParcial() {
        return contadorParcial;
    }

    public void setContadorParcial(Integer contadorParcial) {
        this.contadorParcial = contadorParcial;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
        sesiones.forEach((s) -> {
            s.setMaquina(this);
        });
    }

    public void addSesion(Sesion sesion) {
        if (!sesiones.contains(sesion)) {
            sesiones.add(sesion);
            sesion.setMaquina(this);
        }
    }

    public void removeSesion(Sesion sesion) {
        if (sesiones.contains(sesion)) {
            sesiones.remove(sesion);
            sesion.setMaquina(null);
        }
    }

    public void addLamparaInstalada(LamparaInstalada lamparaInstalada) {
        if (!lamparasInstaladas.contains(lamparaInstalada)) {
            lamparasInstaladas.add(lamparaInstalada);
            lamparaInstalada.setMaquina(this);
        }
    }

    public void removeLamparaInstalada(LamparaInstalada lamparaInstalada) {
        if (lamparasInstaladas.contains(lamparaInstalada)) {
            lamparasInstaladas.remove(lamparaInstalada);
            lamparaInstalada.setMaquina(this);
        }
    }

    public void setLamparasInstaladas(List<LamparaInstalada> lamparasInstaladas) {
        this.lamparasInstaladas = lamparasInstaladas;
        lamparasInstaladas.forEach((l) -> {
            l.setMaquina(this);
        });
    }

    public List<LamparaInstalada> getLamparasInstaladas() {
        return lamparasInstaladas;
    }

    @Override
    public String toString() {
        return "Solarium{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", modelo=" + modelo + ", sesiones=" + sesiones + '}';
    }

}
