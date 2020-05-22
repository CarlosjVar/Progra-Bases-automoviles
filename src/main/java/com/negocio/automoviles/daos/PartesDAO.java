package com.negocio.automoviles.daos;

import com.negocio.automoviles.models.Organizacion;
import com.negocio.automoviles.models.Parte;

import javax.sql.DataSource;
import java.util.List;

public interface PartesDAO {
    public void setDataSource(DataSource ds);
    public Parte getParte(int ParteID);
    public List<Parte> getPartes();
    public void agregarParte(Parte parte);
}
