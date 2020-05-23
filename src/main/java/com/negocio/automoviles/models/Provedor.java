package com.negocio.automoviles.models;

import java.util.ArrayList;
import java.util.List;

public class Provedor {
    public String nombre;
    public String ciudad;
    public String Dirección;
    public List<String> telefonos;
    public String nombre_contacto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDirección() {
        return Dirección;
    }

    public void setDirección(String dirección) {
        Dirección = dirección;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public static ArrayList<String> getNombres(List<Provedor> provedores)
    {
        ArrayList<String> nombres=new ArrayList<String>();

        for (Provedor p:provedores
             )
        {

            nombres.add(p.nombre);

        }
        return nombres;
    }
}
