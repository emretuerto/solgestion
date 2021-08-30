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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

	@Column(name = "IDENTIFICADOR_MAQUNA", length = 10, nullable = false, unique = true)
	@NotBlank
	@Pattern(regexp = "[0-9]{4}", message = "Introduzca un código de 4 dígitos numericos")
	private String identificador;

	@NotBlank
	@Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
	private String nombre;

	@Column(name = "MARCA", length = 50, nullable = true, unique = false)
	private String marca;

	@Column(name = "MODELO", length = 50, nullable = true, unique = false)
	private String modelo;

	@NotNull
	@Column(name = "CONTADOR_TOTAL", nullable = false)
	private Integer contadorTotal;

	@Column(name = "CONTADOR_PARCIAL", nullable = true)
	private Integer contadorParcial;

/*	@OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "SESION_ID")
	private List<Sesion> sesiones = new ArrayList<Sesion>();

	@OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "LAMPARAINSTALADA_ID")
	private List<LamparaInstalada> lamparasInstaladas = new ArrayList<LamparaInstalada>();

*/
	@Column(name="LAMPARA")
	private Lampara lampara;

public Maquina(String identificador,String nombre, String marca, String modelo, @NotNull Integer contadorTotal,
		Integer contadorParcial) {
	this.identificador = identificador;
	this.nombre = nombre;
	this.marca = marca;
	this.modelo = modelo;
	this.contadorTotal = contadorTotal;
	this.contadorParcial = contadorParcial;
}
	

	
	public Maquina() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getIdentificador() {
		return identificador;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
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


	public Lampara getLampara() {
		return lampara;
	}


	public void setLampara(Lampara lampara) {
		this.lampara = lampara;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Maquina [id=" + id + ", identificador=" + identificador + ", nombre=" + nombre + ", marca=" + marca
				+ ", modelo=" + modelo + ", contadorTotal=" + contadorTotal + ", contadorParcial=" + contadorParcial
				+ ", lampara=" + lampara + "]";
	}
	
	
	
	
}
