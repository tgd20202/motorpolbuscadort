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
public class daoHelperOpenJournal {
     public static String insertarOpenJournal(){
       StringBuilder sb= new StringBuilder();
       sb.append("insert into openjournal values(?,?,?,?)");
       return sb.toString();
    }
    
    public static String eliminarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete openjournal where nombre=?");
        return sb.toString();
    }
    
    public static String buscarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from openjournal where nombre=?");
        return sb.toString();
    }
    public static String actulixarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("update openjournal SET url=?,nombre=?,header=?,usuario=?,"
                + "WHERE nombre=?");
        return sb.toString();
    }
}
