package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Paciente;
import com.proyecto.repository.IPacienteRepository;

@Service
public class PacienteService {
	@Autowired
	private IPacienteRepository repository;
	
	public List<Paciente> listarPacientes(){
		return repository.findAll();
	}
	
	public Optional<Paciente> buscarPaciente(int id){
		return repository.findById(id);
	}
	
	public void eliminarPaciente(Paciente Paciente){
		repository.delete(Paciente);
	}
	
	public Paciente agregarPaciente(Paciente Paciente) {
		return repository.save(Paciente);
	}
	
	public Paciente actualizarPaciente(Paciente Paciente) {
		return repository.save(Paciente);
	}
}
