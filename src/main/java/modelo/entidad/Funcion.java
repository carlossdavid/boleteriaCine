package modelo.entidad;

import java.time.LocalDate;
import java.time.LocalTime;

public class Funcion {

    private String id;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private boolean activa;

    public Funcion(
            String id,
            Pelicula pelicula,
            Sala sala,
            LocalDate fecha,
            LocalTime hora,
            double precio,
            boolean activa
    ) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.activa = activa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String toCsv() {
        return id + ","
                + pelicula.getId() + ","
                + sala.getId() + ","
                + fecha + ","
                + hora + ","
                + precio + ","
                + activa;
    }

    @Override
    public String toString() {
        return pelicula.getTitulo()
                + " - "
                + fecha
                + " "
                + hora
                + " - "
                + sala.getNombre();
    }
}