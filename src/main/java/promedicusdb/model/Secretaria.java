package promedicusdb.model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "secretarias")
public class Secretaria {
	private int nroLegajo;
	private String dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String email;
	private Date fechaIngreso;
	
	@Id
	@Column(name = "NroLegajo")
	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	
	@Basic
	@Column(name = "DNI")
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Basic
	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Basic
	@Column(name = "Apellido")
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Basic
	@Column(name = "Direccion")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Basic
	@Column(name = "Telefono")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Basic
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Basic
	@Column(name = "FechaIngreso")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
		
}
