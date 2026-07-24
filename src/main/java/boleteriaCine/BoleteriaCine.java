/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package boleteriaCine;

import vista.*;

/**
 *
 * @author Carlos Ortega
 */
public class BoleteriaCine {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        
        java.awt.EventQueue.invokeLater(() -> {
            new Registrate().setVisible(true);
            new InicioSesion().setVisible(true);
        });
    }
}
