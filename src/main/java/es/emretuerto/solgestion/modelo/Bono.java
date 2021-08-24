/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Table(name = "BONOS")

public class Bono implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IDENTIFICADOR_BONO", length = 10, nullable = false, unique = true)
    private String identificadorBono;

    @Column(name = "CODIGO_BARRAS", length = 20, unique = true, nullable = true)
    private String codigoBarras;

    @Column(name = "ES_DE_MINUTOS")
    private Boolean esDeMinutos;

    @Column(name = "SESIONES")
    private Integer sesiones;

    @Column(name = "MINUTOS")
    private Integer minutos;

    @OneToMany(mappedBy = "bono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cliente> clientesBono = new ArrayList<Cliente>();

    public Bono() {
    }

    ;

    public Bono(String identificadorBono, String codigoBarras, Boolean esDeMinutos, Integer sesiones, Integer minutos) {
        this.identificadorBono = identificadorBono;
        this.codigoBarras = codigoBarras;
        this.esDeMinutos = esDeMinutos;
        this.sesiones = sesiones;
        this.minutos = minutos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificadorBono() {
        return identificadorBono;
    }

    public void setIdentificadorBono(String identificadorBono) {
        this.identificadorBono = identificadorBono;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Boolean getEsDeMinutos() {
        return esDeMinutos;
    }

    public void setEsDeMinutos(Boolean esDeMinutos) {
        this.esDeMinutos = esDeMinutos;
    }

    public Integer getSesiones() {
        return sesiones;
    }

    public void setSesiones(Integer sesiones) {
        this.sesiones = sesiones;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public List<Cliente> getClientesBono() {
        return clientesBono;
    }

    public void setClientesBono(List<Cliente> clientesBono) {
        this.clientesBono = clientesBono;
        clientesBono.forEach((c) -> {
            c.setBono(this);
        });
    }

    public void addCliente(Cliente cliente) {
        if (!clientesBono.contains(cliente)) {
            clientesBono.add(cliente);
            cliente.setBono(this);
        }
    }

    public void removeCliente(Cliente cliente) {
        if (clientesBono.contains(cliente)) {
            clientesBono.remove(cliente);
            cliente.setBono(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bono other = (Bono) obj;
        if (!Objects.equals(this.identificadorBono, other.identificadorBono)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.esDeMinutos, other.esDeMinutos)) {
            return false;
        }
        if (!Objects.equals(this.sesiones, other.sesiones)) {
            return false;
        }
        if (!Objects.equals(this.minutos, other.minutos)) {
            return false;
        }
        if (!Objects.equals(this.clientesBono, other.clientesBono)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bono{" + "identificadorBono=" + identificadorBono + "Código de barras= " +  codigoBarras + ", esDeMinutos=" + esDeMinutos + ", sesiones=" + sesiones + ", minutos=" + minutos + '}';
    }

}
