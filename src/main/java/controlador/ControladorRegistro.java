package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.usuarios.Cliente;
import modelo.entidad.usuarios.Usuario;
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
        
        // BOTON INICIO SESION 
        if (e.getSource() == vistaRegistrate.getBtnIniciarSesion()) {
            abrirInterfazInicioSesion();
            vistaRegistrate.cerrar();
        } 
        // BOTON REGISTRATE 
        else if (e.getSource() == vistaRegistrate.getBtnRegistrarse()) {
            
            // Si pasa las validaciones registrar usuario 
            
            String [] contenidoCampos = new String[4];
            contenidoCampos[0]  = vistaRegistrate.getTxtNombreCampo().getText().trim();
            contenidoCampos[1] = vistaRegistrate.getTxtApellidoCampo().getText().trim();
            contenidoCampos[2]= vistaRegistrate.getTxtCorreoCampo().getText().trim();
            char[] contraChars = vistaRegistrate.getTxtContraCampo().getPassword();
            contenidoCampos[3]= new String(contraChars);
            
            if (!validarCampos(contenidoCampos)) return;
            
            
            boolean usuarioYaExiste = usuarioDAO.buscarPorCorreo(contenidoCampos[2]) != null;
            
            if (usuarioYaExiste) { 
                vistaRegistrate.mostrarError("Correo existente, registrate con otro correo o inicia sesión");
                return;
            }
            
            
            
            Usuario usuarioARegistrar = new Cliente(
                    "U000",
                    contenidoCampos[0],
                    contenidoCampos[1],
                    contenidoCampos[2],
                    contenidoCampos[3]
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
    
    public boolean validarCampos(String [] contenidoCampos) {
        boolean flag = true;
        
        boolean algunoVacio = (contenidoCampos[0].equals(vistaRegistrate.PLACEHOLDER_TXT_NOMBRE) 
                || contenidoCampos[1].equals(vistaRegistrate.PLACEHOLDER_TXT_APELLIDO)
                || contenidoCampos[2].equals(vistaRegistrate.PLACEHOLDER_TXT_CORREO)
                || contenidoCampos[3].equals(vistaRegistrate.PLACEHOLDER_TXT_CONTRA));
        
        
        if (algunoVacio) {
            vistaRegistrate.mostrarError("Llena todos los campos por favor");
            flag=false;
        }
        
        return flag; 
    }
    
    
    
}
    
    

