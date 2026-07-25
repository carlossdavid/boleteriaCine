package modelo.DAO;

import java.io.File;
import java.util.ArrayList;
import modelo.entidad.Pelicula;

/**
 *
 * @author carlo
 */
public class PeliculaDAO {
    private File archivo;
    
    public PeliculaDAO( ) {
        archivo = new File("src\\main\\resources\\bd\\peliculas.txt");
    }
    
    public ArrayList<Pelicula> getListaPeliculas() {
        return null;
    }
    
    
}
