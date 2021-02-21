/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author emretuerto
 */
@Entity
@Table(name = "LAMPARAS_INSTALADAS")
public class LamparaInstalada implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAQUINA_ID")
    @Cascade(CascadeType.ALL)
    private Maquina maquina;

    @ManyToOne
    @JoinColumn(name = "LAMPARA_ID")
    @Cascade(CascadeType.ALL)
    private Lampara lampara;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false, name = "FECHA_INSTALACION")
    private Date fechaInstalacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "FECHA_RETIRADA")
    private Date fechaRetirada;

    @Column(name = "MINUTOS_CONSUMIDOS")
    private Integer minutosConsumidos;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    public LamparaInstalada(Maquina maquina, Lampara lampara, Date fechaInstalacion, Integer cantidad) {
        this.maquina = maquina;
        this.lampara = lampara;
        this.fechaInstalacion = fechaInstalacion;
        this.minutosConsumidos = 0;
        this.cantidad = cantidad;
    }

    public LamparaInstalada(Lampara lampara, Date fechaInstalacion, Integer cantidad) {
        this.lampara = lampara;
        this.fechaInstalacion = fechaInstalacion;
        this.cantidad = cantidad;
        this.minutosConsumidos = 0;
    }

    public LamparaInstalada(Maquina maquina, Lampara lampara, Integer cantidad) {
        this.maquina = maquina;
        this.lampara = lampara;
        this.cantidad = cantidad;
        this.minutosConsumidos = 0;
        this.fechaInstalacion = new Date();
    }

    public LamparaInstalada(Lampara lampara, Integer cantidad) {
        this.lampara = lampara;
        this.cantidad = cantidad;
        this.minutosConsumidos = 0;
        this.fechaInstalacion = new Date();
    }

    public LamparaInstalada() {
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Lampara getLampara() {
        return lampara;
    }

    public void setLampara(Lampara lampara) {
        this.lampara = lampara;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public Date getFechaRetirada() {
        return fechaRetirada;
    }

    public void setFechaRetirada(Date fechaRetirada) {
        this.fechaRetirada = fechaRetirada;
    }

    public Integer getMinutosConsumidos() {
        return minutosConsumidos;
    }

    public void setMinutosConsumidos(Integer minutosConsumidos) {
        this.minutosConsumidos = minutosConsumidos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.maquina);
        hash = 29 * hash + Objects.hashCode(this.lampara);
        hash = 29 * hash + Objects.hashCode(this.fechaInstalacion);
        hash = 29 * hash + Objects.hashCode(this.fechaRetirada);
        hash = 29 * hash + Objects.hashCode(this.minutosConsumidos);
        hash = 29 * hash + Objects.hashCode(this.cantidad);
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
        final LamparaInstalada other = (LamparaInstalada) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.maquina, other.maquina)) {
            return false;
        }
        if (!Objects.equals(this.lampara, other.lampara)) {
            return false;
        }
        if (!Objects.equals(this.fechaInstalacion, other.fechaInstalacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaRetirada, other.fechaRetirada)) {
            return false;
        }
        if (!Objects.equals(this.minutosConsumidos, other.minutosConsumidos)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LamparaInstalada{" + "maquina=" + maquina.getNombre() + ", lampara=" + lampara.getCodigo() + ", fechaInstalacion=" + fechaInstalacion + ", fechaRetirada=" + fechaRetirada + ", minutosConsumidos=" + minutosConsumidos + ", cantidad=" + cantidad + '}';
    }

}
