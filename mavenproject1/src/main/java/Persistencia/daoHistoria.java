/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jorop
 */
public class daoHistoria {
    public void insertRegistroOpenJournal(String mail,String url,String titulo) {
        String result = "";
        Connection con = null;
        
        if (!buscarUrl(url,mail)) {
            try {
                System.out.println("insertar un open joournal nuveo");
                Conexion co = new Conexion();
                co = co.conectar();
                con = co.getConexion();
                PreparedStatement pr = con.prepareStatement(daoHelperHistorial.insertarHistorial());

                pr.setString(1, mail);
                pr.setString(2, url);
                pr.setString(3, titulo);
                

                pr.execute();//para insertar actualizar //pr.executeUpdate();//para actualizar
                System.out.println("Registro exitoso");
            } catch (Exception ex) {
                result = "La url: " + url + " Error   " + ex.getMessage();
            } finally {
                try {
                    con.close();
                } catch (Exception clo) {
                }
            }
        } else{
            System.out.println("esta url se repidte:"+url);
        }

    }
    
    public boolean buscarUrl(String url,String mail) {

        boolean a = false;
        Connection con = null;

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperHistorial.buscarHistorialInsertar());
            cl.setString(1, url);
            cl.setString(2, mail);
            ResultSet r = cl.executeQuery();
            while (r.next()) {
                return true;

            }
        } catch (Exception entidadregistro) {
            entidadregistro.printStackTrace();
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return a;

    }
    
    public int eliminarUrl(int id,String usuario) {
        Conexion conexion = new Conexion();
        conexion = conexion.conectar();
        Connection c = conexion.getConexion();

        try {
            PreparedStatement p = c.prepareStatement(daoHelperHistorial.eliminarUrl());
            p.setInt(1, id);
            p.setString(2, usuario);
            return p.executeUpdate();
            //p.executeUpdate(); tambien puede ser
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (Exception clo) {
                clo.printStackTrace();
            }
        }
        return 0;
    }//fin cambiar estado usuario
}
