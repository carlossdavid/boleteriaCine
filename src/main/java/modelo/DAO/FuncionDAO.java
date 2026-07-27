package modelo.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.entidad.Funcion;
import modelo.entidad.Pelicula;
import modelo.entidad.Sala;


/**
 *
 * @author carlo
 */
public class FuncionDAO {
    private File archivo;
    private PeliculaDAO peliculaDAO;
    private SalaDAO salaDAO;
    
    public FuncionDAO( SalaDAO salaDAO, PeliculaDAO peliculaDAO) {
        archivo = new File("src\\main\\resources\\bd\\funciones.txt");
        this.peliculaDAO = peliculaDAO;
        this.salaDAO = salaDAO;
    }
    
    // Obtener los usuarios como una lista 
    public ArrayList<Funcion> getListaFunciones() {
        
        ArrayList<Funcion> funciones = new ArrayList<>();
        
        // Verificar si el archivo existe sino devuelve una lista vacía
        if (!archivo.exists()) return funciones;        
        
        // Leemos el archivo 
        try (FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(",", -1);

                if (partes.length == 7) {

                    String idFuncion = partes[0].trim();
                    String idPelicula = partes[1].trim();
                    String idSala = partes[2].trim();

                    Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
                    Sala sala = salaDAO.buscarPorId(idSala);

                    LocalDate fecha = LocalDate.parse(partes[3].trim());
                    LocalTime hora = LocalTime.parse(partes[4].trim());
                    double precio = Double.parseDouble(partes[5].trim());
                    boolean activa = Boolean.parseBoolean(partes[6].trim());

                    if (pelicula == null || sala == null) {
                        System.out.println(
                                "No se encontró la película o la sala de la función: "
                                + idFuncion
                        );
                        continue;
                    }

                    Funcion f = new Funcion(
                            idFuncion,
                            pelicula,
                            sala,
                            fecha,
                            hora,
                            precio,
                            activa
                    );

                    funciones.add(f);

                } else {
                    System.out.println("Línea inválida: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return funciones;
    }

    public ArrayList<Funcion> getListaFuncionesPorPelicula(Pelicula pelicula) {
        ArrayList<Funcion> funcionesPorPelicula = new ArrayList<>();

        if (pelicula == null) {
            return funcionesPorPelicula;
        }

        ArrayList<Funcion> funciones = getListaFunciones();

        for (Funcion funcion : funciones) {
            if (funcion.getPelicula().getId().equalsIgnoreCase(pelicula.getId())) {
                funcionesPorPelicula.add(funcion);
            }
        }

        return funcionesPorPelicula;
    }
}
