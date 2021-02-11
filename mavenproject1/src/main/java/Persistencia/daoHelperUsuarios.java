/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

/**
 *
 * @author jorop
 */
public class daoHelperUsuarios {
     public static String insertarUsuarios(){
       StringBuilder sb= new StringBuilder();
       sb.append("insert into editores values(?,?,?,?)");
       return sb.toString();
    }
    
    public static String eliminarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete editores where id=?");
        return sb.toString();
    }
    
    public static String buscarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from editores where id=?");
        return sb.toString();
    }
    public static String actulixarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("update editores SET mail=?,nombre=?,rol=?"
                + "WHERE id=?");
        return sb.toString();
    }
}
