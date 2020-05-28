package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.ClienteDAO;
import com.negocio.automoviles.mappers.EstadoMapper;
import com.negocio.automoviles.mappers.OrganizacionMapper;
import com.negocio.automoviles.mappers.PersonaMapper;
import com.negocio.automoviles.models.Cliente;
import com.negocio.automoviles.models.Persona;
import org.springframework.jdbc.core.JdbcTemplate;
import com.negocio.automoviles.models.Organizacion;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Modifica la informacion de un cliente
     * @param id
     * @param nombre
     * @param direccion
     * @param ciudad
     * @param estado
     */
    @Override
    public void modificarCliente(int id, String nombre, String direccion, String ciudad, String estado) {
        String query = "UPDATE clientes SET nombre = ?, direccion = ?, ciudad = ?, estado = ? WHERE id = ?";
        jdbcTemplateObject.update(query, nombre, direccion, ciudad, estado, id);
    }

    /**
     * Suspende un cliente
     * @param id
     */
    @Override
    public void suspenderCliente(int id) {
        String query = "UPDATE clientes SET estado = 'SUSPENDIDO' WHERE id = ?";
        jdbcTemplateObject.update(query, id);
    }

    /**
     * Obtiene todos los estados
     * @return Los estados disponibles para un cliente
     */
    @Override
    public List<String> getEstados() {
        String query = "SELECT estado FROM estados";
        List<String> estados = jdbcTemplateObject.query(query, new EstadoMapper());
        return estados;
    }

    /**
     * Activar un cliente luego de realizar una orden
     * @param id El id del cliente
     */
    @Override
    public void activarCliente(int id) {
        String query = "UPDATE clientes SET estado = 'ACTIVO' WHERE id = ?";
        jdbcTemplateObject.update(query, id);
    }

    /**
     * Obtiene todos los clientes activos
     * @return La lista de clientes activos
     */
//    @Override
//    public List<Cliente> getClientesActivos() {
//        // Obtener personas
//        String queryPersonas = "SELECT clientes.nombre, personas.cedula, clientes.estado, clientes.id, clientes.direccion, clientes.ciudad " +
//                "FROM clientes INNER JOIN personas ON clientes.id = personas.id_cliente WHERE NOT estado = 'SUSPENDIDO'";
//        List<Persona> personas = jdbcTemplateObject.query(queryPersonas, new PersonaMapper());
//        // Obtener organizaciones
//        String queryOrganizaciones = "SELECT clientes.nombre, organizaciones.cedula_juridica, clientes.estado, clientes.id, clientes.direccion, clientes.ciudad " +
//                ",organizaciones.encargado_nombre,organizaciones.encargado_telefono,organizaciones.encargado_cargo "
//                +
//                "FROM clientes INNER JOIN organizaciones ON clientes.id = organizaciones.surrogate_key";
//        List<Organizacion> organizacions = jdbcTemplateObject.query(queryOrganizaciones, new OrganizacionMapper());
//        List<Cliente> clientesActivos = new ArrayList<>();
//        // Agregar personas
//        for (Persona persona: personas) {
//            clientesActivos.add(persona);
//        }
//        // Agregar organizaciones
//        for (Organizacion organizacion : organizacions) {
//            clientesActivos.add(organizacion);
//        }
//        return clientesActivos;
//    }
}
