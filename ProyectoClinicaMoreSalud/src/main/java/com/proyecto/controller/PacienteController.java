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

import com.proyecto.model.Paciente;
import com.proyecto.service.PacienteService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping({"/listar", "/"})
	public String getList(Model model) {
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "Paciente/listaPaciente";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "Paciente/agregarPaciente";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Paciente paciente) {
		try {
			Paciente pacienteA = pacienteService.agregarPaciente(paciente);
			 if (pacienteA == null) {
		            throw new Exception("El paciente no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("paciente", paciente);
			model.addAttribute("error", "Error al agregar paciente");
			return "Paciente/agregarPaciente";
		}
		return "redirect:/paciente/listar";
		
	}
	@GetMapping("/actualizar")
	public String getEditar(Model model, int id) {
		model.addAttribute("paciente", pacienteService.buscarPaciente(id));
		return "Paciente/editarPaciente";
	}
	@PostMapping("/actualizar")
	public String EditarPaciente(Model model, Paciente paciente) {
		try {
			Paciente pacienteA = pacienteService.actualizarPaciente(paciente);
			 if (pacienteA == null) {
		            throw new Exception("El paciente no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("paciente", paciente);
			model.addAttribute("error", "Error al actualizar paciente");
			return "Paciente/editarPaciente";
		}
		return "redirect:paciente/listar";
		
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("paciente", pacienteService.buscarPaciente(id).get());
		return "paciente/informacionPaciente";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Paciente paciente = pacienteService.buscarPaciente(id).get();
		pacienteService.eliminarPaciente(paciente);
		return "redirect:listar";
	}
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/PacienteReport", method = RequestMethod.GET)
	@ResponseBody
	public void PacienteReport(HttpServletResponse response) throws JRException, IOException, SQLException {
	    Connection connection = jdbcTemplate.getDataSource().getConnection();
	    InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/Pacientes.jasper");
	    Map<String, Object> params = new HashMap<String, Object>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Pacientes.pdf");
	    final OutputStream outputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	    connection.close();
	}
	

}
