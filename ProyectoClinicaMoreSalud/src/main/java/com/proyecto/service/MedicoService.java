package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Medico;
import com.proyecto.repository.IMedicoRepository;

@Service
public class MedicoService {
	@Autowired
	private IMedicoRepository repository;
	
	public List<Medico> listarMedicos(){
		return repository.findAll();
	}
	
	public Optional<Medico> buscarMedico(int id){
		return repository.findById(id);
	}
	
	public void eliminarMedico(Medico Medico){
		repository.delete(Medico);
	}
	
	public Medico agregarMedico(Medico Medico) {
		return repository.save(Medico);
	}
	
	public Medico actualizarMedico(Medico Medico) {
		return repository.save(Medico);
	}
}
