/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package boleteriaCine;

import controlador.ControladorIniciarSesion;
import modelo.DAO.UsuarioDAO;
import modelo.servicios.Autenticador;
import vista.*;

/**
 *
 * @author Carlos Ortega
 */
public class BoleteriaCine {

    public static void main(String[] args) {
        // Evita que las imanes se muestren borrosas 
        System.setProperty("sun.java2d.uiScale", "1.0");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO(); 
        VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
        Autenticador autenticador = new Autenticador();
        
        ControladorIniciarSesion ctlrIniciarSesion = new ControladorIniciarSesion(vistaInicioSesion, usuarioDAO, autenticador);
        
        ctlrIniciarSesion.iniciar();
        
        
        
    }
}
