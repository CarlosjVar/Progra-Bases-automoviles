package com.negocio.automoviles.daos;

import com.negocio.automoviles.models.Parte;
import javax.sql.DataSource;
import java.util.List;

/**
 * Partes Data Access Object
 */
public interface PartesDAO {
    public void setDataSource(DataSource ds);
    public List<Parte> getPartes();
    public Parte getParte(int id);
    public List<Parte> getPartesByModeloAnio(String modelo, int anio);
}
