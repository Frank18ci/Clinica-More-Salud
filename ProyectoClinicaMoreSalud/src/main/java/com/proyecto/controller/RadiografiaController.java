package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.HistorialMedico;
import com.proyecto.model.Radiografia;
import com.proyecto.service.HistorialMedicoService;
import com.proyecto.service.RadiografiaService;

@Controller
@RequestMapping("/radiografia")
public class RadiografiaController {
	@Autowired
	private RadiografiaService radiografiaService;
	@Autowired
	private HistorialMedicoService historialMedicoService;
	
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("radiografia", new Radiografia());
		return "Radiografia/agregarRadiografia";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Radiografia radiografia) {
		try {
			Radiografia radiografiaA = radiografiaService.agregarRadiografia(radiografia);
			 if (radiografiaA == null) {
		            throw new Exception("La radiografia no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("radiografia", radiografia);
			model.addAttribute("error", "Error al agregar radiografia");
			return "Radiografia/agregarRadiografia";
		}
		return "redirect:listar";
	}
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaRadiografia", radiografiaService.listarRadiografias());
		return "Radiografia/listaRadiografia";
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("radiografia", radiografiaService.buscarRadiografia(id).get());
		return "Radiografia/actualizarRadiografia";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, Radiografia radiografia) {
		try {
			Radiografia radiografiaA = radiografiaService.actualizarRadiografia(radiografia);
			 if (radiografiaA == null) {
		            throw new Exception("La radiografia no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("radiografia", radiografia);
			model.addAttribute("error", "Error al actualizar radiografia");
			return "Radiografia/actualizarRadiografia";
		}
		return "redirect:listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("radiografia", radiografiaService.buscarRadiografia(id).get());
		return "Radiografia/informacionRadiografia";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Radiografia radiografia= radiografiaService.buscarRadiografia(id).get();
		radiografiaService.eliminarRadiografia(radiografia);
		return "redirect:listar";
	}
	
	@GetMapping("/asignar")
	public String getAsignar(Model model, HistorialMedico historialMedico) {
		model.addAttribute("historialMedico", new HistorialMedico());
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		model.addAttribute("listaRadiografia", radiografiaService.listarRadiografias());
		return "Radiografia/asignarRadiografia";
	}
	@PostMapping("/asignar")
	public String asignar(Model model, HistorialMedico historialMedico) {
		HistorialMedico historialMedico2 = historialMedicoService.buscarHistorialMedico(historialMedico.getId()).get();
		historialMedico2.setRadiografias(historialMedico.getRadiografias());
		historialMedicoService.actualizarHistorialMedico(historialMedico2);
		return "redirect:listar";
	}
	
}
