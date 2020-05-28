package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.OrdenJDBC;
import com.negocio.automoviles.models.Orden;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class OrdenesController {
    /**
     * Carga la página de ordenes
     * @param model
     * @return
     */
    @RequestMapping(value = "/ordenes",method = RequestMethod.GET)
    public String ordenes(Model model)
    {
        OrdenJDBC ordenJDBC= new OrdenJDBC();
        ordenJDBC.setDataSource(DatabaseSource.getDataSource());
        List<Orden> ordenes= ordenJDBC.getOrdenes();
        model.addAttribute("ordenes",ordenes);
        return "ordenes";
    }
    @RequestMapping(value= "/ordenes/{consecutivo}",method = RequestMethod.GET)
    public String detallesOrden(@PathVariable(value = "consecutivo") int consecutivo, Model model)
    {

        return "detallesOrden" ;
    }
}
