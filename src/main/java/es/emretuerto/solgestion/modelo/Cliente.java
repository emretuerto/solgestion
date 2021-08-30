package es.emretuerto.solgestion.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name = "CLIENTES")

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

  //  @Column(name = "CODIGO_CLIENTE", length = 10, nullable = false, unique = true)
  //  private String codigoCliente;

    @NotBlank
    @Pattern(regexp = "[0-9]{0,20}", message = "Error código de barras!!")
    @Column(name = "CODIGO_BARRAS", length = 20, unique = true, nullable = false)
    private String codigoBarras;

    @NotBlank
    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "APELLIDOS")
    private String apellidos;

    @NotBlank
    @Pattern(regexp = "[0-9]{6,8}[A-Z,a-z]{1}", message = "¡DNI no válido!")
    @Column(name = "NIF", length = 10, unique = true, nullable = false)
    private String nif;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CODIGO_POSTAL", length = 5)
    private String codigoPostal;

    @Column(name = "LOCALIDAD", length = 30)
    private String localidad;

    @Column(name = "PROVINCIA", length = 30)
    private String provincia;

    @Column(name = "FECHA_NACIMIENTO", columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Pattern(regexp = "[0-9]{0,9}", message = "Error en el número de teléfono")
    @Column(name = "TELEFONO_FIJO", length = 9)
    private String telefonoFijo;

    @Pattern(regexp = "[0-9]{0,9}", message = "Error en el número de teléfono")
    @Column(name = "TELEFONO_MOVIL", length = 9)
    private String telefonoMovil;
    
    //@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "E-mail no válido")
    @Email
    @Column(name = "EMAIL")
    private String email;

/*    @ManyToOne
    @JoinColumn(name = "FOTOTIPO_ID")
    @Cascade(CascadeType.ALL)
    private Fototipo fototipo;
*/
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @Column(name = "SESIONES_CLIENTES")
    private List<Sesion> sesionesCliente = new ArrayList<Sesion>();

  /*  @ManyToOne
    @JoinColumn(name = "TIPO_CLIENTE_ID", nullable = false)
    @Cascade(CascadeType.ALL)
    private TipoCliente tipoCliente;
*/
    @ManyToOne
    @JoinColumn(name = "BONO_ID")
    @Cascade(CascadeType.ALL)
    private Bono bono;
    
    private boolean activo;

    public Cliente() {
    }

    

    public Cliente(String codigoBarras, String nombre, String apellidos, String nif, String direccion,
			String codigoPostal, String localidad, String provincia, LocalDate fechaNacimiento, String telefonoFijo,
			String telefonoMovil, String email,  Bono bono, boolean activo) {
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
		this.fechaNacimiento = fechaNacimiento;
		this.telefonoFijo = telefonoFijo;
		this.telefonoMovil = telefonoMovil;
		this.email = email;
		this.bono = bono;
		this.activo = activo;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  /*  public Fototipo getFototipo() {
        return fototipo;
    }

    public void setFototipo(Fototipo fototipo) {
        this.fototipo = fototipo;
    }
*/
    public List<Sesion> getSesionesCliente() {
        return sesionesCliente;
    }

    public void setSesionesCliente(List<Sesion> sesionesCliente) {
        this.sesionesCliente = sesionesCliente;
        sesionesCliente.forEach((s) -> {
            s.setCliente(this);
        });
    }

   /* public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }*/

    public Bono getBono() {
        return bono;
    }

    public void setBono(Bono bono) {
        this.bono = bono;
    }

    public void addSesion(Sesion sesion) {
        if (!sesionesCliente.contains(sesion)) {
            sesionesCliente.add(sesion);
            sesion.setCliente(this);
        }
    }

    public void removeSesion(Sesion sesion) {
        if (sesionesCliente.contains(sesion)) {
            sesionesCliente.remove(sesion);
            sesion.setCliente(null);
        }
    }
    
    

    public boolean isActivo() {
		return activo;
	}



	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	@Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nif=" + nif + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", localidad=" + localidad + ", provincia=" + provincia + ", fechaNacimiento=" + fechaNacimiento + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", email=" + email + ", sesionesCliente=" + sesionesCliente  + '}';
    }

}
