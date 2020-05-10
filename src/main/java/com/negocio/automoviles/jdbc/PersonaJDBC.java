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

    /**
     * Obtiene todas las personas
     * @return Las personas
     */
    @Override
    public List<Persona> getPersonas() {
        String query = "SELECT clientes.nombre, personas.cedula, clientes.estado, clientes.id, clientes.direccion, clientes.ciudad " +
                "FROM clientes INNER JOIN personas ON clientes.id = personas.surrogate_key";
        List<Persona> personas = jdbcTemplateObject.query(query, new PersonaMapper());
        return personas;
    }

    /**
     * Verifica si existe la cedula
     * @param cedula
     * @return true si existe la cedula, false de lo contrario
     */
    @Override
    public boolean existeCedula(int cedula) {
        String query = "SELECT clientes.nombre, personas.cedula, clientes.estado, clientes.id, clientes.direccion, clientes.ciudad " +
                "FROM clientes INNER JOIN personas ON clientes.id = personas.surrogate_key WHERE cedula = ?";
        List<Persona> personas = jdbcTemplateObject.query(query, new Object[]{cedula}, new PersonaMapper());
        return personas.size() > 0;
    }

    /**
     * Agrega una persona a la base de datos
     * @param persona
     */
    @Override
    public void agregarPersona(Persona persona) {
        ClientJDBC clientJDBC = new ClientJDBC();
        clientJDBC.setDataSource(this.dataSource);
        // Insertar en tabla de clientes
        int surrogateKey = clientJDBC.agregarCliente(persona.getNombre(), persona.getDireccion(), persona.getCiudad(), "ACTIVO");
        String query = "INSERT INTO personas (cedula, surrogate_key) VALUES (?, ?)";
        jdbcTemplateObject.update(query, persona.getCedula(), surrogateKey);
    }
}
