package com.negocio.automoviles.models;

public class Parte {
    private int parteID;
    private String nombre;
    private String marca;
    private String fabricante;

    public int getParteID() {
        return parteID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setParteID(int parteID) {
        this.parteID = parteID;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
