package com.proyecto.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Alergia;
import com.proyecto.model.HistorialMedico;
import com.proyecto.repository.IAlergiaRepository;

@Service
public class AlergiaService {
	@Autowired
	private IAlergiaRepository repository;
	@Autowired
	private HistorialMedicoService historialMedicoService;
	
	public List<Alergia> listarAlergias(){
		return repository.findAll();
	}
	public Optional<Alergia> buscarAlergia(int id){
		return repository.findById(id);
	}
	
	public void eliminarAlergia(Alergia alergia){
		List<HistorialMedico> historialMedicos = historialMedicoService.listarHistorialMedicos();
		boolean cambio = false;

		for(HistorialMedico h : historialMedicos) {
		    cambio = false;
		    Iterator<Alergia> iterador = h.getAlergias().iterator();

		    while (iterador.hasNext()) {
		        Alergia a = iterador.next();
		        if(a.getId() == alergia.getId()) {
		            iterador.remove();
		            cambio = true;
		        }
		    }

		    if(cambio) {
		        historialMedicoService.actualizarHistorialMedico(h);
		    }
		}
		repository.delete(alergia);

	}
	
	public Alergia agregarAlergia(Alergia alergia) {
		return repository.save(alergia);
	}
	
	public Alergia actualizarAlergia(Alergia alergia) {
		return repository.save(alergia);
	}
}
