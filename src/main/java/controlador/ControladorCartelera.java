package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.DAO.PeliculaDAO;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.Pelicula;
import modelo.servicios.Autenticador;
import vista.VistaCliente;
import vista.VistaInicioSesion;

/**
 *
 * @author carlo
 */
public class ControladorCartelera implements ActionListener{
    private VistaCliente vistaCartelera; 
    private PeliculaDAO peliculaDAO;
    private boolean flagInvitado = true;

    public ControladorCartelera(VistaCliente vistaCartelera, PeliculaDAO peliculaDAO) {
        this.vistaCartelera = vistaCartelera;
        this.peliculaDAO = peliculaDAO;
        
        vistaCartelera.addBtnCuentaListener(this);
        vistaCartelera.addBtnComprasListener(this);
        vistaCartelera.addBtnCarteleraListener(this);
        vistaCartelera.addBtnCerrarSesionListener(this);
    }
    
    public void iniciar() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas = peliculaDAO.getListaPeliculas();
        if (peliculas == null) {
            vistaCartelera.mostrarError("No se pudo cargar las películas");
            return;
        }
        vistaCartelera.mostrarPeliculas(peliculas);
        vistaCartelera.setVisible(true);
        flagInvitado = false;
    }
    
    
    public void iniciarInvitado() {
        this.iniciar();
        vistaCartelera.configurarInvitado();
    }
    
    
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaCartelera.getBtnCerrarSesion()) {
            regresarInicioSesion();
            vistaCartelera.cerrar();
        }
        if (flagInvitado) {
            vistaCartelera.mostrarError("Por favor inicia sesión para acceder a la función");
        } else {
            if (e.getSource() == vistaCartelera.getBtnCuenta()) {
                
            } else if (e.getSource() == vistaCartelera.getBtnCompras()) {
                
            }
        }
    }
    
    public void regresarInicioSesion(){
        VistaInicioSesion vista = new VistaInicioSesion();
        UsuarioDAO usuarioDAO  = new UsuarioDAO();
        Autenticador autenticador = new Autenticador();
        ControladorIniciarSesion ctrl = new ControladorIniciarSesion(vista, usuarioDAO, autenticador);
        ctrl.iniciar();
    }
    
    public void abrirVistaCuenta() {
    
    }
    
    
}
