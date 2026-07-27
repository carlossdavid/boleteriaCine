package modelo.entidad;

import java.time.LocalDateTime;

/**
 *
 * @author carlo
 */
public class Compra {

    private String id;
    private String idCliente;
    private String idFuncion;
    private LocalDateTime fechaCompra;
    private int cantidadAdultos;
    private int cantidadNinos;
    private String butacas;
    private double total;
    private String estado;

    public Compra(
            String id,
            String idCliente,
            String idFuncion,
            LocalDateTime fechaCompra,
            int cantidadAdultos,
            int cantidadNinos,
            String butacas,
            double total,
            String estado
    ) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
        this.fechaCompra = fechaCompra;
        this.cantidadAdultos = cantidadAdultos;
        this.cantidadNinos = cantidadNinos;
        this.butacas = butacas;
        this.total = total;
        this.estado = estado;
    }

    // Constructor para poder leer las compras antiguas
    public Compra(
            String id,
            String idCliente,
            LocalDateTime fechaCompra,
            double total,
            String estado
    ) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFuncion = "";
        this.fechaCompra = fechaCompra;
        this.cantidadAdultos = 0;
        this.cantidadNinos = 0;
        this.butacas = "";
        this.total = total;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdFuncion() {
        return idFuncion;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public int getCantidadAdultos() {
        return cantidadAdultos;
    }

    public int getCantidadNinos() {
        return cantidadNinos;
    }

    public String getButacas() {
        return butacas;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toCsv() {
        return id
                + "," + idCliente
                + "," + idFuncion
                + "," + fechaCompra
                + "," + cantidadAdultos
                + "," + cantidadNinos
                + "," + butacas
                + "," + total
                + "," + estado;
    }
}