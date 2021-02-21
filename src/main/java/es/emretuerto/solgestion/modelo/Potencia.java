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
@Table(name = "POTENCIAS")
public class Potencia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "CODIGO", unique = true, nullable = false, length = 4)
    private String codigo;
 
    @Column(name = "POTENCIA", nullable = false, length = 4)
    private Integer potencia;

    public Potencia() {
    }

    public Potencia(String codigo, Integer potencia) {
        this.codigo = codigo;
        this.potencia = potencia;
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

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Potencia{" + "codigo=" + codigo + ", potencia=" + potencia + '}';
    }
    
    

    
}
