package controlador;

import modelo.DAO.CompraDAO;
import modelo.entidad.usuarios.Cliente;
import vista.VistaCompras;

/**
 *
 * @author carlo
 */
public class ControladorVistaCompras {
    private VistaCompras vista; 
    private CompraDAO compraDAO;
    private Cliente cliente; 

    public ControladorVistaCompras(VistaCompras vista, CompraDAO compraDAO, Cliente cliente) {
        this.vista = vista;
        this.compraDAO = compraDAO;
        this.cliente = cliente;
    }
    
    public void iniciar() {
        
    }
    
    
    
}
