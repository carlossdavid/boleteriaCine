package modelo.entidad.usuarios;

import modelo.enums.RolUsuario;

/**
 *
 * @author carlo
 */
public abstract class Usuario {

    protected String id;
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String contrasena;


    public Usuario(
            String id,
            String nombre,
            String apellido,
            String correo,
            String contrasena
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    
    // GETTERS 

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public abstract String toCSV(); 

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    
    
}
