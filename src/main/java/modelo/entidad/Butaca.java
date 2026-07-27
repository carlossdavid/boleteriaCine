package modelo.entidad;

/**
 *
 * @author carlo
 */
import modelo.enums.EstadoButaca;

public class Butaca {

    private String id;
    private String fila;
    private int numero;
    private EstadoButaca estado;

    public Butaca(
            String id,
            String fila,
            int numero,
            EstadoButaca estado
    ) {
        this.id = id;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoButaca getEstado() {
        return estado;
    }

    public void setEstado(EstadoButaca estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return fila + numero;
    }
}