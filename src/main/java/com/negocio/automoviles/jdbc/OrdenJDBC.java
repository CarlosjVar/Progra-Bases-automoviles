package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.OrdenDAO;
import com.negocio.automoviles.mappers.DetallesMapper;
import com.negocio.automoviles.mappers.OrdenMapper;
import com.negocio.automoviles.models.Detalle;
import com.negocio.automoviles.models.Orden;
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

    @Override
    public List<Orden> getOrdenes() {
        String query="SELECT ordenes.consecutivo , ordenes.fecha, ordenes.monto_total ,ordenes.cliente_id ,clientes.nombre " +
                "FROM ordenes INNER JOIN clientes" +
                " On ordenes.cliente_id = clientes.id";
        List<Orden> ordenes= jdbcTemplateObject.query(query,new OrdenMapper());

        return ordenes;
    }
}
