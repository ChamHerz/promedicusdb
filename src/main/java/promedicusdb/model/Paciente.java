package promedicusdb.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "pacientes")
public class Paciente {
	private String Dni;
	private String Nombre;
	private String Apellido;
	private String Direccion;
	private String Telefono;
	private String Email;
	private Integer idObraSocial;
	
	@Id
	@Column(name = "Dni")
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	
	@Basic
	@Column(name = "Nombre")
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	@Basic
	@Column(name = "Apellido")
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
	@Basic
	@Column(name = "Direccion")
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	@Basic
	@Column(name = "Telefono")
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	@Basic
	@Column(name = "Email")
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Basic
	@Column(name = "IdObraSocial")
	public Integer getIdObraSocial() {
		return idObraSocial;
	}
	public void setIdObraSocial(Integer idObraSocial) {
		this.idObraSocial = idObraSocial;
	}
}
