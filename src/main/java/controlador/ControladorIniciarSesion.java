package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaInicioSesion;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.Usuario;
import modelo.servicios.Autenticador;
import vista.VistaAdmin;
import vista.VistaCartelera;
import vista.VistaEmpleado;
import vista.VistaRegistrate;

/**
 *
 * @author Carlos Ortega 
 */
public class ControladorIniciarSesion implements ActionListener {
    private VistaInicioSesion vistaIniciarSesion; 
    private UsuarioDAO usuarioDAO; 
    private Autenticador autenticador; 

    public ControladorIniciarSesion(VistaInicioSesion vistaIniciarSesion, 
        UsuarioDAO usuarioDAO, Autenticador autenticador) {
        this.vistaIniciarSesion = vistaIniciarSesion;
        this.usuarioDAO = usuarioDAO;
        this.autenticador = autenticador;
        
        // Listeners de los botones 
        this.vistaIniciarSesion.addBtnIngresarListener(this);
        this.vistaIniciarSesion.addBtnRegistrateListener(this);
        this.vistaIniciarSesion.addBtnSinSesionListener(this);
    }
    
    public void iniciar(){
        vistaIniciarSesion.configurarPropiedadesVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaIniciarSesion.getBtnIngresar()){
            if (!verificarCampos()) return;
            
            String usuariotxt = vistaIniciarSesion.getTxtUserCampo().getText();
            char[] contraChars = vistaIniciarSesion.getTxtContraCampo().getPassword();
            String contratxt = new String(contraChars);
            
            // Crea usuario si esque coincide 
            Usuario usuarioActivo = autenticador.autenticar(usuariotxt, contratxt);
            
            if (usuarioActivo == null) {
                vistaIniciarSesion.mostrarError("Contraseña o usuario incorrectos.");
            } else {
                abrirVistaSegunRol(usuarioActivo);
                vistaIniciarSesion.cerrar();
            }
        } else if (e.getSource() == vistaIniciarSesion.getBtnRegistrate()) {
            abrirInterfazRegistrate();
            vistaIniciarSesion.cerrar();    
        }else if (e.getSource() == vistaIniciarSesion.getBtnSinSesion()) {
            abrirInterfazAnonimo();
            vistaIniciarSesion.cerrar();    
        }
        
    }
    
    // Métodos 
    private void abrirVistaSegunRol(Usuario usuario) {
        switch (usuario.getRol()) {
            case CLIENTE -> abrirInterfazCliente();
            case EMPLEADO -> abrirInterfazEmpelado();
            case ADMINISTRADOR -> abrirInterfazAdmin();
        }
    }

    private void abrirInterfazCliente() {
        VistaCartelera vista = new VistaCartelera();
        
    }

    private void abrirInterfazEmpelado() {
        VistaEmpleado vista = new VistaEmpleado();
    }

    private void abrirInterfazAdmin() {
        VistaAdmin vista = new VistaAdmin();
    }
    
    private void abrirInterfazAnonimo() {
        VistaCartelera vista = new VistaCartelera(true);
    }
    
    private void abrirInterfazRegistrate() {
        VistaRegistrate vista = new VistaRegistrate();
        ControladorRegistro ctrl = new ControladorRegistro(usuarioDAO, vista);
        ctrl.iniciar();
    }
    /** 
     * @return true si son válidos y false si son inválidos
     */
    public boolean verificarCampos() {
        boolean flag = true; 
        
        String contenidoCampoUsuario = vistaIniciarSesion.getTxtUserCampo().getText().trim();
        char[] contraChars = vistaIniciarSesion.getTxtContraCampo().getPassword();
        String contenidoCampoContra = new String(contraChars);
        
        if (contenidoCampoUsuario.isEmpty() || contenidoCampoContra.isEmpty() 
                || contenidoCampoUsuario.equals(vistaIniciarSesion.getPLACEHOLDER_TXT_USUARIO())
                || contenidoCampoContra.equals(vistaIniciarSesion.getPLACEHOLDER_TXT_CONTRA())) {
            vistaIniciarSesion.mostrarError("CAMPOS VACIOS: Por favor ingrese sus datos");
            flag = false;
        }
        
        return flag; 
    }
    
 
    
}
