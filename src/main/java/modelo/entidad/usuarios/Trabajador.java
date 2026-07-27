package modelo.entidad.usuarios;

import modelo.enums.RolUsuario;

/**
 *
 * @author carlo
 */
public class Trabajador extends Usuario {

    private String codigoEmpleado;
    private String cargo;

    public Trabajador(
            String id,
            String nombre,
            String apellido,
            String correo,
            String contrasena,
            String cargo
    ) {
        super(id, nombre, apellido, correo, contrasena);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toCSV() {
        
        return "" 
                + id 
                + ","
                + nombre
                + ","
                + apellido
                + ","
                + correo
                + ","
                + contrasena
                + ","
                + cargo;
    }

    
}
