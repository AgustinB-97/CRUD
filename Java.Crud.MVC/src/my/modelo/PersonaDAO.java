/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

        

public class PersonaDAO {
    Conexion conectar = new Conexion();
    Connection conexion;
    PreparedStatement pst;
    ResultSet rs;
    
    public List listar(){
        
        List<Persona>datos = new ArrayList<>();
        String consulta= "select * from datos";
        try{
            conexion = conectar.getConexion();
            pst=conexion.prepareStatement(consulta);
            rs=pst.executeQuery();
            while(rs.next()){
                Persona persona = new Persona();
                persona.setId(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setCorreo(rs.getString(3));
                persona.setTelefono(rs.getInt(4));
                datos.add(persona);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error en Consulta."+ e);
        }
        return datos;
    }
    
    public int agregar(Persona persona){
        String consulta = "insert into datos (nombre, correo, telefono) values (?,?,?)";
        
        
        try{
            conexion = conectar.getConexion();
            pst=conexion.prepareStatement(consulta);
            
                        
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getCorreo());
            pst.setInt(3, persona.getTelefono());
            
            pst.executeUpdate();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en Agregar."+e);
        }
        return 1;
    }
    public int actualizar (Persona persona){
       String consulta = "update datos set nombre=?, correo=?, telefono=? where id=?";
        try{
            conexion = conectar.getConexion();
            pst=conexion.prepareStatement(consulta);
                 
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getCorreo());
            pst.setInt(3, persona.getTelefono());
            pst.setInt(4, persona.getId());
            
            pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo ACTUALIZAR LA BASE DE DATOS"+e);
        }
       return 1;
    }
    
    public void eliminar(int id){
        String consulta = "delete from datos where id="+id;
        try{
             conexion = conectar.getConexion();
             pst=conexion.prepareStatement(consulta);
             pst.executeUpdate();
             
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR - No se pudo ELIMINAR"+ e);
        }
    }
}
