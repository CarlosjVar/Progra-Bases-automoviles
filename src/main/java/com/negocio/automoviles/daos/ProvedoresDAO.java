package com.negocio.automoviles.daos;

import com.negocio.automoviles.models.Provedor;

import javax.sql.DataSource;
import java.util.List;

public interface ProvedoresDAO {
    public void setDataSource(DataSource ds);
    public Provedor getProvedor(String nombre);
    public List<Provedor> getProvedores();

}
