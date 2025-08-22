package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Atencion;
import com.proyecto.model.Paciente;
import com.proyecto.repository.IAtencionRepository;

@Service
public class AtencionService {
	@Autowired
	private IAtencionRepository repository;
	@Autowired 
	private PacienteService pacienteService;
	
	public List<Atencion> listarAtenciones(){
		return repository.findAll();
	}
	
	public Optional<Atencion> buscarAtencion(int id){
		return repository.findById(id);
	}
	
	public void eliminarAtencion(Atencion Atencion){
		repository.delete(Atencion);
	}
	
	public Atencion agregarAtencion(Atencion Atencion) {
		Paciente p = pacienteService.buscarPaciente(Atencion.getPaciente().getId()).get();
		Atencion.setHistorialMedico(p.getHistorialMedico());
		return repository.save(Atencion);
	}
	
	public Atencion actualizarAtencion(Atencion Atencion) {
		Paciente p = pacienteService.buscarPaciente(Atencion.getPaciente().getId()).get();
		Atencion.setHistorialMedico(p.getHistorialMedico());
		return repository.save(Atencion);
	}
}
