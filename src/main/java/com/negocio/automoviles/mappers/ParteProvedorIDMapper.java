package com.negocio.automoviles.mappers;

import com.negocio.automoviles.models.HolderPartProvedor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParteProvedorIDMapper implements RowMapper<HolderPartProvedor> {


    @Override
    public HolderPartProvedor mapRow(ResultSet rs, int rowNum) throws SQLException {
        HolderPartProvedor holder= new HolderPartProvedor();
        holder.parteID=rs.getInt("parte_id");
        holder.provedorID=rs.getInt("provedor_id");
        holder.precio=rs.getFloat("precio");
        holder.porcentaje_ganancia=rs.getFloat("porcentaje_ganancia");
        return holder;
    }
}
