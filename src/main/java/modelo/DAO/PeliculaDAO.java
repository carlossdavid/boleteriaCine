package modelo.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import modelo.entidad.Pelicula;
import modelo.entidad.Usuario;
import modelo.enums.RolUsuario;

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
        
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        
        // Verificar si el archivo existe sino devuelve una lista vacía
        if (!archivo.exists()) return peliculas;        
        
        // Leemos el archivo 
        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while((linea = br.readLine()) != null) {
                String [] partes = linea.split(",");
                if (partes.length == 9) {
                    
                    Pelicula p = new Pelicula(
                            partes[0],
                            partes[1],
                            partes[2], 
                            Integer.parseInt(partes[3]),
                            partes[4],
                            partes[5],
                            partes[6],
                            partes[7], 
                            Boolean.parseBoolean(partes[8])
                    );
                    peliculas.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return peliculas;
    }
    
    
}
