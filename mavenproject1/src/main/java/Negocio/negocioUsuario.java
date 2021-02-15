/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.openJournal;
import Entidades.usuarios;
import Persistencia.daoOpenJournal;
import Persistencia.daoUsuarios;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class negocioUsuario {

    daoUsuarios dao = new daoUsuarios();

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

    public usuarios buscarActualizar(int id) {
        Conexion b = new Conexion();
        Connection c;
        b = b.conectar();
        c = b.getConexion();
        usuarios hor = dao.buscarRegistroUsuarios(c, id);
        return hor;
    }
    
     public int eliminarRegistroUsuario(int id) {
        return dao.eliminarUsuario(id);
    }
     
}
