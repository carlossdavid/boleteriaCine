package modelo.servicios;

/**
 *
 * @author carlo
 */
import modelo.DAO.UsuarioDAO;
import modelo.entidad.usuarios.Usuario;


public class Autenticador {
    private UsuarioDAO usuarioDAO;
    
    public Autenticador() {
        usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario autenticar (String correo, String contra) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
        
        if (usuario == null) {
            return null; 
        }
        
        if (usuario.getContrasena().equals(contra)){
            return usuario;
        }
        return null; 
    }
    
}
