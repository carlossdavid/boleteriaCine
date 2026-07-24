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
    private String contra; 
    private RolUsuario rol; 

    public Usuario(int id, String nombre, String apellido, String correo, String contraseña, RolUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contra = contraseña;
        this.rol = rol;
        
        
    }

    public String getCorreo() {
        return correo;
    }

    public String getContra() {
        return contra;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public String toCSV() {
        return "" + id + "," + nombre + "," + apellido + "," + correo + "," +
                contra + "," + rol;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    
    
}
