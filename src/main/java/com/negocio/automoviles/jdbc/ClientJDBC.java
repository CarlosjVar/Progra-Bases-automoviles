package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.ClienteDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ClientJDBC implements ClienteDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private SimpleJdbcInsert simpleJdbcInsert;

    /**
     * Configura el data source de la app
     * @param dataSource El data source de la app
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("clientes").usingGeneratedKeyColumns("id");
    }

    /**
     * Agregar informacion a la tabla cliente
     * @param nombre
     * @param direccion
     * @param ciudad
     * @param estado
     * @return La llave de la fila
     */
    @Override
    public int agregarCliente(String nombre, String direccion, String ciudad, String estado) {
        // Agregar parametros
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("nombre", nombre);
        parameters.put("direccion", direccion);
        parameters.put("ciudad", ciudad);
        parameters.put("estado", estado);
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return newId.intValue();
    }

    @Override
    public void modificarCliente(int id, String nombre, String direccion, String ciudad, String estado) {
        String query = "UPDATE clientes SET nombre = ?, direccion = ?, ciudad = ?, estado = ? WHERE id = ?";
        jdbcTemplateObject.update(query, nombre, direccion, ciudad, estado, id);
    }
}
