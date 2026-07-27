package modelo.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import modelo.entidad.Pelicula;
import modelo.entidad.usuarios.Usuario;
import modelo.enums.RolUsuario;

/**
 *
 * @author carlo
 */
public class PeliculaDAO {
    private File archivo;
    private final String carpetaPortadas = "src/main/resources/portadasPeliculas/";
    
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
    
    
    private String generarNuevoID() {
        ArrayList<Pelicula> lista = getListaPeliculas();
        if (lista.isEmpty()) return "P001";
        try {
            Pelicula ultima = lista.get(lista.size() - 1);
            String idNum = ultima.getId().replaceAll("\\D+", ""); // Extrae sólo números
            int num = Integer.parseInt(idNum) + 1;
            return String.format("P%03d", num);
        } catch (Exception e) {
            return "P" + (lista.size() + 1);
        }
    }

    private boolean guardarTodas(ArrayList<Pelicula> peliculas) {
        try (FileWriter fw = new FileWriter(archivo, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (Pelicula p : peliculas) {
                String linea = String.format("%s,%s,%s,%d,%s,%s,%s,%s,%b",
                        p.getId(), p.getTitulo(), p.getGenero(),
                        p.getDuracionMinutos(), p.getClasificacion(),
                        p.getSinopsis(), p.getRutaImagen(),
                        p.getUrlTrailer(), p.isActiva());
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarPelicula(Pelicula pelicula, File archivoImagenOrigen) {
        if (pelicula.getId() == null || pelicula.getId().isEmpty()) {
            pelicula.setId(generarNuevoID());
        }

        if (archivoImagenOrigen != null && archivoImagenOrigen.exists()) {
            String nombreImagen = pelicula.getId() + "_" + archivoImagenOrigen.getName();
            File destino = new File(carpetaPortadas + nombreImagen);
            try {
                Files.copy(archivoImagenOrigen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                pelicula.setRutaImagen(nombreImagen);
            } catch (IOException e) {
                System.err.println("Error al copiar la portada: " + e.getMessage());
            }
        }

        ArrayList<Pelicula> lista = getListaPeliculas();
        lista.add(pelicula);
        return guardarTodas(lista);
    }

    public boolean actualizarPelicula(Pelicula peliculaEditada, File archivoImagenOrigen) {
        ArrayList<Pelicula> lista = getListaPeliculas();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equalsIgnoreCase(peliculaEditada.getId())) {
                if (archivoImagenOrigen != null && archivoImagenOrigen.exists()) {
                    String nombreImagen = peliculaEditada.getId() + "_" + archivoImagenOrigen.getName();
                    File destino = new File(carpetaPortadas + nombreImagen);
                    try {
                        Files.copy(archivoImagenOrigen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        peliculaEditada.setRutaImagen(nombreImagen);
                    } catch (IOException e) {
                        System.err.println("Error al actualizar la portada: " + e.getMessage());
                    }
                }
                lista.set(i, peliculaEditada);
                return guardarTodas(lista);
            }
        }
        return false;
    }

    public boolean eliminarPelicula(String idPelicula) {
        ArrayList<Pelicula> lista = getListaPeliculas();
        boolean eliminado = lista.removeIf(p -> p.getId().equalsIgnoreCase(idPelicula));
        if (eliminado) {
            return guardarTodas(lista);
        }
        return false;
    }
    
    
    public Pelicula buscarPorId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        ArrayList<Pelicula> peliculas = getListaPeliculas();

        for (Pelicula p : peliculas) {
            if (p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }

        return null;
    }
    
    
}
