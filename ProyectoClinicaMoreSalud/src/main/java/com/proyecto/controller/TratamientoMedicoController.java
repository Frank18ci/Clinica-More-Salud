package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.TratamientoMedico;
import com.proyecto.service.HistorialMedicoService;
import com.proyecto.service.TratamientoMedicoService;

@Controller
@RequestMapping("/tratamientoMedico")
public class TratamientoMedicoController {
	@Autowired
	private TratamientoMedicoService tratamientoMedicoService;
	@Autowired
	private HistorialMedicoService historialMedicoService;
	
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("tratamientoMedico", new TratamientoMedico());
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		return "TratamientoMedico/agregarTratamientoMedico";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, TratamientoMedico tratamientoMedico) {
		try {
			TratamientoMedico tratamientoMedicoA = tratamientoMedicoService.agregarTratamientoMedico(tratamientoMedico);
			 if (tratamientoMedicoA == null) {
		            throw new Exception("El tratamiento medico no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("tratamientoMedico", tratamientoMedico);
			model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
			model.addAttribute("error", "Error al agregar tratamiento medico");
			return "TratamientoMedico/agregarTratamientoMedico";
		}
		return "redirect:listar";
	}
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaTratamientoMedico", tratamientoMedicoService.listarTratamientoMedicos());
		return "TratamientoMedico/listaTratamientoMedico";
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("tratamientoMedico", tratamientoMedicoService.buscarTratamientoMedico(id).get());
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		return "TratamientoMedico/actualizarTratamientoMedico";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, TratamientoMedico tratamientoMedico) {
		try {
			TratamientoMedico tratamientoMedicoA = tratamientoMedicoService.actualizarTratamientoMedico(tratamientoMedico);
			 if (tratamientoMedicoA == null) {
		            throw new Exception("El tratamiento medico no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("tratamientoMedico", tratamientoMedico);
			model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
			model.addAttribute("error", "Error al actualizar tratamiento medico");
			return "TratamientoMedico/actualizarTratamientoMedico";
		}
		return "redirect:listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("tratamientoMedico", tratamientoMedicoService.buscarTratamientoMedico(id).get());
		return "TratamientoMedico/informacionTratamientoMedico";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		TratamientoMedico tratamientoMedico = tratamientoMedicoService.buscarTratamientoMedico(id).get();
		tratamientoMedicoService.eliminarTratamientoMedico(tratamientoMedico);
		return "redirect:listar";
	}
	
}
