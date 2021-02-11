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
public class daoHelperLogin {
    public static String buscarUsuarios() {
         StringBuilder sb = new StringBuilder();
        sb.append("select * from editores");
        return sb.toString();
    }
}
