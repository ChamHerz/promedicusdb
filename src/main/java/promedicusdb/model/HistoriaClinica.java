package promedicusdb.model;

import java.sql.Date;

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
@Table(name = "historia_clinica")
public class HistoriaClinica {
	private Integer idHistoriaClinica;
	private String dni;
	private String nacionalidad;
	private String ocupacion;
	private Date fechaNacimiento;
	private String instruccion;
	private String religion;
	private String residencia;
	private String residenciaAnterior;
	private Integer padres;
	private Integer padresVivos;
	private Integer padresFallecidos;
	private String padresCausas;
	private Integer hermanos;
	private Integer hermanosVivos;
	private Integer hermanosFallecidos;
	private String hermanosCausas;
	private Integer hijos;
	private Integer hijosVivos;
	private Integer hijosFallecidos;
	private String hijosCausas;
	private Integer dbt;
	private Integer hta;
	private Integer tbc;
	private String gemelar;
	private String otrosHeredo;
	private String alcohol;
	private String tabaco;
	private String drogas;
	private String infusiones;
	private String infancia;
	private String adulto;
	private String quirurgicos;
	private String traumatologicos;
	private String alergicos;
	private String otrosPersonales;
	
	@Id
	@Column(name = "idHistoria_Clinica")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdHistoriaClinica() {
		return idHistoriaClinica;
	}
	public void setIdHistoriaClinica(Integer idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}
	
