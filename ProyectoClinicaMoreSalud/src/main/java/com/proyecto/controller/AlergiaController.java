package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Alergia;
import com.proyecto.model.HistorialMedico;
import com.proyecto.service.AlergiaService;
import com.proyecto.service.HistorialMedicoService;

@Controller
@RequestMapping("/alergia")
public class AlergiaController {
	@Autowired
	private AlergiaService alergiaService;
	
	@Autowired
	private HistorialMedicoService historialMedicoService;
	
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("alergia", new Alergia());
		return "Alergia/agregarAlergia";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Alergia alergia) {
		try {
			Alergia alergiaA = alergiaService.agregarAlergia(alergia);
			 if (alergiaA == null) {
		            throw new Exception("La alergia no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("alergia", alergia);
			model.addAttribute("error", "Error al agregar alergia");
			return "Alergia/agregarAlergia";
		}
		return "redirect:listar";
	}
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaAlergia", alergiaService.listarAlergias());
		return "Alergia/listaAlergia";
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("alergia", alergiaService.buscarAlergia(id).get());
		return "Alergia/actualizarAlergia";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, Alergia alergia) {
		try {
			Alergia alergiaA = alergiaService.actualizarAlergia(alergia);
			 if (alergiaA == null) {
		            throw new Exception("La alergia no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("alergia", alergia);
			model.addAttribute("error", "Error al actualizar alergia");
			return "Alergia/actualizarAlergia";
		}
		return "redirect:listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("alergia", alergiaService.buscarAlergia(id).get());
		return "Alergia/informacionAlergia";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Alergia alergia = alergiaService.buscarAlergia(id).get();
		alergiaService.eliminarAlergia(alergia);
		return "redirect:listar";
	}
	 
	@GetMapping("/asignar")
	public String getAsignar(Model model, HistorialMedico historialMedico) {
		model.addAttribute("historialMedico", new HistorialMedico());
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		model.addAttribute("listaAlergias", alergiaService.listarAlergias());
		return "Alergia/asignarAlergia";
	}
	@PostMapping("/asignar")
	public String asignar(Model model, HistorialMedico historialMedico) {
		HistorialMedico historialMedico2 = historialMedicoService.buscarHistorialMedico(historialMedico.getId()).get();
		historialMedico2.setAlergias(historialMedico.getAlergias());
		historialMedicoService.actualizarHistorialMedico(historialMedico2);
		
		return "redirect:listar";
	}
}
