package modelo.entidad;

import modelo.enums.RolUsuario;

/**
 *
 * @author carlo
 */
public class Usuario {
    private int id; 
    private String nombre;
    private String apellido; 
    private String correo;
    private String contraseña; 
    private RolUsuario rol; 

    public Usuario(int id, String nombre, String apellido, String correo, String contraseña, RolUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        
        
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public RolUsuario getRol() {
        return rol;
    }
    
    

    
    
    
}
