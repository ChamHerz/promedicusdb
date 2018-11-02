package promedicusdb.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "turnos")
public class Turno {
	private int idTurno;
	private int nroLegajo;
	private Medico medico;
	private int idAgendaTurno;
	private int duracion;
	private Date fechaHora;
	//private Time hora;
	private int estadoTurno;
	//private EstadoTurno estadoTurno;
	private Integer idPaciente;
	//private Paciente paciente;
	private int idEspecialidad;
	private Especialidad especialidad;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_turno")
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	
	@Basic
	@Column(name = "NroLegajo")
	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	
	@ManyToOne(targetEntity = Medico.class)
    @JoinColumn(name = "NroLegajo",referencedColumnName="NroLegajo",insertable = false, updatable = false)
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@Basic
	@Column(name = "ID_Agendas_Turnos")
	public int getIdAgendaTurno() {
		return idAgendaTurno;
	}
	public void setIdAgendaTurno(int idAgendaTurno) {
		this.idAgendaTurno = idAgendaTurno;
	}
	
	@Basic
	@Column(name = "DuracionTurno")
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	@Basic
	@Column(name = "FechaHora")
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
//	@Basic
//	@Column(name = "Hora")
//	public Time getHora() {
//		return hora;
//	}
//	public void setHora(Time hora) {
//		this.hora = hora;
//	}
	
//	@Basic
//	@Column(name = "EstadoTurno")
//	public EstadoTurno getEstadoTurno() {
//		return estadoTurno;
//	}
//	public void setEstadoTurno(EstadoTurno estadoTurno) {
//		this.estadoTurno = estadoTurno;
//	}
	@Basic
	@Column(name = "DNI_Paciente")
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	
//	@ManyToOne(targetEntity = Paciente.class)
//    @JoinColumn(name = "DNI_Paciente",referencedColumnName="Dni")
//	public Paciente getPaciente() {
//		return paciente;
//	}
//	public void setPaciente(Paciente paciente) {
//		this.paciente = paciente;
//	}
	
	
	
	@Basic
	@Column(name = "EstadoTurno")
	public int getEstadoTurno() {
		return estadoTurno;
	}
	public void setEstadoTurno(int estadoTurno) {
		this.estadoTurno = estadoTurno;
	}

	@Basic
	@Column(name = "ID_Especialidad")
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	@ManyToOne(targetEntity = Especialidad.class)
    @JoinColumn(name = "ID_Especialidad",referencedColumnName="ID_Especialidad",insertable = false, updatable = false)
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	
}
