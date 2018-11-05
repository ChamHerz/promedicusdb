package promedicusdb.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "configuracion")
public class Configuracion {
	private String idVariable;
	private int valor;
	
	@Id
	@Column(name = "idVariable")
	public String getIdVariable() {
		return idVariable;
	}
	public void setIdVariable(String idVariable) {
		this.idVariable = idVariable;
	}
	
	@Basic
	@Column(name = "valor")
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
}
