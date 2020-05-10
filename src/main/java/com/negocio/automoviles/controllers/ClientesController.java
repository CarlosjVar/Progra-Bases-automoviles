package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.ClientJDBC;
import com.negocio.automoviles.jdbc.PersonaJDBC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.negocio.automoviles.models.Persona;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para clientes
 */
@Controller
public class ClientesController {

    // Pagina principal de clientes
    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String clientes(Model model) {
        PersonaJDBC personaJDBC = new PersonaJDBC();
        personaJDBC.setDataSource(DatabaseSource.getDataSource());
        List<Persona> personas = personaJDBC.getPersonas();
        model.addAttribute("personas", personas);
        // TODO: Obtener Organizaciones
        return "clientes";
    }

    // Formulario para agregar clientes
    @RequestMapping(value="/clientes/personas/add", method = RequestMethod.GET)
    public String agregarPersona(Model model) {
        if (!model.containsAttribute("persona")) {
            model.addAttribute("persona", new Persona());
        }
        return "addpersona";
    }

    @RequestMapping(value = "/clientes/personas/add", method = RequestMethod.POST)
    public String procesarAgregarPersona(@ModelAttribute Persona persona, RedirectAttributes redirectAttributes) {
        ArrayList<String> errores = new ArrayList<String>();
        // Crear acceso a las personas
        PersonaJDBC personaJDBC = new PersonaJDBC();
        personaJDBC.setDataSource(DatabaseSource.getDataSource());
        // Revisar cedula
        if (persona.getCedula() > 999999999 || persona.getCedula() < 100000000
                || personaJDBC.existeCedula(persona.getCedula())) {
            errores.add("Cedula invalida");
        }
        // Revisar nombre
        if (persona.getNombre().equals("") || persona.getNombre().length() > 30) {
            errores.add("Nombre invalido");
        }
        // Revisar direccion
        if (persona.getDireccion().equals("") || persona.getDireccion().length() > 30) {
            errores.add("Direccion invalida");
        }
        // Revisar ciudad
        if (persona.getCiudad().equals("") || persona.getCiudad().length() > 15) {
            errores.add("Ciudad invalida");
        }
        // Hay errores
        if (errores.size() != 0) {
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("persona", persona);
            return "redirect:/clientes/personas/add";
        }
        // Insertar en la tabla de clientes
        personaJDBC.agregarPersona(persona);
        redirectAttributes.addFlashAttribute("success_msg", "Persona agregada");
        return "redirect:/clientes";
    }

    @RequestMapping(value = "/clientes/personas/{cedula}", method = RequestMethod.GET)
    public String getPersona(@PathVariable(value = "cedula") int cedula, Model model) {
        PersonaJDBC personaJDBC = new PersonaJDBC();
        personaJDBC.setDataSource(DatabaseSource.getDataSource());
        Persona persona = personaJDBC.getPersona(cedula);
        model.addAttribute("persona", persona);
        return "detallespersona";
    }

    @RequestMapping(value="/clientes/personas/{cedula}/modificar", method = RequestMethod.GET)
    public String modificarPersona(@PathVariable(value = "cedula") int cedula, Model model) {
        // Obtener persona
        if (!model.containsAttribute("persona")) {
            PersonaJDBC personaJDBC = new PersonaJDBC();
            personaJDBC.setDataSource(DatabaseSource.getDataSource());
            Persona persona = personaJDBC.getPersona(cedula);
            model.addAttribute("persona", persona);
        }
        return "modificarpersona";
    }

    @RequestMapping(value="/clientes/personas/{cedula}/modificar", method = RequestMethod.POST)
    public String procesarModificarPersona(@ModelAttribute Persona persona, @PathVariable(value = "cedula") int cedula, RedirectAttributes redirectAttributes) {
        ArrayList<String> errores = new ArrayList<String>();
        if (persona.getNombre().equals("") || persona.getNombre().length() > 30) {
            errores.add("Nombre invalido");
        }
        if (persona.getDireccion().equals("") || persona.getDireccion().length() > 30) {
            errores.add("Direccion invalida");
        }
        if (persona.getCiudad().equals("") || persona.getCiudad().length() > 15) {
            errores.add("Ciudad invalida");
        }
        // Hay errores
        if (errores.size() != 0) {
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("persona", persona);
            return "redirect:/clientes/personas/" + persona.getCedula() + "/modificar";
        }
        // Obtener id
        PersonaJDBC personaJDBC = new PersonaJDBC();
        personaJDBC.setDataSource(DatabaseSource.getDataSource());
        int idPersona = personaJDBC.getPersona(persona.getCedula()).getId();
        String estadoPersona = personaJDBC.getPersona(persona.getCedula()).getEstado();
        // Modificar persona
        ClientJDBC clientJDBC = new ClientJDBC();
        clientJDBC.setDataSource(DatabaseSource.getDataSource());
        clientJDBC.modificarCliente(idPersona, persona.getNombre(), persona.getDireccion(), persona.getCiudad(), estadoPersona);
        redirectAttributes.addFlashAttribute("success_msg", "Persona modificada");
        return "redirect:/clientes/personas/" + persona.getCedula() + "?id=" + persona.getId();
    }
}
