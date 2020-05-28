package com.negocio.automoviles.daos;

import com.negocio.automoviles.models.Orden;

import javax.sql.DataSource;
import java.util.List;

public interface OrdenDAO {

    public void setDataSource(DataSource ds);
    public boolean Parte_en_orden (int id);
    public List<Orden> getOrdenes();
}
