package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.ClientJDBC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para ordenes
 */
@Controller
public class OrdenesController {
    /**
     * Agregar una orde
     * @return Carga el formulario para agregar una orden
     */
    @RequestMapping(value = "/ordenes/add", method = RequestMethod.GET)
    public String addOrden() {
        return "addorden";
    }

    @RequestMapping(value="/ordenes/add", method = RequestMethod.POST)
    public String procesarAddOrden(@RequestParam int cedula, @RequestParam String fecha, RedirectAttributes redirectAttributes) {

        // TODO: Redirigir a la pagina principal de ordenes
        return "redirect:/";
    }
}
