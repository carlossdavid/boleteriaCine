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

public class ControladorVistaCliente implements ActionListener {
    private VistaCliente vistaCartelera; 
    private PeliculaDAO peliculaDAO;
    private Cliente clienteUsuario; 

    public ControladorVistaCliente(VistaCliente vistaCartelera, PeliculaDAO peliculaDAO, Cliente clienteUsuario) {
        this.vistaCartelera = vistaCartelera;
        this.peliculaDAO = peliculaDAO;
        this.clienteUsuario = clienteUsuario;
        
        if (vistaCartelera != null) {
            vistaCartelera.addBtnCuentaListener(this);
            vistaCartelera.addBtnComprasListener(this);
            vistaCartelera.addBtnCarteleraListener(this);
            vistaCartelera.addBtnCerrarSesionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistaCartelera != null) {
            // Cerrar Sesión
            if (e.getSource() == vistaCartelera.getBtnCerrarSesion()) {
                regresarInicioSesion();
                vistaCartelera.cerrar();
                return;
            }
            
            // Verificación de sesión invitada vs logueada
            if (clienteUsuario == null) {
                if (e.getSource() == vistaCartelera.getBtnCompras() 
                        || e.getSource() == vistaCartelera.getBtnCuenta()) {
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
    }

    public void iniciar() {
        if (clienteUsuario != null && vistaCartelera != null) {
            vistaCartelera.setTxtBienvenidaNombre(clienteUsuario.getNombre());
        }
        
        if (peliculaDAO != null && vistaCartelera != null) {
            ArrayList<Pelicula> peliculas = peliculaDAO.getListaPeliculas();
            if (peliculas == null || peliculas.isEmpty()) {
                vistaCartelera.mostrarError("No se pudieron cargar las películas");
                return;
            }
            ArrayList<TarjetaPelicula> tarjetas = vistaCartelera.mostrarPeliculas(peliculas);
            agregarListenersTarjetas(tarjetas); 
            vistaCartelera.setVisible(true);
        }
        
    }
    
    public void agregarListenersTarjetas(ArrayList<TarjetaPelicula> tarjetas) {
        if (tarjetas == null) return;
        for (TarjetaPelicula tarjeta : tarjetas) {
            tarjeta.agregarListener(new MouseAdapter() {
                
                @Override
                public void mousePressed(MouseEvent e) {
                    Pelicula pelicula = tarjeta.getPelicula();
                    abrirVistaComprar(pelicula);
                    if (vistaCartelera != null) vistaCartelera.cerrar();
                }
            });
        }
    }

    public void abrirVistaComprar(Pelicula pelicula) {
        VistaCompraBoleto vistaBoleto = new VistaCompraBoleto();
        CompraDAO compraDAO = new CompraDAO(); 
        
        ControladorVistaCompraBoleto ctrl = new ControladorVistaCompraBoleto(vistaBoleto, clienteUsuario, compraDAO, pelicula);
        
        if (clienteUsuario != null) {
            ctrl.iniciar();
        } else {
            ctrl.iniciarInvitado();
        }
    }
    
    
    public void iniciarInvitado() {
        this.iniciar();
        if (vistaCartelera != null) vistaCartelera.configurarInvitado();
    }
    
    public void regresarInicioSesion() {
        VistaInicioSesion vistaIni = new VistaInicioSesion();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Autenticador autenticador = new Autenticador();
        ControladorIniciarSesion ctrl = new ControladorIniciarSesion(vistaIni, usuarioDAO, autenticador);
        ctrl.iniciar();
    }
    
    public void abrirVistaCuenta() {
        VistaCuenta vistaC = new VistaCuenta();
        ControladorVistaCuenta ctrl = new ControladorVistaCuenta(vistaC, clienteUsuario);
        ctrl.iniciar();
    }
    
    public void abrirVistaCompras() {
        VistaCompras vistaComp = new VistaCompras();
        CompraDAO compraDAO = new CompraDAO();
        ControladorVistaCompras ctrl = new ControladorVistaCompras(vistaComp, compraDAO, clienteUsuario);
        ctrl.iniciar();
    }
    
}