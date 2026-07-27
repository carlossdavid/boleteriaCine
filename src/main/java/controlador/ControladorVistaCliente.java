package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import modelo.DAO.CompraDAO;
import modelo.DAO.PeliculaDAO;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.Pelicula;
import modelo.entidad.usuarios.Cliente;
import modelo.servicios.Autenticador;
import vista.modular.TarjetaPelicula;
import vista.VistaCliente;
import vista.VistaCompraBoleto;
import vista.VistaCompras;
import vista.VistaCuenta;
import vista.VistaInicioSesion;

/**
 *
 * @author carlo
 */
public class ControladorVistaCliente implements ActionListener{
    private VistaCliente vistaCartelera; 
    private PeliculaDAO peliculaDAO;
    private Cliente clienteUsuario; 

    public ControladorVistaCliente(VistaCliente vistaCartelera, PeliculaDAO peliculaDAO, Cliente clienteUsuario) {
        this.vistaCartelera = vistaCartelera;
        this.peliculaDAO = peliculaDAO;
        this.clienteUsuario = clienteUsuario;
        
        vistaCartelera.addBtnCuentaListener(this);
        vistaCartelera.addBtnComprasListener(this);
        vistaCartelera.addBtnCarteleraListener(this);
        vistaCartelera.addBtnCerrarSesionListener(this);
    }
    
    public void iniciar() {
        
        // Cargar Nombre usuario Bienvenida 
        if (clienteUsuario!= null) vistaCartelera.setTxtBienvenidaNombre(clienteUsuario.getNombre());
        // Cargar Películas 
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas = peliculaDAO.getListaPeliculas();
        if (peliculas == null) {
            vistaCartelera.mostrarError("No se pudo cargar las películas");
            return;
        }
        ArrayList<TarjetaPelicula> tarjetas = vistaCartelera.mostrarPeliculas(peliculas);
        agregarListenersTarjetas(tarjetas); 
        vistaCartelera.setVisible(true);
        
    }
    
    public void agregarListenersTarjetas(ArrayList<TarjetaPelicula> tarjetas) {
         for (TarjetaPelicula tarjeta : tarjetas) {
            tarjeta.agregarListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    Pelicula pelicula =
                            tarjeta.getPelicula();
                    
                    abrirVistaComprar(pelicula);
                    vistaCartelera.cerrar();
                }
            });
        }
    }

    public void abrirVistaComprar(Pelicula pelicula) {
        VistaCompraBoleto vista = new VistaCompraBoleto();
        
        ControladorVistaCompraBoleto ctrl = new ControladorVistaCompraBoleto(vista, this.vistaCartelera, clienteUsuario, pelicula);
        
        if (clienteUsuario == null) {
            ctrl.iniciar();
        } else {
            ctrl.iniciarInvitado();
        }
    }
    
    
    public void iniciarInvitado() {
        this.iniciar();
        vistaCartelera.configurarInvitado();
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        //Cerrar Sesion
        if(e.getSource() == vistaCartelera.getBtnCerrarSesion()) {
            regresarInicioSesion();
            vistaCartelera.cerrar();
        }
        
        // 
        if (clienteUsuario == null) {
            if (e.getSource() == vistaCartelera.getBtnCompras() 
                    || e.getSource() == vistaCartelera.getBtnCuenta()){
                vistaCartelera.mostrarError("Por favor inicia sesión para acceder a la función");
            }
        } else {
            if (e.getSource() == vistaCartelera.getBtnCuenta()) {
                abrirVistaCuenta();
                vistaCartelera.cerrar();
            } else if (e.getSource() == vistaCartelera.getBtnCompras()) {
                abrirVistaCompras();
                vistaCartelera.cerrar();
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
        VistaCuenta vista = new VistaCuenta();
        ControladorVistaCuenta ctrl = new ControladorVistaCuenta(vista, clienteUsuario);
        ctrl.iniciar();
    }
    
    public void abrirVistaCompras() {
        VistaCompras vista = new VistaCompras();
        CompraDAO compraDAO = new CompraDAO();
        ControladorVistaCompras ctrl = new ControladorVistaCompras(vista, compraDAO, clienteUsuario);
        ctrl.iniciar();
    }
    
}
