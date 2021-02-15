/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.openJournal;
import Persistencia.daoOpenJournal;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class negocioOpenJournal {
    daoOpenJournal dao=new daoOpenJournal();
    
     public ResultSet getOpenJournal() {
    String sql = "select * from openjournal ";
        Conexion cone = new Conexion().conectar();
        
        Statement st;
        try {
            st = cone.getConexion().createStatement();
            return st.executeQuery(sql);

        } catch (SQLException ex) {

            return null;
        }
    }
     
     public int eliminarRegistroOpenJournal(int id) {
        return dao.eliminarOpenJournal(id);
    }
     
     
     public openJournal buscarActualizar(int id) {
        Conexion b = new Conexion();
        Connection c;
        b = b.conectar();
        c = b.getConexion();
        openJournal hor = dao.buscarRegistroOpenJournal(c, id);
        return hor;
    }
     
}
