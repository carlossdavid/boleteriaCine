package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.DAO.PeliculaDAO;
import modelo.entidad.Pelicula;
import vista.VistaAdmin;
import vista.VistaAdminPeliculas;

public class ControladorAdminPeliculas implements ActionListener {

    private final VistaAdminPeliculas vista;
    private final PeliculaDAO peliculaDAO;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;
    private File archivoImagenSeleccionado;

    public ControladorAdminPeliculas(VistaAdminPeliculas vista) {
        this.vista = vista;
        this.peliculaDAO = new PeliculaDAO();

        
        if (this.vista.getBtnRegresar() != null) this.vista.getBtnRegresar().addActionListener(this);
        if (this.vista.getBtnAnadir() != null) this.vista.getBtnAnadir().addActionListener(this);
        if (this.vista.getBtnActualizar() != null) this.vista.getBtnActualizar().addActionListener(this);
        if (this.vista.getBtnEliminar() != null) this.vista.getBtnEliminar().addActionListener(this);
        if (this.vista.getBtnSeleccionarPortada() != null) this.vista.getBtnSeleccionarPortada().addActionListener(this);

       
        inicializarTabla();
        cargarPeliculasEnTabla();

        
        KeyAdapter listenerFiltro = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                aplicarFiltroDinamico();
            }
        };

        if (vista.getTxtTitulo() != null) vista.getTxtTitulo().addKeyListener(listenerFiltro);
        if (vista.getTxtGenero() != null) vista.getTxtGenero().addKeyListener(listenerFiltro);

       
        this.vista.getTblPeliculas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "Título", "Género", "Duración", "Clasificación", "Estado", "Portada"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.getTblPeliculas().setModel(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        vista.getTblPeliculas().setRowSorter(sorter);
    }

    private void cargarPeliculasEnTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<Pelicula> lista = peliculaDAO.getListaPeliculas();

        for (Pelicula p : lista) {
            Object[] fila = {
                p.getId(),
                p.getTitulo(),
                p.getGenero(),
                p.getDuracionMinutos() + " min",
                p.getClasificacion(),
                p.isActiva() ? "Activa" : "Inactiva",
                p.getRutaImagen()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void aplicarFiltroDinamico() {
        String titulo = vista.getTxtTitulo() != null ? vista.getTxtTitulo().getText().trim() : "";
        if (titulo.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + titulo, 1));
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTblPeliculas().getSelectedRow();
        if (fila >= 0) {
            String id = vista.getTblPeliculas().getValueAt(fila, 0).toString();
            ArrayList<Pelicula> lista = peliculaDAO.getListaPeliculas();

            for (Pelicula p : lista) {
                if (p.getId().equalsIgnoreCase(id)) {
                    if (vista.getTxtID() != null) vista.getTxtID().setText(p.getId());
                    if (vista.getTxtTitulo() != null) vista.getTxtTitulo().setText(p.getTitulo());
                    if (vista.getTxtGenero() != null) vista.getTxtGenero().setText(p.getGenero());
                    if (vista.getTxtDuracion() != null) vista.getTxtDuracion().setText(String.valueOf(p.getDuracionMinutos()));
                    if (vista.getTxtClasificacion() != null) vista.getTxtClasificacion().setText(p.getClasificacion());
                    if (vista.getTxtSinopsiss() != null) vista.getTxtSinopsiss().setText(p.getSinopsis());
                    if (vista.getTxtUrlTrailer() != null) vista.getTxtUrlTrailer().setText(p.getUrlTrailer());
                    if (vista.getChkActiva() != null) vista.getChkActiva().setSelected(p.isActiva());
                    if (vista.getLblRutaPortada() != null) vista.getLblRutaPortada().setText(p.getRutaImagen());
                    archivoImagenSeleccionado = null;
                    break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegresar()) {
            regresarAVistaAdmin();
        } else if (e.getSource() == vista.getBtnSeleccionarPortada()) {
            seleccionarPortada();
        } else if (e.getSource() == vista.getBtnAnadir()) {
            anadirPelicula();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizarPelicula();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarPelicula();
        }
    }

    private void seleccionarPortada() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(vista);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoImagenSeleccionado = fileChooser.getSelectedFile();
            vista.getLblRutaPortada().setText(archivoImagenSeleccionado.getName());
        }
    }

    private void anadirPelicula() {
        try {
            String titulo = vista.getTxtTitulo().getText().trim();
            String genero = vista.getTxtGenero().getText().trim();
            int duracion = Integer.parseInt(vista.getTxtDuracion().getText().trim());
            String clasificacion = vista.getTxtClasificacion().getText().trim();
            String sinopsis = vista.getTxtSinopsiss() != null ? vista.getTxtSinopsiss().getText().trim() : "Sin sinopsis";
            String urlTrailer = vista.getTxtUrlTrailer() != null ? vista.getTxtUrlTrailer().getText().trim() : "";
            boolean activa = vista.getChkActiva() == null || vista.getChkActiva().isSelected();

            if (titulo.isEmpty() || genero.isEmpty() || clasificacion.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor complete los campos obligatorios.");
                return;
            }

            Pelicula nueva = new Pelicula("", titulo, genero, duracion, clasificacion, sinopsis, "default.png", urlTrailer, activa);

            if (peliculaDAO.agregarPelicula(nueva, archivoImagenSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Película añadida correctamente.");
                cargarPeliculasEnTabla();
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar la película.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "La duración debe ser un número entero de minutos.");
        }
    }

    private void actualizarPelicula() {
        try {
            String id = vista.getTxtID().getText().trim();
            String titulo = vista.getTxtTitulo().getText().trim();
            String genero = vista.getTxtGenero().getText().trim();
            int duracion = Integer.parseInt(vista.getTxtDuracion().getText().trim());
            String clasificacion = vista.getTxtClasificacion().getText().trim();
            String sinopsis = vista.getTxtSinopsiss() != null ? vista.getTxtSinopsiss().getText().trim() : "";
            String urlTrailer = vista.getTxtUrlTrailer() != null ? vista.getTxtUrlTrailer().getText().trim() : "";
            boolean activa = vista.getChkActiva() != null && vista.getChkActiva().isSelected();
            String portadaActual = vista.getLblRutaPortada().getText();

            Pelicula editada = new Pelicula(id, titulo, genero, duracion, clasificacion, sinopsis, portadaActual, urlTrailer, activa);

            if (peliculaDAO.actualizarPelicula(editada, archivoImagenSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Película actualizada correctamente.");
                cargarPeliculasEnTabla();
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró la película para actualizar.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Seleccione una película válida de la tabla.");
        }
    }

    private void eliminarPelicula() {
        String id = vista.getTxtID().getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione una película para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Desea eliminar la película con ID " + id + "?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (peliculaDAO.eliminarPelicula(id)) {
                JOptionPane.showMessageDialog(vista, "Película eliminada correctamente.");
                cargarPeliculasEnTabla();
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar la película.");
            }
        }
    }

    private void regresarAVistaAdmin() {
        vista.dispose();
        VistaAdmin vistaAdmin = new VistaAdmin();
        ControladorAdmin ctrlAdmin = new ControladorAdmin(vistaAdmin);
        vistaAdmin.setLocationRelativeTo(null);
        vistaAdmin.setVisible(true);
    }
}