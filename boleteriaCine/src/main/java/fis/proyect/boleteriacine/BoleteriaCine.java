/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fis.proyect.boleteriacine;

import vista.InicioSesion;

/**
 *
 * @author Carlos Ortega
 */
public class BoleteriaCine {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        java.awt.EventQueue.invokeLater(() -> {
            new InicioSesion().setVisible(true);
        });
    }
}
