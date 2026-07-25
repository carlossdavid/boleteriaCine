package modelo.entidad.usuarios;


import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Compra;
import modelo.enums.RolUsuario;

public class Cliente extends Usuario {

    private List<Compra> historialCompras;

    public Cliente(
            String id,
            String nombre,
            String apellido,
            String correo,
            String contrasena
    ) {
        super(id, nombre, apellido, correo, contrasena);
        this.historialCompras = new ArrayList<>();
    }

    public List<Compra> getHistorialCompras() {
        return historialCompras;
    }

    
    // METODOS HISTORIAL DE COMPRAS 
    public void agregarCompra(Compra compra) {
        historialCompras.add(compra);
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
                + String.valueOf(RolUsuario.CLIENTE);
    }
}