package com.negocio.automoviles.daos;

import javax.sql.DataSource;

public interface OrdenDAO {

    public void setDataSource(DataSource ds);
    public boolean Parte_en_orden (int id);
    public void crearOrdenNueva(int idCliente, String fecha);
}
