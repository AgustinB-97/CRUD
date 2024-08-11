/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Agustin
 */
public class Conexion {
    Connection conexion;
    
    public Connection getConexion(){
        String direccion= "jdbc:mysql://localhost:3306/bd_mvc";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(direccion, "root", "");
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la Conexión." + e);
        }
        return conexion;
    }
}
