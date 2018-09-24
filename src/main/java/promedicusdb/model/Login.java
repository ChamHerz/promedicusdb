package promedicusdb.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login {
	private int idLogin;
	private String Nombre;

	/** Persistente como clave y valor generado por la base de datos */
	@Id
	@GeneratedValue
	public int getIdLogin() {
		return idLogin;
	}

	private void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	/** Persistente, un tipo basico (string) */
	@Basic
	@Column(name = "Nombre")
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
}
