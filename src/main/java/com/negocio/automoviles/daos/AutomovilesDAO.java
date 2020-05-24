package com.negocio.automoviles.daos;

import javax.sql.DataSource;
import java.util.List;

public interface AutomovilesDAO {
    public void setDataSource(DataSource ds);
    public List<String> getModelosDisponibles();
    public List<Integer> getAniosDisponinles();
}
