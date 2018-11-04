package promedicusdb.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "medicos_obras_sociales")
public class MedicoObraSocial {
	private Integer idMedicoObraSocial;
	private int nroLegajo;
	private int idObraSocial;
	
	@Id
	@Column(name = "ID_medicos_obras_sociales")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdMedicoObraSocial() {
		return idMedicoObraSocial;
	}
	public void setIdMedicoObraSocial(Integer idMedicoObraSocial) {
		this.idMedicoObraSocial = idMedicoObraSocial;
	}
	
	@Basic
	@Column(name = "NroLegajo")
	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	
	@Basic
	@Column(name = "ID_ObraSocial")
	public int getIdObraSocial() {
		return idObraSocial;
	}
	public void setIdObraSocial(int idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	
}
