package controlador;

import modelo.entidad.usuarios.Usuario;
import vista.VistaCuenta;

/**
 *
 * @author carlo
 */
public class ControladorVistaCuenta {
    private VistaCuenta vista;
    private Usuario usuario;

    public ControladorVistaCuenta(VistaCuenta vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
    }
    
    
    public void iniciar() {
        
    }
}
