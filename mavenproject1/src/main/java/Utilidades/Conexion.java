/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jorop
 */
public class Conexion {

    private Connection conexion;
    private PreparedStatement stm = null;
    private ResultSet rs;

    public static void main(String[] args) {

        Conexion obcconexion = new Conexion();

        obcconexion.conectar();

    }

    public Conexion conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            String BaseDeDatos = "jdbc:postgresql://ec2-52-4-171-132.compute-1.amazonaws.com:5432/dan9j9ts7buek9";
            conexion = (Connection) DriverManager.getConnection(BaseDeDatos, "budfdjcxzwhcuo", "ce1c5d8e9bc2c2b072d817fab8101c382031ad627d0b756142c29fd416e4f644");


            if (conexion != null) {
                System.out.println("conexion exitosa");
            } else {
                System.out.println("conexion fallo");
            }
        } catch (Exception e) {

            System.out.println("Error conectando: " + e.getMessage());
        }
        return this;

    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error desconectando: " + ex.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

}
