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
public class daoHelperHistorial {
     public static String insertarHistorial(){
       StringBuilder sb= new StringBuilder();
       sb.append("insert into public.historial (usuario,url,titulo) values(?,?,?)");
       return sb.toString();
    }
    
    
    
    public static String buscarHistorial(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.historial where usuario=?");
        return sb.toString();
    }
    
    public static String buscarHistorialInsertar(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.historial where url=? AND usuario=?");
        return sb.toString();
    }
   
    
     public static String eliminarUrl(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete  from public.historial where idhistorial=? AND usuario=?");
        return sb.toString();
    }
    
}
