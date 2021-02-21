package es.emretuerto.solgestion.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class ClienteDTO {
    
    
    private String codigoCliente;
    private String nombre;
    private String apellidos;
    private String nif;
    private String direccion;
    private String codigoPostal;
    private String localidad;
    private String provincia;
    private Date fechaNacimiento;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private FototipoDTO fototipo;
    private List<SesionDTO> sesionesCliente  = new ArrayList<>();
    private TipoClienteDTO tipoCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(String codigoCliente, String nombre, String apellidos, String nif, String direccion, String codigoPostal, String localidad, String provincia, Date fechaNacimiento, String telefonoFijo, String telefonoMovil, String email, FototipoDTO fototipoDTO, List<SesionDTO> sesionesCliente, TipoClienteDTO tipoClienteDTO) {
        this.codigoCliente = codigoCliente;
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
        this.fototipo = fototipoDTO;
        this.sesionesCliente = sesionesCliente;
        this.tipoCliente = tipoClienteDTO;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public FototipoDTO getFototipoDTO() {
        return fototipo;
    }

    public void setFototipoDTO(FototipoDTO fototipoDTO) {
        this.fototipo = fototipoDTO;
    }

    public List<SesionDTO> getSesionesCliente() {
        return sesionesCliente;
    }

    public void setSesionesCliente(List<SesionDTO> sesionesCliente) {
        this.sesionesCliente = sesionesCliente;
    }

    public TipoClienteDTO getTipoClienteDTO() {
        return tipoCliente;
    }

    public void setTipoClienteDTO(TipoClienteDTO tipoClienteDTO) {
        this.tipoCliente = tipoClienteDTO;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "codigoCliente=" + codigoCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nif=" + nif + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", localidad=" + localidad + ", provincia=" + provincia + ", fechaNacimiento=" + fechaNacimiento + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", email=" + email + ", fototipoDTO=" + fototipo + ", sesionesCliente=" + sesionesCliente + ", tipoClienteDTO=" + tipoCliente + '}';
    }
    
    
    
}
