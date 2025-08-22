package com.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.HistorialMedico;
import com.proyecto.service.AlergiaService;
import com.proyecto.service.HistorialMedicoService;
import com.proyecto.service.PacienteService;

@Controller
@RequestMapping("/historialMedico")
public class HistorialMedicoController {
	@Autowired
	private HistorialMedicoService historialMedicoService;
	@Autowired
	private PacienteService pacienteService;
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		return "HistorialMedico/listaHistorialMedico";
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("historialMedico", historialMedicoService.buscarHistorialMedico(id).get());
		return "HistorialMedico/informacionHistorialMedico";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("historialMedico", new HistorialMedico());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "HistorialMedico/agregarHistorialMedico";
	}
	@PostMapping("/agregar")
	public String AgregarHistorialMedico(Model model, HistorialMedico HistorialMedico) {
		try {
			HistorialMedico historialMedicoA =  historialMedicoService.agregarHistorialMedico(HistorialMedico);
			if(historialMedicoA == null) {
				throw new Exception();
			}
		}catch (Exception e) {
			model.addAttribute("historialMedico", HistorialMedico);
			model.addAttribute("error", "Error al agregar Historial Medico");
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			return "HistorialMedico/agregarHistorialMedico";
		}
		return "redirect:listar";
	}
	@GetMapping("/actualizar")
	public String getActualizar(Model model, int id) {
		Optional<HistorialMedico> historialMedico = historialMedicoService.buscarHistorialMedico(id);
		model.addAttribute("historialMedico", historialMedico.get());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "HistorialMedico/actualizarHistorialMedico";
	}
	@PostMapping("/actualizar")
	public String ActualizarHistorialMedico(Model model, HistorialMedico historialMedico) {
		try {
			HistorialMedico historialMedicoA =  historialMedicoService.actualizarHistorialMedico(historialMedico);
			 if (historialMedicoA == null) {
		            throw new Exception("el historial no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("historialMedico", historialMedico);
			model.addAttribute("id", historialMedico.getId());
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			model.addAttribute("error", "Error al actualizar Historial Medico");
			return "HistorialMedico/actualizarHistorialMedico";
		}
		return "redirect:listar";
		
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Optional<HistorialMedico> historialMedico = historialMedicoService.buscarHistorialMedico(id);
		 if(historialMedico.isPresent()) {
		        HistorialMedico hm = historialMedico.get();
		        historialMedicoService.eliminarHistorialMedico(hm);
		    }
		
		return "redirect:listar";
	}
}
