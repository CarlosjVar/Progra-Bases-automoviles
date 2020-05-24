package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.AutomovilesDAO;
import com.negocio.automoviles.mappers.AnioMapper;
import com.negocio.automoviles.mappers.ModeloMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Clase para acceder a las entidades de automoviles
 */
public class AutomovilesJDBC implements AutomovilesDAO {
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

    /**
     * Obtiene todos los modelos disponibles
     * @return Los modelos de la base de datos
     */
    @Override
    public List<String> getModelosDisponibles() {
        String query = "SELECT DISTINCT modelo FROM automoviles";
        List<String> modelos = jdbcTemplateObject.query(query, new ModeloMapper());
        return modelos;
    }

    /**
     * Obtiene todos los anios dispobles
     * @return Los anios disponibles
     */
    @Override
    public List<Integer> getAniosDisponinles() {
        String query = "SELECT DISTINCT anio FROM automoviles";
        List<Integer> anios = jdbcTemplateObject.query(query, new AnioMapper());
        return anios;
    }
}
