package promedicusdb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Login {
	private String token;
	private int nivelPermiso;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getNivelPermiso() {
		return nivelPermiso;
	}

	public void setNivelPermiso(int nivelPermiso) {
		this.nivelPermiso = nivelPermiso;
	}

	

}
