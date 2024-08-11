/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import my.controlador.controlador;
import my.vista.vista;

/**
 *
 * @author Agustin
 */
public class main {
    public static void main(String[] args) {
        vista interfaz = new vista();
        controlador con = new controlador (interfaz);
        
        interfaz.setResizable(false);
        interfaz.setLocationRelativeTo(null);
        interfaz.setVisible(true);
    }
}
