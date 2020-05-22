package com.negocio.automoviles.models;

public class HolderPartProvedor {
    public String parte;
    public String proveedor;
    public float precio;
    public float porcentaje_ganancia;

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
