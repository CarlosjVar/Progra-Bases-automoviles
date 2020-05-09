package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.PersonaJDBC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.negocio.automoviles.models.Persona;

import java.util.List;

/**
 * Controlador para clientes
 */
@Controller
public class ClientesController {

    // Pagina principal de clientes
    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String clientes(Model model) {
        // TODO: Obtener Personas
        PersonaJDBC personaJDBC = new PersonaJDBC();
        personaJDBC.setDataSource(DatabaseSource.getDataSource());
        List<Persona> personas = personaJDBC.getPersonas();
        model.addAttribute("personas", personas);
        // TODO: Obtener Organizaciones
        return "clientes";
    }

}
