package com.negocio.automoviles.daos;

import javax.sql.DataSource;
import java.util.List;

import com.negocio.automoviles.models.Persona;

public interface PersonaDAO {
    public void setDataSource(DataSource ds);
    public List<Persona> getPersonas();
}
