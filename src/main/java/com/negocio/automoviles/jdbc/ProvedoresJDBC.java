package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.ProvedoresDAO;
import com.negocio.automoviles.mappers.ProvedoresMapper;
import com.negocio.automoviles.models.Provedor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ProvedoresJDBC implements ProvedoresDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(ds);
    }

    @Override
    public Provedor getProvedor(String nombre) {
        return null;
    }

    @Override
    public List<Provedor> getProvedores() {
        String query = "SELECT nombre,ciudad,direccion,telefono,nombre_contacto FROM provedores";
        List<Provedor> provedores= jdbcTemplateObject.query(query,new ProvedoresMapper());
        return provedores;
    }

}
