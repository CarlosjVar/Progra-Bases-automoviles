package com.negocio.automoviles.models;

public class Orden {
    private int consecutivo;
    private String fecha;
    private int idCliente;

    public int getConsecutivo() {
        return consecutivo;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
