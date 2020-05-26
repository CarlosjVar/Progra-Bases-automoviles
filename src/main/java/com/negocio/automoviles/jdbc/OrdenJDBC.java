package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.OrdenDAO;
import com.negocio.automoviles.mappers.DetallesMapper;
import com.negocio.automoviles.models.Detalle;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class OrdenJDBC implements OrdenDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource=ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean Parte_en_orden(int id) {
        String query="SELECT parte_id,provedor_id,cantidad,consecutivo_orden FROM detalles" +
                " WHERE parte_id = ? ";
        List<Detalle> detalles=jdbcTemplateObject.query(query,new Object[]{id},new DetallesMapper());


        return detalles.size()>0;
    }
}
