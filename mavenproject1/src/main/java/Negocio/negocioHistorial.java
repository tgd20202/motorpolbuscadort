/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Persistencia.daoHistoria;
import Persistencia.daoUsuarios;
import Utilidades.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class negocioHistorial {
    daoHistoria dao = new daoHistoria();

    public ResultSet getUrls() {
        String sql = "select * from public.historial";
        Conexion cone = new Conexion().conectar();

        Statement st;
        try {
            st = cone.getConexion().createStatement();
            return st.executeQuery(sql);

        } catch (SQLException ex) {

            return null;
        }
    }
    
    public int eliminarRegistroUrl(int id,String usuario) {
        return dao.eliminarUrl(id,usuario);
    }
    
}
