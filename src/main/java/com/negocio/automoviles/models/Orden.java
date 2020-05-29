package com.negocio.automoviles.models;

public class Orden {
    private int consecutivo;
    private String fecha;
    private int idCliente;
    private String nombreCliente;
    private int total;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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
