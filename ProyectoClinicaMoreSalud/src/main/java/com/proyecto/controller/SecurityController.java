package com.proyecto.controller;

import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("respuesta", "Usuario o contraseña incorrectos");
        }
        return "security/login";
    }
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "error/403";
    }


}
