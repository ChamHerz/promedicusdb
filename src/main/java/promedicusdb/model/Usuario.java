package promedicusdb.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "usuarios")
public class Usuario {
	private String Email;
	private String Password;
	private Integer NivelPermiso;
	private Boolean Activo;
	private Boolean EmailConfirm;
	private String PathReset;
	private String Nombre;
	private String Apellido;
	
	@Id
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Basic
	@Column(name = "Password")
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	@Basic
	@Column(name = "NivelPermiso")
	public Integer getNivelPermiso() {
		return NivelPermiso;
	}
	public void setNivelPermiso(Integer nivelPermiso) {
		NivelPermiso = nivelPermiso;
	}
	
	@Basic
	@Column(name = "Activo")
	public Boolean getActivo() {
		return Activo;
	}
	public void setActivo(Boolean activo) {
		Activo = activo;
	}
	
	@Basic
	@Column(name = "EmailConfirm")
	public Boolean getEmailConfirm() {
		return EmailConfirm;
	}
	public void setEmailConfirm(Boolean emailConfirm) {
		EmailConfirm = emailConfirm;
	}
	
	@Basic
	@Column(name = "PathReset")
	public String getPathReset() {
		return PathReset;
	}
	public void setPathReset(String pathReset) {
		PathReset = pathReset;
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
}
