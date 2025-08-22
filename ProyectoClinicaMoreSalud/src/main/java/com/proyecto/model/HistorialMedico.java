package com.proyecto.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class HistorialMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
    private Paciente paciente;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date fecha;
	private String diagnostico;
	@OneToMany(mappedBy = "historialMedico")
	private List<TratamientoMedico> tratamientoMedicos;
	@OneToMany(mappedBy = "historialMedico")
	private List<Atencion> atenciones;
	@ManyToMany
	private List<Radiografia> radiografias;
	@ManyToMany
	private List<Alergia> alergias;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Atencion> getAtenciones() {
		return atenciones;
	}
	public void setAtenciones(List<Atencion> atenciones) {
		this.atenciones = atenciones;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public List<TratamientoMedico> getTratamientoMedicos() {
		return tratamientoMedicos;
	}
	public void setTratamientoMedicos(List<TratamientoMedico> tratamientoMedicos) {
		this.tratamientoMedicos = tratamientoMedicos;
	}
	public List<Radiografia> getRadiografias() {
		return radiografias;
	}
	public void setRadiografias(List<Radiografia> radiografias) {
		this.radiografias = radiografias;
	}
	public List<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<Alergia> alergias) {
		this.alergias = alergias;
	}

}
