package controlador;

import java.util.ArrayList;
import modelo.DAO.PeliculaDAO;
import modelo.entidad.Pelicula;
import vista.VistaCartelera;

/**
 *
 * @author carlo
 */
public class ControladorCartelera {
    private VistaCartelera vistaCartelera; 
    private PeliculaDAO peliculaDAO;

    public ControladorCartelera(VistaCartelera vistaCartelera, PeliculaDAO peliculaDAO) {
        this.vistaCartelera = vistaCartelera;
        this.peliculaDAO = peliculaDAO;
    }
    
    public void iniciar() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas = peliculaDAO.getListaPeliculas();
        if (peliculas == null) {
            vistaCartelera.mostrarError("No se pudo cargar las películas");
            return;
        }
        vistaCartelera.mostrarPeliculas(peliculas);
        vistaCartelera.setVisible(true);
    }
    
    
    
}
