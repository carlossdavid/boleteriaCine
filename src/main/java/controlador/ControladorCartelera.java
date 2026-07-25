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
    
    public void inciar() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas = peliculaDAO.getListaPeliculas(); 
        vistaCartelera.mostrarPeliculas(peliculas);
    }
    
    
    
}
