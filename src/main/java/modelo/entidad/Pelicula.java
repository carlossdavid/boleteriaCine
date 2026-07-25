package modelo.entidad;

/**
 *
 * @author carlo
 */
public class Pelicula {
    private String id;
    private String titulo;
    private String genero;
    private int duracionMinutos;
    private String clasificacion;
    private String sinopsis;
    private String rutaImagen;
    private String urlTrailer;
    private boolean activa;

    public Pelicula( String id, String titulo, String genero, 
            int duracionMinutos, String clasificacion, String sinopsis,
            String rutaImagen, String urlTrailer, boolean activa
    ) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.rutaImagen = rutaImagen;
        this.urlTrailer = urlTrailer;
        this.activa = activa;
    }

    
    // -- GETTERS && SETTERS -- // 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }


    public int getDuracionMinutos() {
        return duracionMinutos;
    }


    public String getClasificacion() {
        return clasificacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String obtenerDuracionFormateada() {
        int horas = duracionMinutos / 60;
        int minutos = duracionMinutos % 60;

        return horas + "h " + minutos + "min";
    }

    public String toCsv() {
        return id + ","
                + titulo + ","
                + genero + ","
                + duracionMinutos + ","
                + clasificacion + ","
                + sinopsis + ","
                + rutaImagen + ","
                + urlTrailer + ","
                + activa;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
