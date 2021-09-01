package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "SESIONES")

public class Sesion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(updatable = false, nullable = false, name = "FECHA")
    private LocalDate fecha;

    @ManyToOne
    @Cascade(CascadeType.MERGE)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @Cascade(CascadeType.MERGE)
    @JoinColumn(name = "MAQUINA_ID", nullable = false)
    private Maquina maquina;

    /*
    @Column(name = "SESIONES_CONSUMIDAS_BONO", nullable = true)
    private Double sesionesConsumidasBono;
    */
    
    
    @Column(name = "DURACION", nullable = false)
    private Integer duracion;

    public Sesion(Cliente cliente, Maquina maquina, Double sesionesConsumidasBono, Integer duracion) {
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.maquina = maquina;
        this.duracion = duracion;
    }

    public Sesion() {
    	this.fecha = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void setMaquina(Maquina solarium) {
        this.maquina = solarium;
    }

	@Override
	public String toString() {
		return "Sesion [fecha=" + fecha + ", duracion=" + duracion + "]";
	}

    /*
    public Double getSesionesConsumidasBono() {
        return sesionesConsumidasBono;
    }

    public void setSesionesConsumidasBono(Double sesionesConsumidasBono) {
        this.sesionesConsumidasBono = sesionesConsumidasBono;
    }*/




}
