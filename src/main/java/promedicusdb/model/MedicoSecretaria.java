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
@Table(name = "medicos_secretarias")
public class MedicoSecretaria {
	private Integer idMedicoSecretaria;
	private Integer nroLegajoMedico;
	private Integer nroLegajoSecretaria;
	
	@Id
	@Column(name = "ID_Medicos_Secretarias")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdMedicoSecretaria() {
		return idMedicoSecretaria;
	}
	public void setIdMedicoSecretaria(Integer idMedicoSecretaria) {
		this.idMedicoSecretaria = idMedicoSecretaria;
	}
	
	@Basic
	@Column(name = "NroLegajo_Medico")
	public Integer getNroLegajoMedico() {
		return nroLegajoMedico;
	}
	public void setNroLegajoMedico(Integer nroLegajoMedico) {
		this.nroLegajoMedico = nroLegajoMedico;
	}
	
	@Basic
	@Column(name = "NroLegajo_Secretaria")
	public Integer getNroLegajoSecretaria() {
		return nroLegajoSecretaria;
	}
	public void setNroLegajoSecretaria(Integer nroLegajoSecretaria) {
		this.nroLegajoSecretaria = nroLegajoSecretaria;
	}
	
}
