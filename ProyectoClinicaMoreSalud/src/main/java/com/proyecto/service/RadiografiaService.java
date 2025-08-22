package com.proyecto.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.HistorialMedico;
import com.proyecto.model.Radiografia;
import com.proyecto.repository.IRadiografiaRepository;

@Service
public class RadiografiaService {
	@Autowired
	private IRadiografiaRepository repository;
	
	@Autowired
	private HistorialMedicoService historialMedicoService;
	
	
	public List<Radiografia> listarRadiografias(){
		return repository.findAll();
	}
	
	public Optional<Radiografia> buscarRadiografia(int id){
		return repository.findById(id);
	}
	
	public void eliminarRadiografia(Radiografia Radiografia){
		List<HistorialMedico> historialMedicos = historialMedicoService.listarHistorialMedicos();
		boolean cambio = false;

		for(HistorialMedico h : historialMedicos) {
		    cambio = false;
		    Iterator<Radiografia> iterador = h.getRadiografias().iterator();

		    while (iterador.hasNext()) {
		    	Radiografia r = iterador.next();
		        if(r.getId() == Radiografia.getId()) {
		            iterador.remove();
		            cambio = true;
		        }
		    }

		    if(cambio) {
		        historialMedicoService.actualizarHistorialMedico(h);
		    }
		}
		repository.delete(Radiografia);
	}
	
	public Radiografia agregarRadiografia(Radiografia Radiografia) {
		return repository.save(Radiografia);
	}
	
	public Radiografia actualizarRadiografia(Radiografia Radiografia) {
		return repository.save(Radiografia);
	}
}
