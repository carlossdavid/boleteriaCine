package modelo.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import modelo.entidad.Sala;
import modelo.enums.TipoSala;

public class SalaDAO {

    private final File archivo;

    public SalaDAO() {
        archivo = new File("src\\main\\resources\\bd\\salas.txt");
    }

    public ArrayList<Sala> getListaSalas() {
        ArrayList<Sala> salas = new ArrayList<>();

        // Verificar si el archivo existe; si no, devuelve una lista vacía
        if (!archivo.exists()) {
            return salas;
        }

        // Leer el archivo
        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(",", -1);

                if (partes.length == 5) {

                    String idSala = partes[0].trim();
                    String nombre = partes[1].trim();
                    int capacidad = Integer.parseInt(partes[2].trim());

                    TipoSala tipoSala = TipoSala.valueOf(
                            partes[3].trim().toUpperCase()
                    );

                    boolean activa = Boolean.parseBoolean(
                            partes[4].trim()
                    );

                    Sala sala = new Sala(
                            idSala,
                            nombre,
                            capacidad,
                            tipoSala,
                            activa
                    );

                    salas.add(sala);

                } else {
                    System.out.println(
                            "Línea inválida en salas.txt: " + linea
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al cargar las salas"
                    + e.getMessage()
            );
        } 

        return salas;
    }
    
    public Sala buscarPorId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        ArrayList<Sala> salas = getListaSalas();

        for (Sala sala : salas) {
            if (sala.getId().equalsIgnoreCase(id.trim())) {
                return sala;
            }
        }

        return null;
    }
    
    public Sala buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return null;
        }

        ArrayList<Sala> salas = getListaSalas();

        for (Sala sala : salas) {
            if (sala.getNombre().equalsIgnoreCase(nombre.trim())) {
                return sala;
            }
        }

        return null;
    }
   
}