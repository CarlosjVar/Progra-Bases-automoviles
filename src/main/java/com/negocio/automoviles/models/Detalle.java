package com.negocio.automoviles.models;

public class Detalle {
    private int cantidad;
    private int ProveedorID;
    private int ParteID;
    private int ConsecutivoOrden;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProveedorID() {
        return ProveedorID;
    }

    public void setProveedorID(int proveedorID) {
        ProveedorID = proveedorID;
    }

    public int getParteID() {
        return ParteID;
    }

    public void setParteID(int parteID) {
        ParteID = parteID;
    }

    public int getConsecutivoOrden() {
        return ConsecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        ConsecutivoOrden = consecutivoOrden;
    }
}
