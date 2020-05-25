package com.negocio.automoviles.controllers;
import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.ParteProvedorJDBC;
import com.negocio.automoviles.jdbc.PartesJDBC;
import com.negocio.automoviles.jdbc.ProvedoresJDBC;
import com.negocio.automoviles.models.HolderPartProvedor;
import com.negocio.automoviles.models.Parte;
import com.negocio.automoviles.models.ParteProvedor;
import com.negocio.automoviles.models.Provedor;
import com.negocio.automoviles.validators.ParteValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

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
        // TODO: Agregar funcionalidad para buscar segun modelo y anio de auto
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
    @RequestMapping(value = "/partes/{id}", method = RequestMethod.GET)
    public String detallesParte(Model model, @PathVariable(value = "id") int id) {
        PartesJDBC partesJDBC = new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        // Obtener parte
        Parte parte = partesJDBC.getParte(id);
        model.addAttribute("parte", parte);
        return "detallesparte";
    }

    /**
     * Carga la plantilla para añadir partes
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

    /**
     * Procesa la adición de la parte
     * @param parte
     * @param redirectAttributes
     * @return
     */
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

    /**
     * Carga la plantilla para crear relaciones entre partes y provedores
     * @param model
     * @return
     */
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
        return "joinParte";
    }

    /**
     * Procesa la solicitud de relacion parte-provedor
     * @param holder
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/partes/join",method = RequestMethod.POST)
    public String ProcesarJoinParteProv(@ModelAttribute HolderPartProvedor holder, RedirectAttributes redirectAttributes)
    {
        ArrayList<String>errores= ParteValidator.validarRelacion(holder);
        if(errores.size()>0)
        {
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("holder", holder);
            return "redirect:/partes/join";
        }
        PartesJDBC partesJDBC=new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        ProvedoresJDBC provedoresJDBC= new ProvedoresJDBC();
        provedoresJDBC.setDataSource(DatabaseSource.getDataSource());
        ParteProvedorJDBC parteProvedorJDBC= new ParteProvedorJDBC();
        parteProvedorJDBC.setDataSource(DatabaseSource.getDataSource());
        int idParte= partesJDBC.getIDParte(holder.parte);
        int idProvedor= provedoresJDBC.getIDProvedor(holder.proveedor);
        if(parteProvedorJDBC.existeRelacion(idParte,idProvedor))
        {
            errores.add("Esa relación ya existe");
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("holder", holder);
            return "redirect:/partes/join";

        }
        partesJDBC.relacionParteProvedor(holder,idParte,idProvedor);
        redirectAttributes.addFlashAttribute("success_msg", "Afiliación creada");

        return "redirect:/partes";
    }

    /**
     * Carga la plantilla para editar una relación
     * @param model
     * @return
     */
    @RequestMapping(value = "/partes/joinEdit",method = RequestMethod.GET)
    public String modificarRelacion(Model model)
    {
        if (!model.containsAttribute("holder")) {
            model.addAttribute("holder", new HolderPartProvedor());
        }
        ParteProvedorJDBC parteProvedorJDBC= new ParteProvedorJDBC();
        parteProvedorJDBC.setDataSource(DatabaseSource.getDataSource());
        List<HolderPartProvedor> relaciones =parteProvedorJDBC.getRelaciones();
        HolderPartProvedor.FindMissingInfo(relaciones);
        model.addAttribute("relaciones",relaciones);
        return "editRelacion";
    }
    @RequestMapping(value = "partes/joinEdit",method = RequestMethod.POST)
    public String procesarModificarRelacion(@ModelAttribute HolderPartProvedor holder, RedirectAttributes redirectAttributes)
    {
        ArrayList<String>errores= ParteValidator.validarRelacion(holder);
        if(errores.size()>0)
        {
            redirectAttributes.addFlashAttribute("errors", errores);
            redirectAttributes.addFlashAttribute("holder", holder);
            return "redirect:/partes/joinEdit";
        }
        String [] parte_provedor= holder.relacion.split("-");
        ParteProvedorJDBC parteProvedorJDBC= new ParteProvedorJDBC();
        parteProvedorJDBC.setDataSource(DatabaseSource.getDataSource());
        List<HolderPartProvedor> relaciones =parteProvedorJDBC.getRelaciones();
        HolderPartProvedor.FindMissingInfo(relaciones);
        for (HolderPartProvedor relacion:relaciones
             ) {

            if(relacion.parte.equals(parte_provedor[0])&&relacion.proveedor.equals(parte_provedor[1]))
            {

                parteProvedorJDBC.modificarRelacion(relacion.parteID,relacion.provedorID,holder.precio,holder.porcentaje_ganancia);
            }

        }
        redirectAttributes.addFlashAttribute("success_msg", "Afiliación modificada");
        return "redirect:/partes";
    }
}
