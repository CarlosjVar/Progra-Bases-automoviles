package com.negocio.automoviles.controllers;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.ClientJDBC;
import com.negocio.automoviles.jdbc.OrdenJDBC;
import com.negocio.automoviles.validators.OrdenValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.crypto.Data;
import java.util.ArrayList;

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

    /**
     * Procesa una orden nueva
     * @param cedula La cedula del cliente
     * @param fecha La fecha de la orden
     * @param redirectAttributes Los atributos para redirigir
     * @return La pagina de ordenes
     */
    @RequestMapping(value="/ordenes/add", method = RequestMethod.POST)
    public String procesarAddOrden(@RequestParam int cedula, @RequestParam String fecha, RedirectAttributes redirectAttributes) {
        ArrayList<String> errors = new ArrayList<String>();
        // Validar la cedula
        int idCliente = OrdenValidator.validarClienteCedula(cedula);
        if (idCliente == -1) {
            errors.add("Ningun cliente posee esta cedula");
        }
        if (fecha.equals("")) {
            errors.add("Por favor ingrese una fecha");
        }
        // Revisar si hay errores
        if (errors.size() > 0) {
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/ordenes/add";
        }
        // Procesar la orden
        OrdenJDBC ordenJDBC = new OrdenJDBC();
        ordenJDBC.setDataSource(DatabaseSource.getDataSource());
        ordenJDBC.crearOrdenNueva(idCliente, fecha);
        // Activar cliente
        ClientJDBC clientJDBC = new ClientJDBC();
        clientJDBC.setDataSource(DatabaseSource.getDataSource());
        clientJDBC.activarCliente(idCliente);
        redirectAttributes.addFlashAttribute("success_msg", "Orden creada");
        // TODO: Redirigir a la pagina principal de ordenes
        return "redirect:/";    

    }
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

        OrdenJDBC ordenJDBC= new OrdenJDBC();
        ordenJDBC.setDataSource(DatabaseSource.getDataSource());
        Orden orden=ordenJDBC.getOrden(consecutivo);
        List<Detalle> detalles=ordenJDBC.getDetalles(consecutivo);
        model.addAttribute("orden",orden);
        model.addAttribute("detalles",detalles);
        return "detallesOrden" ;
}
