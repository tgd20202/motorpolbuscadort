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
public class deoHelperCatalogos {
     public static String insertarCatalogos(){
       StringBuilder sb= new StringBuilder();
       sb.append("insert into catalogos values(?,?)");
       return sb.toString();
    }
    
    public static String eliminarCatalogos(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete catalogos where nombre=?");
        return sb.toString();
    }
    
    public static String buscarCatalogos(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from catalogos where nombre=?");
        return sb.toString();
    }
    public static String actuliaxarCatalogos(){
        StringBuilder sb = new StringBuilder();
        sb.append("update catalogos SET url=?,nombre=?"
                + "WHERE nombre=?");
        return sb.toString();
    }
}
