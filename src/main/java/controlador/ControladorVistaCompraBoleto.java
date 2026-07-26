package controlador;

import modelo.DAO.CompraDAO;
import modelo.entidad.Pelicula;
import modelo.entidad.usuarios.Cliente;
import vista.VistaCompraBoleto;

/**
 *
 * @author carlo
 */
public class ControladorVistaCompraBoleto {
    private VistaCompraBoleto vista;
    private Pelicula pelicula; 
    private Cliente cliente; 
    private CompraDAO compraDAO; 

    public ControladorVistaCompraBoleto(
            VistaCompraBoleto vista,
            Cliente cliente,
            CompraDAO compraDAO,
            Pelicula pelicula) 
    {
        this.cliente = cliente;
        this.compraDAO = compraDAO;
        this.vista = vista;
        this.pelicula = pelicula;
    }
    
    public void iniciar() {
        vista.setVisible(true);
        vista.cargarDatosPeli(pelicula);
    }
    
    public void iniciarInvitado() {
        iniciar();
    }
        
    
}
