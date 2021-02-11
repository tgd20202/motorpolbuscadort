/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Utilidades.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jorop
 */
public class negocioLogin {
     public ResultSet getUsuarios() {
    String sql = "select * from editores ";
        Conexion cone = new Conexion().conectar();
        
        Statement st;
        try {
            st = cone.getConexion().createStatement();
            return st.executeQuery(sql);

        } catch (SQLException ex) {

            return null;
        }
    }
}
