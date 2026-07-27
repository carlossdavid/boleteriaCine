package modelo.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.entidad.Compra;

/**
 *
 * @author carlo
 */
public class CompraDAO {

    private final File archivoCompras;
    private final File archivoFacturas;
    private final File carpetaFacturas;

    public CompraDAO() {
        archivoCompras = new File(
                "src\\main\\resources\\bd\\compras.txt"
        );

        archivoFacturas = new File(
                "src\\main\\resources\\bd\\facturas.txt"
        );

        carpetaFacturas = new File(
                "src\\main\\resources\\bd\\facturas_generadas"
        );
    }

    public ArrayList<Compra> getListaCompras() {

        ArrayList<Compra> compras = new ArrayList<>();

        if (!archivoCompras.exists()) {
            return compras;
        }

        try (FileReader fr = new FileReader(archivoCompras);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(",", -1);

                try {

                    // Nuevo formato
                    if (partes.length == 9) {

                        Compra compra = new Compra(
                                partes[0].trim(),
                                partes[1].trim(),
                                partes[2].trim(),
                                LocalDateTime.parse(
                                        partes[3].trim()
                                ),
                                Integer.parseInt(
                                        partes[4].trim()
                                ),
                                Integer.parseInt(
                                        partes[5].trim()
                                ),
                                partes[6].trim(),
                                Double.parseDouble(
                                        partes[7].trim()
                                ),
                                partes[8].trim()
                        );

                        compras.add(compra);

                    // Formato antiguo que ya tienes
                    } else if (partes.length == 5) {

                        Compra compra = new Compra(
                                partes[0].trim(),
                                partes[1].trim(),
                                LocalDateTime.parse(
                                        partes[2].trim()
                                ),
                                Double.parseDouble(
                                        partes[3].trim()
                                ),
                                partes[4].trim()
                        );

                        compras.add(compra);

                    } else {
                        System.out.println(
                                "Línea inválida en compras.txt: "
                                + linea
                        );
                    }

                } catch (Exception e) {
                    System.out.println(
                            "Error al cargar la compra: "
                            + linea
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al cargar las compras: "
                    + e.getMessage()
            );
        }

        return compras;
    }

    private String generarNuevoID() {

        ArrayList<Compra> compras = getListaCompras();
        int mayorId = 0;

        for (Compra compra : compras) {

            try {
                String numeroTexto =
                        compra.getId().replaceAll("\\D+", "");

                int numero = Integer.parseInt(numeroTexto);

                if (numero > mayorId) {
                    mayorId = numero;
                }

            } catch (Exception e) {
                System.out.println(
                        "ID de compra inválido: "
                        + compra.getId()
                );
            }
        }

        return String.format(
                "C%03d",
                mayorId + 1
        );
    }

    public boolean agregarCompra(Compra compra) {

        if (compra == null) {
            return false;
        }

        if (compra.getId() == null
                || compra.getId().isBlank()) {

            compra.setId(generarNuevoID());
        }

        try (FileWriter fw =
                    new FileWriter(archivoCompras, true);
             BufferedWriter bw =
                    new BufferedWriter(fw)) {

            bw.write(compra.toCsv());
            bw.newLine();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al guardar la compra: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public ArrayList<Compra> getListaComprasPorCliente(
            String idCliente
    ) {
        ArrayList<Compra> comprasCliente =
                new ArrayList<>();

        if (idCliente == null || idCliente.isBlank()) {
            return comprasCliente;
        }

        ArrayList<Compra> compras =
                getListaCompras();

        for (Compra compra : compras) {

            if (compra.getIdCliente()
                    .equalsIgnoreCase(idCliente.trim())) {

                comprasCliente.add(compra);
            }
        }

        return comprasCliente;
    }

    private String generarNuevoIDFactura() {

        int mayorId = 0;

        if (!archivoFacturas.exists()) {
            return "FAC001";
        }

        try (FileReader fr =
                    new FileReader(archivoFacturas);
             BufferedReader br =
                    new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(",", -1);

                try {
                    String numeroTexto =
                            partes[0].replaceAll("\\D+", "");

                    int numero =
                            Integer.parseInt(numeroTexto);

                    if (numero > mayorId) {
                        mayorId = numero;
                    }

                } catch (Exception e) {
                    System.out.println(
                            "ID de factura inválido: "
                            + linea
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format(
                "FAC%03d",
                mayorId + 1
        );
    }

    public String guardarFactura(
            Compra compra,
            String textoFactura
    ) {
        if (compra == null || textoFactura == null) {
            return null;
        }

        String idFactura =
                generarNuevoIDFactura();

        if (!carpetaFacturas.exists()) {
            carpetaFacturas.mkdirs();
        }

        File archivoTexto = new File(
                carpetaFacturas,
                idFactura + ".txt"
        );

        String facturaCompleta =
                "NÚMERO DE FACTURA: "
                + idFactura
                + "\n"
                + textoFactura;

        // Guardar el texto completo de la factura
        try (FileWriter fw =
                    new FileWriter(archivoTexto);
             BufferedWriter bw =
                    new BufferedWriter(fw)) {

            bw.write(facturaCompleta);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al guardar el texto de la factura"
            );

            return null;
        }

        // Guardar el resumen en facturas.txt
        try (FileWriter fw =
                    new FileWriter(archivoFacturas, true);
             BufferedWriter bw =
                    new BufferedWriter(fw)) {

            String linea =
                    idFactura
                    + "," + compra.getId()
                    + "," + compra.getIdCliente()
                    + "," + compra.getFechaCompra()
                    + "," + compra.getTotal()
                    + ",0.0"
                    + "," + compra.getTotal();

            bw.write(linea);
            bw.newLine();

            return idFactura;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Error al guardar la factura"
            );

            return null;
        }
    }
}