	@Basic
	@Column(name = "dni")
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Basic
	@Column(name = "nacionalidad")
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Basic
	@Column(name = "ocupacion")
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	@Basic
	@Column(name = "fechaNacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Basic
	@Column(name = "instruccion")
	public String getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	
	@Basic
	@Column(name = "religion")
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	@Basic
	@Column(name = "residencia")
	public String getResidencia() {
		return residencia;
	}
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	
	@Basic
	@Column(name = "residenciaAnterior")
	public String getResidenciaAnterior() {
		return residenciaAnterior;
	}
	public void setResidenciaAnterior(String residenciaAnterior) {
		this.residenciaAnterior = residenciaAnterior;
	}
	
	@Basic
	@Column(name = "padres")
	public Integer getPadres() {
		return padres;
	}
	public void setPadres(Integer padres) {
		this.padres = padres;
	}
	
	@Basic
	@Column(name = "padresVivos")
	public Integer getPadresVivos() {
		return padresVivos;
	}
	public void setPadresVivos(Integer padresVivos) {
		this.padresVivos = padresVivos;
	}
	
	@Basic
	@Column(name = "padresFallecidos")
	public Integer getPadresFallecidos() {
		return padresFallecidos;
	}
	public void setPadresFallecidos(Integer padresFallecidos) {
		this.padresFallecidos = padresFallecidos;
	}
	
	@Basic
	@Column(name = "padresCausas")
	public String getPadresCausas() {
		return padresCausas;
	}
	public void setPadresCausas(String padresCausas) {
		this.padresCausas = padresCausas;
	}
	
	@Basic
	@Column(name = "hermanos")
	public Integer getHermanos() {
		return hermanos;
	}
	public void setHermanos(Integer hermanos) {
		this.hermanos = hermanos;
	}
	
	@Basic
	@Column(name = "hermanosVivos")
	public Integer getHermanosVivos() {
		return hermanosVivos;
	}
	public void setHermanosVivos(Integer hermanosVivos) {
		this.hermanosVivos = hermanosVivos;
	}
	
	@Basic
	@Column(name = "hermanosFallecidos")
	public Integer getHermanosFallecidos() {
		return hermanosFallecidos;
	}
	public void setHermanosFallecidos(Integer hermanosFallecidos) {
		this.hermanosFallecidos = hermanosFallecidos;
	}
	
	@Basic
	@Column(name = "hermanosCausas")
	public String getHermanosCausas() {
		return hermanosCausas;
	}
	public void setHermanosCausas(String hermanosCausas) {
		this.hermanosCausas = hermanosCausas;
	}
	
	@Basic
	@Column(name = "hijos")
	public Integer getHijos() {
		return hijos;
	}
	public void setHijos(Integer hijos) {
		this.hijos = hijos;
	}
	
	@Basic
	@Column(name = "hijosVivos")
	public Integer getHijosVivos() {
		return hijosVivos;
	}
	public void setHijosVivos(Integer hijosVivos) {
		this.hijosVivos = hijosVivos;
	}
	
	@Basic
	@Column(name = "hijosFallecidos")
	public Integer getHijosFallecidos() {
		return hijosFallecidos;
	}
	public void setHijosFallecidos(Integer hijosFallecidos) {
		this.hijosFallecidos = hijosFallecidos;
	}
	
	@Basic
	@Column(name = "hijosCausas")
	public String getHijosCausas() {
		return hijosCausas;
	}
	public void setHijosCausas(String hijosCausas) {
		this.hijosCausas = hijosCausas;
	}
	
	@Basic
	@Column(name = "dbt")
	public Integer getDbt() {
		return dbt;
	}
	public void setDbt(Integer dbt) {
		this.dbt = dbt;
	}
	
	@Basic
	@Column(name = "hta")
	public Integer getHta() {
		return hta;
	}
	public void setHta(Integer hta) {
		this.hta = hta;
	}
	
	@Basic
	@Column(name = "tbc")
	public Integer getTbc() {
		return tbc;
	}
	public void setTbc(Integer tbc) {
		this.tbc = tbc;
	}
	
	@Basic
	@Column(name = "gemelar")
	public String getGemelar() {
		return gemelar;
	}
	public void setGemelar(String gemelar) {
		this.gemelar = gemelar;
	}
	
	@Basic
	@Column(name = "otrosHeredo")
	public String getOtrosHeredo() {
		return otrosHeredo;
	}
	public void setOtrosHeredo(String otrosHeredo) {
		this.otrosHeredo = otrosHeredo;
	}
	
	@Basic
	@Column(name = "alcohol")
	public String getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	
	@Basic
	@Column(name = "tabaco")
	public String getTabaco() {
		return tabaco;
	}
	public void setTabaco(String tabaco) {
		this.tabaco = tabaco;
	}
	
	@Basic
	@Column(name = "drogas")
	public String getDrogas() {
		return drogas;
	}
	public void setDrogas(String drogas) {
		this.drogas = drogas;
	}
	
	@Basic
	@Column(name = "infusiones")
	public String getInfusiones() {
		return infusiones;
	}
	public void setInfusiones(String infusiones) {
		this.infusiones = infusiones;
	}
	
	@Basic
	@Column(name = "infancia")
	public String getInfancia() {
		return infancia;
	}
	public void setInfancia(String infancia) {
		this.infancia = infancia;
	}
	
	@Basic
	@Column(name = "adulto")
	public String getAdulto() {
		return adulto;
	}
	public void setAdulto(String adulto) {
		this.adulto = adulto;
	}
	
	@Basic
	@Column(name = "quirurgicos")
	public String getQuirurgicos() {
		return quirurgicos;
	}
	public void setQuirurgicos(String quirurgicos) {
		this.quirurgicos = quirurgicos;
	}
	
	@Basic
	@Column(name = "traumatologicos")
	public String getTraumatologicos() {
		return traumatologicos;
	}
	public void setTraumatologicos(String traumatologicos) {
		this.traumatologicos = traumatologicos;
	}
	
	@Basic
	@Column(name = "alergicos")
	public String getAlergicos() {
		return alergicos;
	}
	public void setAlergicos(String alergicos) {
		this.alergicos = alergicos;
	}
	
	@Basic
	@Column(name = "otrosPersonales")
	public String getOtrosPersonales() {
		return otrosPersonales;
	}
	public void setOtrosPersonales(String otrosPersonales) {
		this.otrosPersonales = otrosPersonales;
	}
	
}
