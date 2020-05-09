package com.negocio.automoviles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.negocio.automoviles.models.Persona;

/**
 * Controlador para clientes
 */
@Controller
public class ClientesController {

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String clientes(Model model) {
        // TODO: Obtener Personas
        // TODO: Obtener Organizaciones
        return "clientes";
    }

}
