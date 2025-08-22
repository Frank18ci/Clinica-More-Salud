package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Atencion;
import com.proyecto.service.AtencionService;
import com.proyecto.service.MedicoService;
import com.proyecto.service.PacienteService;

@Controller
@RequestMapping("/atencion")
public class AtencionController {
	@Autowired
	private AtencionService atencionService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaAtencion", atencionService.listarAtenciones());
		return "atencion/listaAtencion";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("atencion", new Atencion());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		model.addAttribute("listaMedico", medicoService.listarMedicos());
		return "atencion/agregarAtencion";
	}
	@PostMapping("/agregar")
	public String AgregarAtencion(Model model, Atencion Atencion) {
		try {
			Atencion AtencionA =  atencionService.agregarAtencion(Atencion);
			if(AtencionA == null) {
				throw new Exception();
			}
		}catch (Exception e) {
			model.addAttribute("atencion", Atencion);
			model.addAttribute("error", "Error al agregar Atencion");
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			model.addAttribute("listaMedico", medicoService.listarMedicos());
			return "atencion/agregarAtencion";
		}
		return "redirect:/atencion/listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("atencion", atencionService.buscarAtencion(id).get());
		return "Atencion/informacionAtencion";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Atencion atencion = atencionService.buscarAtencion(id).get();
		atencionService.eliminarAtencion(atencion);
		return "redirect:listar";
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("atencion", atencionService.buscarAtencion(id).get());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		model.addAttribute("listaMedico", medicoService.listarMedicos());
		return "Atencion/actualizarAtencion";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, Atencion atencion) {
		try {
			Atencion atencionA = atencionService.actualizarAtencion(atencion);
			 if (atencionA == null) {
		            throw new Exception("La atencion no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("atencion", atencion);
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			model.addAttribute("listaMedico", medicoService.listarMedicos());
			model.addAttribute("error", "Error al actualizar atencion");
			return "Atencion/actualizarAtencion";
		}
		return "redirect:listar";
		
	}
	
}
