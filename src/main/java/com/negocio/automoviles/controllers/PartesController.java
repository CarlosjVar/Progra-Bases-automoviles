package com.negocio.automoviles.controllers;
import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.PartesJDBC;
import com.negocio.automoviles.jdbc.ProvedoresJDBC;
import com.negocio.automoviles.models.HolderPartProvedor;
import com.negocio.automoviles.models.Parte;
import com.negocio.automoviles.models.Provedor;
import com.negocio.automoviles.validators.ParteValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
     * Carga la página para agregar partes
     * @param model
     * @return
     */
    @RequestMapping(value = "/partes/add", method = RequestMethod.GET)
    public String AddParte(Model model)
    {
        if (!model.containsAttribute("parte")) {
        model.addAttribute("parte", new Parte());
    }
        PartesJDBC partesJDBC= new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        List<String> marcas=partesJDBC.getMarcasP();
        List<String> fabricantes=partesJDBC.getFabricantresP();
        model.addAttribute("marcas",marcas);
        model.addAttribute("fabricantes",fabricantes);
        return "addparte";
    }
    @RequestMapping(value = "/partes/add",method = RequestMethod.POST)
    public String AddParteConfirmar(@ModelAttribute Parte parte, RedirectAttributes redirectAttributes)
    {
        ArrayList<String>errores= ParteValidator.validarParte(parte.getNombre());
        if(errores.size()>0)
        {
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("parte", parte);
            return "redirect:/partes/add";
        }
        PartesJDBC partesJDBC = new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        int idM=partesJDBC.getIDMarcasP(parte.getMarca());
        int idF=partesJDBC.getIDFabricantesP(parte.getFabricante());
        System.out.println(idM);
        partesJDBC.agregarParte(parte,idM,idF);
        redirectAttributes.addFlashAttribute("success_msg", "Parte agregada");
        return "redirect:/partes";

    }
    @RequestMapping(value = "/partes/join",method = RequestMethod.GET)
    public String JoinParteProv(Model model)
    {
        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new HolderPartProvedor());
        }
        PartesJDBC partesJDBC=new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        ProvedoresJDBC provedoresJDBC= new ProvedoresJDBC();
        provedoresJDBC.setDataSource(DatabaseSource.getDataSource());
        List<Parte> partes= partesJDBC.getPartes();
        List<Provedor> provedores= provedoresJDBC.getProvedores();
        model.addAttribute("partes",Parte.getNombres(partes));
        model.addAttribute("provedores",Provedor.getNombres(provedores));
        System.out.println(Provedor.getNombres(provedores));
        return "joinParte";
    }

}
