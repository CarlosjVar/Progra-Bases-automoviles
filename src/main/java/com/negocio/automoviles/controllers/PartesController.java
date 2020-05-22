package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.PartesJDBC;
import com.negocio.automoviles.models.Parte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controlador para partes
 *
 */
@Controller
public class PartesController 
{

    /**
     * Carga la pagina principal para partes
     * @param model El modelo para cargar datos
     * @return La pagina principal de partes
     */
    @RequestMapping(value = "/partes", method = RequestMethod.GET)
    public String partes(Model model) {
        PartesJDBC partesJDBC = new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        // Obtener las partes
        List<Parte> partes = partesJDBC.getPartes();
        model.addAttribute("partes", partes);
        return "partes";
    }

    /**
     * Carga la pagina para los detalles de una parte
     * @param model El modelo para cargar datos
     * @param id El id de la parte
     * @return La pagina de detalles para la parte
     */
    @RequestMapping(value = "/partes/{id}", method = RequestMethod.GET)
    public String detallesParte(Model model, @PathVariable(value = "id") int id) {
        PartesJDBC partesJDBC = new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        // Obtener parte
        Parte parte = partesJDBC.getParte(id);
        model.addAttribute("parte", parte);
        return "detallesparte";
    }
 
    @RequestMapping(value = "/partes/add", method = RequestMethod.GET)
    public String AddParte(Model model)
    {
        if (!model.containsAttribute("parte")) {
        model.addAttribute("parte", new Parte());
    }
        return "addparte";
    }

}
