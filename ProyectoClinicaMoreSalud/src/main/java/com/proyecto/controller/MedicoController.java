package com.proyecto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto.model.Medico;
import com.proyecto.service.MedicoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("medico", new Medico());
		return "Medico/agregarMedico";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Medico medico) {
		try {
			Medico medicoA = medicoService.agregarMedico(medico);
			 if (medicoA == null) {
		            throw new Exception("El medico no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("paciente", medico);
			model.addAttribute("error", "Error al agregar medico");
			return "Medico/agregarMedico";
		}
		return "redirect:/medico/listar";
	}
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaMedico", medicoService.listarMedicos());
		return "Medico/listaMedico";
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("medico", medicoService.buscarMedico(id));
		return "Medico/actualizarMedico";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, Medico medico) {
		try {
			Medico medicoA = medicoService.actualizarMedico(medico);
			 if (medicoA == null) {
		            throw new Exception("El medico no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("medico", medico);
			model.addAttribute("error", "Error al actualizar medico");
			return "Medico/actualizarMedico";
		}
		return "redirect:/medico/listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("medico", medicoService.buscarMedico(id).get());
		return "Medico/informacionMedico";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Medico medico = medicoService.buscarMedico(id).get();
		medicoService.eliminarMedico(medico);
		return "redirect:listar";
	}
	
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/MedicoReport", method = RequestMethod.GET)
	@ResponseBody
	public void PacienteReport(HttpServletResponse response) throws JRException, IOException, SQLException {
	    Connection connection = jdbcTemplate.getDataSource().getConnection();
	    InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/Medicos.jasper");
	    Map<String, Object> params = new HashMap<String, Object>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Medicos.pdf");
	    final OutputStream outputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	    connection.close();
	}
}
