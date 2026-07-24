package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.Usuario;
import modelo.enums.RolUsuario;
import modelo.servicios.Autenticador;
import vista.VistaInicioSesion;
import vista.VistaRegistrate;

/**
 *
 * @author carlo
 */
public class ControladorRegistro implements ActionListener{
    private UsuarioDAO usuarioDAO; 
    private VistaRegistrate vistaRegistrate; 

    public ControladorRegistro(UsuarioDAO usuarioDAO, VistaRegistrate vistaRegistrate) {
        this.usuarioDAO = usuarioDAO;
        this.vistaRegistrate = vistaRegistrate;
        
        // Add event listeners 
        this.vistaRegistrate.addBtnRegistrarseListener(this);
        this.vistaRegistrate.addBtnIniciarSesion(this);
    }
    
    
    public void iniciar() {
        vistaRegistrate.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaRegistrate.getBtnIniciarSesion()) {
            abrirInterfazInicioSesion();
            vistaRegistrate.cerrar();
        } else if (e.getSource() == vistaRegistrate.getBtnRegistrarse()) {
            if (!validarCampos()) return;
            String correo = vistaRegistrate.getTxtCorreoCampo().getText().trim();
            
            boolean usuarioYaExiste = usuarioDAO.buscarPorCorreo(correo) != null;
            
            if (usuarioYaExiste) { 
                vistaRegistrate.mostrarError("Correo existente, registrate con otro correo o inicia sesión");
                return;
            }
            
            // Si pasa las validaciones registrar usuario 
            String nombre  = vistaRegistrate.getTxtNombreCampo().getText().trim();
            String apellido = vistaRegistrate.getTxtApellidoCampo().getText().trim();
            char[] contraChars = vistaRegistrate.getTxtContraCampo().getPassword();
            String contra = new String(contraChars);
            Usuario usuarioARegistrar = new Usuario (
                    0,
                    nombre,
                    apellido,
                    correo,
                    contra,
                    RolUsuario.CLIENTE
            );
            
            if (usuarioDAO.agregarUsuario(usuarioARegistrar)) {
                vistaRegistrate.mostrarMensaje(usuarioARegistrar.getNombre() + ", tu cuenta ha sido registrada con exito");
                vistaRegistrate.resetearCampos();
            };
            
            
        }
    }
    
    public void abrirInterfazInicioSesion () {
        VistaInicioSesion vista = new VistaInicioSesion();
        Autenticador autenticador = new Autenticador();
        
        ControladorIniciarSesion ctrlInicio = new ControladorIniciarSesion(
                vista,
                usuarioDAO,
                autenticador
        );
        
        ctrlInicio.iniciar();
        
    }
    
    public boolean validarCampos() {
        boolean flag = true;
        String nombre, apellido, contra, correo;
        
        nombre  = vistaRegistrate.getTxtNombreCampo().getText().trim();
        apellido = vistaRegistrate.getTxtApellidoCampo().getText().trim();
        correo = vistaRegistrate.getTxtCorreoCampo().getText().trim();
        char[] contraChars = vistaRegistrate.getTxtContraCampo().getPassword();
        contra = new String(contraChars);
        
   
        
        boolean algunoVacio = (nombre.equals(vistaRegistrate.PLACEHOLDER_TXT_NOMBRE) 
                || apellido.equals(vistaRegistrate.PLACEHOLDER_TXT_APELLIDO)
                || correo.equals(vistaRegistrate.PLACEHOLDER_TXT_CORREO)
                || correo.equals(vistaRegistrate.PLACEHOLDER_TXT_CONTRA));
        
        
        if (algunoVacio) {
            vistaRegistrate.mostrarError("Llena todos los campos por favor");
            flag=false;
        }
        
        return flag; 
    }
    
    
    
}
    
    

