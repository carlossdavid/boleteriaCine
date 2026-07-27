package modelo.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.entidad.Butaca;

/**
 *
 * @author carlo
 */
public class AsientoFuncionDAO {

    private final File archivo;

    public AsientoFuncionDAO() {
        archivo = new File(
                "src\\main\\resources\\bd\\asientos_funcion.txt"
        );
    }

    public ArrayList<String> getButacasOcupadasPorFuncion(
            String idFuncion
    ) {
        ArrayList<String> codigosOcupados =
                new ArrayList<>();

        if (idFuncion == null || idFuncion.isBlank()) {
            return codigosOcupados;
        }

        if (!archivo.exists()) {
            return codigosOcupados;
        }

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(",", -1);

                if (partes.length == 2) {

                    String idFuncionArchivo =
                            partes[0].trim();

                    String codigoButaca =
                            partes[1].trim();

                    if (idFuncionArchivo.equalsIgnoreCase(
                            idFuncion
                    )) {
                        codigosOcupados.add(codigoButaca);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al cargar las butacas ocupadas: "
                    + e.getMessage()
            );
        }

        return codigosOcupados;
    }

    public boolean guardarButacasOcupadas(
            String idFuncion,
            ArrayList<Butaca> butacas
    ) {
        if (idFuncion == null
                || idFuncion.isBlank()
                || butacas == null
                || butacas.isEmpty()) {

            return false;
        }

        ArrayList<String> ocupadasActuales =
                getButacasOcupadasPorFuncion(idFuncion);

        try (FileWriter fw =
                    new FileWriter(archivo, true);
             BufferedWriter bw =
                    new BufferedWriter(fw)) {

            for (Butaca butaca : butacas) {

                String codigo = butaca.getCodigo();

                if (!ocupadasActuales.contains(codigo)) {

                    bw.write(
                            idFuncion
                            + ","
                            + codigo
                    );

                    bw.newLine();
                }
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al guardar las butacas ocupadas: "
                    + e.getMessage()
            );

            return false;
        }
    }
}