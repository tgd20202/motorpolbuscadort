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
       sb.append("insert into public.editores (nombre,rol,mail)  values(?,?,?)");
       return sb.toString();
    }
    
    public static String eliminarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete  from public.editores where id=?");
        return sb.toString();
    }
    
    public static String buscarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.editores where id=?");
        return sb.toString();
    }
    public static String actulizarUsuarios(){
        StringBuilder sb = new StringBuilder();
        sb.append("update public.editores SET mail=?,nombre=?,rol=?"
                + "WHERE id=?");
        return sb.toString();
    }
    
    public static String buscarRegistroUsuarioActualizar(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.editores where id=?");
        return sb.toString();
    }
}
