package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAO.PeliculaDAO;
import vista.VistaInicioSesion;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.usuarios.Cliente;
import modelo.entidad.usuarios.Trabajador;
import modelo.entidad.usuarios.Usuario;
import modelo.enums.RolUsuario;
import modelo.servicios.Autenticador;
import vista.VistaAdmin;
import vista.VistaCliente;
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
        if (usuario instanceof Cliente) {
            abrirInterfazCliente((Cliente) usuario);
        } else if (usuario instanceof Trabajador) {
            RolUsuario cargo = RolUsuario.valueOf(((Trabajador) usuario).getCargo());
            switch (cargo) {
                case ADMIN:
                    abrirInterfazAdmin();
                    break;
                case VENDEDOR: 
                    //abrirInterfazVendedor();
                    break;
                default:
                    
            }
        }
    }

    private void abrirInterfazCliente(Cliente cliente) {
        VistaCliente vista = new VistaCliente();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        
        ControladorVistaCliente ctrl = new ControladorVistaCliente(vista, peliculaDAO, cliente);
        ctrl.iniciar();
    }

    /*private void abrirInterfazVendedor() {
        VistaVendedor vista = new VistaVendedor();
    }*/

    private void abrirInterfazAdmin() {
        VistaAdmin vista = new VistaAdmin();
        ControladorAdmin ctrl = new ControladorAdmin(vista);
        ctrl.iniciar();
    }
    
    private void abrirInterfazAnonimo() {
        VistaCliente vista = new VistaCliente();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        ControladorVistaCliente ctrl = new ControladorVistaCliente(vista, peliculaDAO, null);
        ctrl.iniciarInvitado();
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
