/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.openJournal;
import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jorop
 */
public class daoOpenJournal {
    
    public boolean buscarOpenJournal(int id) {

        boolean a = false;
        Connection con = null;

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperOpenJournal.buscarOpenJournalValidacion());
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

    public int eliminarOpenJournal(int id) {
        Conexion conexion = new Conexion();
        conexion = conexion.conectar();
        Connection c = conexion.getConexion();

        try {
            PreparedStatement p = c.prepareStatement(daoHelperOpenJournal.eliminarOpenJournal());
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

    public openJournal buscarRegistroOpenJournal(Connection con, int id) {

        openJournal c = new openJournal();

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperOpenJournal.buscarOpenJournal());
            cl.setInt(1, id);
            ResultSet r = cl.executeQuery();
            while (r.next()) {
                c.setId(r.getInt("idopenjournal"));
                c.setUsuario(r.getString("usuario"));
                c.setUrl(r.getString("url"));
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
    
    
     
     public String insertRegistroOpenJournal(String mail,String url,String name,int id) {
        String result = "";
        Connection con = null;
        
        if (!buscarOpenJournal(id)) {
            try {
                System.out.println("insertar un open joournal nuveo");
                Conexion co = new Conexion();
                co = co.conectar();
                con = co.getConexion();
                PreparedStatement pr = con.prepareStatement(daoHelperOpenJournal.insertarOpenJournal());

                pr.setString(1, name);
                pr.setString(2, url);
                pr.setString(3, mail);
                

                pr.execute();//para insertar actualizar //pr.executeUpdate();//para actualizar
                result = "Registro exitoso";
            } catch (Exception ex) {
                result = "La url: " + url + " Error   " + ex.getMessage();
            } finally {
                try {
                    con.close();
                } catch (Exception clo) {
                }
            }
        } else {
            
            System.out.println("entra a actualizar");
            System.out.println("mail:"+mail);
                        System.out.println("url:"+url);

                                    System.out.println("name:"+name);

            if (actulizarOpenJournalCheck(id)) {
                result = actualizarOpenJournal(mail,url,name,id);

            } else {
                result = "Fallo al buscar la url del open Journal";
            }

        }

        return result;
    }
     
      public boolean actulizarOpenJournalCheck(int id) {
        boolean a = false;
        Connection con = null;

        try {
            Conexion co = new Conexion();
            co.conectar();
            con = co.getConexion();

            PreparedStatement cl = con.prepareStatement(daoHelperOpenJournal.buscarRegistroOpenJournalActualizar());
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
      
       public String actualizarOpenJournal(String mail, String url, String name,int id) {
        String result = "";
        Connection c = null;
        try {
            Conexion co = new Conexion();
            co = co.conectar();
            c = co.getConexion();
            PreparedStatement pr = c.prepareStatement(daoHelperOpenJournal.actulizarOpenJournal());
            pr.setString(1, url);
            pr.setString(2, name);
            pr.setString(3, mail);
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
}
