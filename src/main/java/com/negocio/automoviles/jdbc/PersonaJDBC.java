package com.negocio.automoviles.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;

import com.negocio.automoviles.daos.PersonaDAO;
import com.negocio.automoviles.mappers.PersonaMapper;
import com.negocio.automoviles.models.Persona;

public class PersonaJDBC implements PersonaDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    /**
     * Configura el data source de la app
     * @param dataSource El data source de la app
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Persona> getPersonas() {
        String query = "SELECT clientes.nombre, personas.cedula, clientes.estado, clientes.id, clientes.direccion, clientes.ciudad " +
                "FROM clientes INNER JOIN personas ON clientes.id = personas.surrogate_key";
        List<Persona> personas = jdbcTemplateObject.query(query, new PersonaMapper());
        return personas;
    }
}
