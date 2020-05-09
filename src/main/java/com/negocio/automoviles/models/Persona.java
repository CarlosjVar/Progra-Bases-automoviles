package com.negocio.automoviles.models;

public class Persona extends Cliente {
    private int cedula;

    public Persona() {

    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}
