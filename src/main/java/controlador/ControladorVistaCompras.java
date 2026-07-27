package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.DAO.CompraDAO;
import modelo.DAO.PeliculaDAO;
import modelo.entidad.Compra;
import modelo.entidad.usuarios.Cliente;
import vista.VistaCliente;
import vista.VistaCompras;
import vista.VistaCuenta;

public class ControladorVistaCompras implements ActionListener, ListSelectionListener {
    private VistaCompras vista;
    private CompraDAO compraDAO;
    private Cliente clienteUsuario;
    private ArrayList<Compra> comprasCliente;

    public ControladorVistaCompras(VistaCompras vista, CompraDAO compraDAO, Cliente clienteUsuario) {
        this.vista = vista;
        this.compraDAO = compraDAO;
        this.clienteUsuario = clienteUsuario;

        if (this.vista.getBtnCasete() != null) {
            this.vista.getBtnCasete().addActionListener(this);
        }
        if (this.vista.getBtnCuenta() != null) {
            this.vista.getBtnCuenta().addActionListener(this);
        }
        if (this.vista.getListaCompras() != null) {
            this.vista.getListaCompras().addListSelectionListener(this);
        }
    }

    public void iniciar() {
        cargarCompras();
        vista.setVisible(true);
    }

    private void cargarCompras() {
        if (clienteUsuario == null) return;

        ArrayList<Compra> todasLasCompras = compraDAO.getListaCompras();
        comprasCliente = new ArrayList<>();

        for (Compra c : todasLasCompras) {
            if (c.getIdCliente() != null && c.getIdCliente().equalsIgnoreCase(clienteUsuario.getId())) {
                comprasCliente.add(c);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] titulosCompras = new String[comprasCliente.size()];
        
        for (int i = 0; i < comprasCliente.size(); i++) {
            Compra c = comprasCliente.get(i);
            String fechaStr = (c.getFechaCompra() != null) ? c.getFechaCompra().format(formatter) : "Sin fecha";
            titulosCompras[i] = "Factura: " + c.getId() + " - " + fechaStr;
        }

        vista.getListaCompras().setListData(titulosCompras);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = vista.getListaCompras().getSelectedIndex();
            if (index >= 0 && index < comprasCliente.size()) {
                Compra compraSeleccionada = comprasCliente.get(index);
                mostrarFactura(compraSeleccionada);
            }
        }
    }

    private void mostrarFactura(Compra compra) {
        String idCompra = compra.getId(); 

       
        String idFactura = idCompra;
        if (idCompra.startsWith("C") || idCompra.startsWith("c")) {
            idFactura = "FAC" + idCompra.substring(1);
        }

        File archivoFactura = new File("src/main/resources/bd/facturas_generadas/" + idFactura + ".txt");

        if (!archivoFactura.exists()) {
            vista.getTxtFactura().setText("No se encontró el archivo de la factura: " + idFactura);
            return;
        }

        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoFactura))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            vista.getTxtFactura().setText(contenido.toString());
        } catch (IOException ex) {
            vista.getTxtFactura().setText("Error al cargar la factura: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCasete()) {
            irACartelera();
        } else if (e.getSource() == vista.getBtnCuenta()) {
            irACuenta();
        }
    }

    private void irACartelera() {
        vista.dispose();
        VistaCliente vistaCartelera = new VistaCliente();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        ControladorVistaCliente ctrl = new ControladorVistaCliente(vistaCartelera, peliculaDAO, clienteUsuario);
        ctrl.iniciar();
    }

    private void irACuenta() {
        vista.dispose();
        VistaCuenta vistaCuenta = new VistaCuenta();
        ControladorVistaCuenta ctrl = new ControladorVistaCuenta(vistaCuenta, clienteUsuario);
        ctrl.iniciar();
    }
}
