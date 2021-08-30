package es.emretuerto.solgestion.modelo;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name = "USUARIOS")

public class Usuario {
	
    private static final long serialVersionUID = 1L;
    

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CODIGO_USUARIO", nullable = false, unique = true)
	private String codigoCliente;
	
	@Column(name = "PASSWORD_USUARIO", nullable = false, unique = true)
	private String passwordUsuario;


	@Column(name = "ROL_USUARIO",  nullable = false)
	@Enumerated(EnumType.STRING)
	private Rol rol;


	public Usuario() {
	}


	public Usuario(Integer id, String codigoCliente, String passwordUsuario, Rol rol) {
		super();
		this.id = id;
		this.codigoCliente = codigoCliente;
		this.passwordUsuario = passwordUsuario;
		this.rol = rol;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodigoCliente() {
		return codigoCliente;
	}


	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	public String getPasswordUsuario() {
		return passwordUsuario;
	}


	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((passwordUsuario == null) ? 0 : passwordUsuario.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (passwordUsuario == null) {
			if (other.passwordUsuario != null)
				return false;
		} else if (!passwordUsuario.equals(other.passwordUsuario))
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
	
	
	
	

}
