package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "TIPO_CLIENTES")
public class TipoCliente  implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CODIGO", length = 4, nullable = false, unique = true)
    @NotEmpty
    @Pattern(regexp = "[0-9]{4}", message = "Introduzca un código de 4 dígitos numericos")
    private String codigo;

    @Column(name = "DESCRIPCION", length = 60)
    @NotEmpty
    private String descripcion;
    
    @Column(name = "ACTIVO")
    private boolean activo;

//    @OneToMany(mappedBy = "id")
//    private List<Cliente> clientesTipo;
    
    
    public TipoCliente(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public TipoCliente() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
    public String toString() {
        return "TipoCliente{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }


    
    
}
