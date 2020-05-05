package com.negocio.automoviles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Controlador para paginas principales
 */
@Controller
public class PagesController {
    /**
     * Carga la pagina de inicio
     * @param model Modelo para renderizar datos
     * @return El template que se desea cargar
     */
    public String index(Model model) {
        model.addAttribute("message", "Hello World");
        return "index";
    }
}
