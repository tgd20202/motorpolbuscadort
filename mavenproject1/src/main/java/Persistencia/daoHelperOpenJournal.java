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
       sb.append("insert into public.openjournal (nombre,url,usuario)  values(?,?,?)");
       return sb.toString();
    }
    
    public static String eliminarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete from public.openjournal where idopenjournal=?");
        return sb.toString();
    }
    
    public static String buscarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.openjournal where idopenjournal=?");
        return sb.toString();
    }
    public static String buscarOpenJournalValidacion(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.openjournal where idopenjournal=?");
        return sb.toString();
    }
    
    public static String buscarRegistroOpenJournalActualizar(){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from public.openjournal where idopenjournal=?");
        return sb.toString();
    }
    
    public static String actulizarOpenJournal(){
        StringBuilder sb = new StringBuilder();
        sb.append("update public.openjournal SET url=?,nombre=?,usuario=? "
                + "WHERE idopenjournal=?");
        return sb.toString();
    }
}
