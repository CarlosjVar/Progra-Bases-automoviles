package com.negocio.automoviles.daos;

import javax.sql.DataSource;
import java.util.List;

import com.negocio.automoviles.models.Persona;

public interface ClienteDAO {
    public void setDataSource(DataSource ds);
    public int agregarCliente(String nombre, String direccion, String ciudad, String estado);
    public void modificarCliente(int id, String nombre, String direccion, String ciudad, String estado);
    public void suspenderCliente(int id);
}

