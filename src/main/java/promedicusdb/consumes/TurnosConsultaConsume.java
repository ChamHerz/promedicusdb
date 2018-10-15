package promedicusdb.consumes;

import java.util.Date;

public class TurnosConsultaConsume {
	private Date fechaDesde;
	private Date fechaHasta;
	private int especialidad;
	private int medico;
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public int getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(int especialidad) {
		this.especialidad = especialidad;
	}
	public int getMedico() {
		return medico;
	}
	public void setMedico(int medico) {
		this.medico = medico;
	}
}
