package com.negocio.automoviles.jdbc;

import com.negocio.automoviles.daos.PartesDAO;
import com.negocio.automoviles.database.DatabaseSource;
import com.negocio.automoviles.mappers.*;
import com.negocio.automoviles.models.HolderPartProvedor;
import com.negocio.automoviles.models.Parte;
import com.negocio.automoviles.models.ParteProvedor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
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

    /**
     * Agrega una parte a la base de datos
     * @param parte
     */
    @Override
    public void agregarParte(Parte parte,int Marcaid,int Fabricanteid) {
        PartesJDBC partesJDBC= new PartesJDBC();
        partesJDBC.setDataSource(DatabaseSource.getDataSource());
        String query="INSERT INTO partes (nombre,marca_id,fabricante_id) VALUES(? ,? ,?)";
        jdbcTemplateObject.update(query,parte.getNombre(),Marcaid,Fabricanteid);
        ;
    }

    /**
     * Retorna una lista con todas las marcas de partes
     * @return
     */
    @Override
    public List<String> getMarcasP()
    {
        String query = "SELECT nombre FROM marcas_partes";
        List<String> marcas= jdbcTemplateObject.query(query,new MarcasPMapper());
        return marcas;
    }

    /**
     * Retorna una lista con todos los fabricantes de partes
     * @return
     */
    @Override
    public List<String> getFabricantresP()
    {
        String query = "SELECT nombre FROM fabricantes_partes";
        List<String> fabricantes=jdbcTemplateObject.query(query,new FabricantesPMapper());
        return fabricantes;

    }

    /**
     * Consigue el id de las marcas
     * @param nombre
     * @return
     */
    @Override
    public int getIDMarcasP(String nombre) {
        String query = "SELECT id FROM marcas_partes" +
                " WHERE marcas_partes.nombre= ?";
        int id=jdbcTemplateObject.queryForObject(query,new Object[]{nombre},new MarcPIDMapper());
        return id;
    }

    /**
     * Obtiene el ID de los fabricantes
     * @param nombre
     * @return
     */
    @Override
    public int getIDFabricantesP(String nombre) {
        String query = "SELECT id FROM fabricantes_partes" +
                " WHERE fabricantes_partes.nombre= ?";
        int id=jdbcTemplateObject.queryForObject(query,new Object[]{nombre},new MarcPIDMapper());
        return id;
    }

    /**
     * Verifica si existe una parte
     * @param nombre
     * @return
     */
    @Override
    public boolean existeParte(String nombre) {
        String query = "SELECT partes.id, partes.nombre, marcas_partes.nombre AS marca, fabricantes_partes.nombre AS fabricante FROM partes " +
                "INNER JOIN marcas_partes ON partes.marca_id = marcas_partes.id " +
                "INNER JOIN fabricantes_partes ON partes.fabricante_id = fabricantes_partes.id " +
                " WHERE partes.nombre = ?";
        List<Parte>partes=jdbcTemplateObject.query(query,new Object[] {nombre},new PartesMapper());

        return partes.size()>0 ;
    }

    /**
     * Crea una relación en la tabla de asociación de partes-provedores
     * @param info
     * @param parteID
     * @param provedorID
     */
    @Override
    public void relacionParteProvedor(HolderPartProvedor info,int parteID,int provedorID) {
    String query="INSERT INTO proveido_por(parte_id,provedor_id,precio,porcentaje_ganancia) VALUES(?,?,?,?)";
    jdbcTemplateObject.update(query,parteID,provedorID,info.precio,info.porcentaje_ganancia);
    }

    /**
     * Retorna el id de una parte basado en su nombre
     * @param nombreP
     * @return
     */
    @Override
    public int getIDParte(String nombreP) {
        String query = "SELECT partes.id  FROM partes " +
                " WHERE partes.nombre = ?";
        int ID=jdbcTemplateObject.queryForObject(query,new Object[] {nombreP},new MarcPIDMapper());
        return  ID;
    }

    @Override
    public void deleteParte(int id) {
        String query="DELETE FROM partes WHERE id = ? ";
        jdbcTemplateObject.update(query,new Object[]{id});
    }


}
