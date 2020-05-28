package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.OrdenDAO;
import com.negocio.automoviles.mappers.DetallesMapperParte;
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
        List<Detalle> detalles=jdbcTemplateObject.query(query,new Object[]{id},new DetallesMapperParte());
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

    @Override
    public Orden getOrden(int consecutivo) {
        String query="SELECT ordenes.consecutivo , ordenes.fecha, ordenes.monto_total ,ordenes.cliente_id ,clientes.nombre " +
                "FROM ordenes INNER JOIN clientes" +
                " On ordenes.cliente_id = clientes.id" +
                " WHERE ordenes.consecutivo = ?";
        Orden orden= jdbcTemplateObject.queryForObject(query,new Object[]{consecutivo},new OrdenMapper());

        return orden;

    }

    @Override
    public List<Detalle> getDetalles(int consecutivo) {
        String query="SELECT detalles.parte_id,proveido_por.parte_id,detalles.provedor_id,cantidad,consecutivo_orden, " +
                " proveido_por.precio, proveido_por.porcentaje_ganancia, provedores.id, " +
                "partes.nombre, partes.id,provedores.nombre FROM detalles " +
                "INNER JOIN proveido_por on proveido_por.parte_id = detalles.parte_id " +
                "INNER JOIN partes on detalles.parte_id = partes.id " +
                "INNER JOIN provedores on detalles.provedor_id = provedores.id " +
                " WHERE consecutivo_orden = ? ";
        List<Detalle> detalles=jdbcTemplateObject.query(query,new Object[]{consecutivo},new DetallesMapperParte());

        return detalles;
    }
}
