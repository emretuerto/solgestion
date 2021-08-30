/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "LAMPARAS")
public class Lampara implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODIGO", length = 30, nullable = false, unique = true)
    private String codigo;

    @Column(name = "MARCA", length = 30, nullable = false)
    private String marca;

    @Column(name = "MODELO", length = 30, nullable = false)
    private String modelo;

    @Column(name = "DURACION", nullable = false)
    private Integer duracion;

    /*
    @Column(name = "PRECIO")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "POTENCIA_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Potencia potencia;
    
  */  

    public Lampara() {
    }

    public Lampara(String codigo, String marca, String modelo, Integer duracion) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.duracion = duracion;
       
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

	@Override
	public String toString() {
		return "Lampara [id=" + id + ", codigo=" + codigo + ", marca=" + marca + ", modelo=" + modelo + ", duracion="
				+ duracion + "]";
	}



}
