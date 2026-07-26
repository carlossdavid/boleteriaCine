package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAO.UsuarioDAO;
import modelo.servicios.Autenticador;
import vista.VistaAdmin;
import vista.VistaAdminPeliculas;
import vista.VistaGestionEmpleados;
import vista.VistaInicioSesion;

public class ControladorAdmin implements ActionListener {

    private VistaAdmin vistaAdmin;

    public ControladorAdmin(VistaAdmin vistaAdmin) {
        this.vistaAdmin = vistaAdmin;

        
        if (this.vistaAdmin.getBtnEmpleados() != null) {
            this.vistaAdmin.getBtnEmpleados().addActionListener(this);
        }
        if (this.vistaAdmin.getBtnPeliculas() != null) {
            this.vistaAdmin.getBtnPeliculas().addActionListener(this);
        }
        if (this.vistaAdmin.getBtnCerrarSesion() != null) {
            this.vistaAdmin.getBtnCerrarSesion().addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent l) {
        if (l.getSource() == vistaAdmin.getBtnEmpleados()) {
            abrirGestionEmpleados();
        }else if (l.getSource() == vistaAdmin.getBtnPeliculas()) {
            abrirGestionPeliculas();
        }else if (l.getSource() == vistaAdmin.getBtnCerrarSesion()) {
            cerrarSesion();
        } 
    }    
        
    private void abrirGestionEmpleados() {
        
        vistaAdmin.dispose(); 
        VistaGestionEmpleados vistaGestion = new VistaGestionEmpleados();
        ControladorGestionEmpleados ctrlGestion = new ControladorGestionEmpleados(vistaGestion); 
        vistaGestion.setLocationRelativeTo(null);
        vistaGestion.setVisible(true);
    }
    
    private void abrirGestionPeliculas() {
        vistaAdmin.dispose();
        VistaAdminPeliculas vistaPeliculas = new VistaAdminPeliculas();
        ControladorAdminPeliculas ctrlPeliculas = new ControladorAdminPeliculas(vistaPeliculas);
        vistaPeliculas.setLocationRelativeTo(null);
        vistaPeliculas.setVisible(true);
    }
    
    private void cerrarSesion() {
        // Cierra la vista actual
        vistaAdmin.dispose();

        // Inyección de dependencias necesarias para la ventana de Inicio de Sesión
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Autenticador autenticador = new Autenticador();

        VistaInicioSesion vistaLogin = new VistaInicioSesion();
        ControladorIniciarSesion ctrlLogin = new ControladorIniciarSesion(vistaLogin, usuarioDAO, autenticador);
        
        ctrlLogin.iniciar();
    }
    
    void iniciar() {
        vistaAdmin.setVisible(true);
    }
}