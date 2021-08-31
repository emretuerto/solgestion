/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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

    @NotBlank
    @Pattern(regexp = "[0-9]{4}", message = "Introduzca un código de 4 dígitos numericos")
    @Column(name = "CODIGO", length = 30, nullable = false, unique = true)
    private String codigo;

    @Column(name = "MARCA", length = 30, nullable = false)
    private String marca;

    @Column(name = "MODELO", length = 30, nullable = false)
    private String modelo;

    @NotNull
    @Min(value=0, message = "Introduzca un dato válido" )
    @Digits(fraction = 0, integer = 10, message ="Introduzca un dato válido")
    @Column(name = "DURACION", nullable = false)
    private Integer duracion;

    
    @OneToOne(mappedBy = "lampara", fetch = FetchType.LAZY)
    private Maquina maquina;
    
    
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


	public Lampara(Integer id,
			@NotBlank @Pattern(regexp = "[0-9]{4}", message = "Introduzca un código de 4 dígitos numericos") String codigo,
			String marca, String modelo,
			@NotNull @Min(value = 0, message = "Introduzca un dato válido") @Digits(fraction = 0, integer = 10, message = "Introduzca un dato válido") Integer duracion,
			Maquina maquina) {
		this.id = id;
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.duracion = duracion;
		this.maquina = maquina;
	}

	
	public Lampara(String codigo,String marca, String modelo,Integer duracion) {
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

	
	public Maquina getMaquina() {
		return maquina;
	}


	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Lampara [id=" + id + ", codigo=" + codigo + ", marca=" + marca + ", modelo=" + modelo + ", duracion="
				+ duracion + "]";
	}

   
}
