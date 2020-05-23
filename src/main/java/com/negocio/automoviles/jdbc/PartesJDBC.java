package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.PartesDAO;
import com.negocio.automoviles.mappers.PartesMapper;
import com.negocio.automoviles.models.Parte;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Clase para acceder a la base de datos
 */
public class PartesJDBC implements PartesDAO {
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
     * Obtiene todas las partes
     * @return Todas las partes
     */
    @Override
    public List<Parte> getPartes() {
        String query = "SELECT partes.id, partes.nombre, marcas_partes.nombre AS marca, fabricantes_partes.nombre AS fabricante FROM partes " +
                        "INNER JOIN marcas_partes ON partes.marca_id = marcas_partes.id " +
                        "INNER JOIN fabricantes_partes ON partes.fabricante_id = fabricantes_partes.id";
        List<Parte> partes = jdbcTemplateObject.query(query, new PartesMapper());
        return partes;
    }

    /**
     * Obtiene una parte de acuerdo al id
     * @param id El id de la parte
     * @return La parte segun el id
     */
    @Override
    public Parte getParte(int id) {
        String query = "SELECT partes.id, partes.nombre, marcas_partes.nombre AS marca, fabricantes_partes.nombre AS fabricante FROM partes " +
                "INNER JOIN marcas_partes ON partes.marca_id = marcas_partes.id " +
                "INNER JOIN fabricantes_partes ON partes.fabricante_id = fabricantes_partes.id WHERE partes.id = ?";
        Parte parte = jdbcTemplateObject.queryForObject(query, new Object[] {id}, new PartesMapper());
        return parte;
    }

    @Override
    public List<Parte> getPartesByModeloAnio(String modelo, int anio) {
        return null;
    }
}
