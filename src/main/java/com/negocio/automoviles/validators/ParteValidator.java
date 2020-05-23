package com.negocio.automoviles.validators;

import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.jdbc.PartesJDBC;

import java.util.ArrayList;

public class ParteValidator {
    public static ArrayList<String> validarParte(String nombre){
        ArrayList<String> errores = new ArrayList<String>();
        PartesJDBC partesJDBC=new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        if(partesJDBC.existeParte(nombre))
        {
            errores.add("Esa parte ya existe en el sistema");
        }
        if(nombre.length()<1)
        {
            errores.add("Nombre inválido");
        }
        return  errores;
    }
}
