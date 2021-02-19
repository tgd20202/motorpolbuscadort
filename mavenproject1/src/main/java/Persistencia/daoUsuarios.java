/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.openJournal;
import Entidades.usuarios;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jorop
 */
public class daoUsuarios {

    public usuarios buscarRegistroUsuarios(Connection con, int id) {

        usuarios c = new usuarios();

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperUsuarios.buscarUsuarios());
            cl.setInt(1, id);
            ResultSet r = cl.executeQuery();
            while (r.next()) {
                c.setId(r.getInt("id"));
                c.setMail(r.getString("mail"));
                c.setRol(r.getString("rol"));
                c.setNombre(r.getString("nombre"));

            }
        } catch (Exception entidadregistro) {
            entidadregistro.printStackTrace();
            return c;
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return c;
    }//fin buscar usuario

    public String insertRegistrousuario(String mail, String rol, String name, int id) {
        String result = "";
        Connection con = null;

        if (!buscarUsuario(id)) {
            try {
                System.out.println("insertar un Usuaro nuveo");
                Conexion co = new Conexion();
                co = co.conectar();
                con = co.getConexion();
                PreparedStatement pr = con.prepareStatement(daoHelperUsuarios.insertarUsuarios());

                pr.setString(1, name);
                pr.setString(2, rol);
                pr.setString(3, mail);

                pr.execute();//para insertar actualizar //pr.executeUpdate();//para actualizar
                result = "Registro exitoso";
            } catch (Exception ex) {
                result = "El usuario: " + mail + " Error   " + ex.getMessage();
            } finally {
                try {
                    con.close();
                } catch (Exception clo) {
                }
            }
        } else {

            System.out.println("entra a actualizar usuario");
            System.out.println("mail:" + mail);
            System.out.println("rol:" + rol);

            System.out.println("name:" + name);
            System.out.println("id"+id);

            if (actulizarUsuarioCheck(id)) {
                result = actualizarUsuario(mail, rol, name,id);

            } else {
                result = "Fallo al buscar usuario";
            }

        }

        return result;
    }

    public boolean buscarUsuario(int id) {

        boolean a = false;
        Connection con = null;

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperUsuarios.buscarRegistroUsuarioActualizar());
            cl.setInt(1, id);
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
    
    public boolean actulizarUsuarioCheck(int id) {
        boolean a = false;
        Connection con = null;

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperUsuarios.buscarRegistroUsuarioActualizar());
            cl.setInt(1, id);
            
            
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
    
     public String actualizarUsuario(String mail, String rol, String name,int id) {
        String result = "";
        Connection c = null;
        try {
            Conexion co = new Conexion();
            co = co.conectar();
            c = co.getConexion();
            PreparedStatement pr = c.prepareStatement(daoHelperUsuarios.actulizarUsuarios());
            pr.setString(1, mail);
            pr.setString(2, name);
            pr.setString(3, rol);
            pr.setInt(4, id);
            

            pr.executeUpdate();
            result = "Registro actualizado";
        } catch (Exception ex) {
            result = "El error es: " + ex.getMessage();
        } finally {
            try {
                c.close();
            } catch (Exception clo) {
                clo.printStackTrace();
            }
        }
        return result;
    }//fin actualizar usuario
     
     
     
    public int eliminarUsuario(int id) {
        Conexion conexion = new Conexion();
        conexion = conexion.conectar();
        Connection c = conexion.getConexion();

        try {
            PreparedStatement p = c.prepareStatement(daoHelperUsuarios.eliminarUsuarios());
            p.setInt(1, id);
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
