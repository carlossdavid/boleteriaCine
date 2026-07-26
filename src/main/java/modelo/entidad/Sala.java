package modelo.entidad;

import modelo.enums.TipoSala;

/**
 *
 * @author carlo
 */
public class Sala {

    private String id;
    private String nombre;
    private int capacidad;
    private TipoSala tipoSala;
    private boolean activa;

    public Sala(
            String id,
            String nombre,
            int capacidad,
            TipoSala tipoSala,
            boolean activa
    ) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipoSala = tipoSala;
        this.activa = activa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero"
            );
        }

        this.capacidad = capacidad;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String toCsv() {
        return id + ","
                + nombre + ","
                + capacidad + ","
                + tipoSala + ","
                + activa;
    }

    @Override
    public String toString() {
        return nombre + " - " + tipoSala;
    }
}