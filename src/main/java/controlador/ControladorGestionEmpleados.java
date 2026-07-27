package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.usuarios.Trabajador;
import modelo.entidad.usuarios.Usuario;
import modelo.enums.RolUsuario;
import vista.VistaAdmin;
import vista.VistaGestionEmpleados;

public class ControladorGestionEmpleados implements ActionListener {

    private final VistaGestionEmpleados vista;
    private final UsuarioDAO usuarioDAO;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    public ControladorGestionEmpleados(VistaGestionEmpleados vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();

       
        if (this.vista.getBtnRegresar() != null) this.vista.getBtnRegresar().addActionListener(this);
        if (this.vista.getBtnAnadir() != null) this.vista.getBtnAnadir().addActionListener(this);
        if (this.vista.getBtnActualizar() != null) this.vista.getBtnActualizar().addActionListener(this);
        if (this.vista.getBtnEliminar() != null) this.vista.getBtnEliminar().addActionListener(this);

        
        inicializarTabla();
        cargarEmpleadosEnTabla();

      
        KeyAdapter listenerFiltro = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                aplicarFiltroDinamico();
            }
        };

        if (vista.getTxtId() != null) vista.getTxtId().addKeyListener(listenerFiltro);
        if (vista.getTxtNombre() != null) vista.getTxtNombre().addKeyListener(listenerFiltro);
        if (vista.getTxtApellido() != null) vista.getTxtApellido().addKeyListener(listenerFiltro);
        if (vista.getTxtCorreo() != null) vista.getTxtCorreo().addKeyListener(listenerFiltro);

     
        this.vista.getTblEmpleados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        vista.getTblEmpleados().setModel(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        vista.getTblEmpleados().setRowSorter(sorter);
    }

    private void cargarEmpleadosEnTabla() {
        modeloTabla.setRowCount(0); 
        List<Usuario> lista = usuarioDAO.getListaUsuarios();

        for (Usuario u : lista) {
            if (u instanceof Trabajador tr) {
                Object[] fila = {
                    tr.getId(),
                    tr.getNombre(),
                    tr.getApellido(),
                    tr.getCorreo()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private void aplicarFiltroDinamico() {
        String id = vista.getTxtId() != null ? vista.getTxtId().getText().trim() : "";
        String nombre = vista.getTxtNombre() != null ? vista.getTxtNombre().getText().trim() : "";
        String apellido = vista.getTxtApellido() != null ? vista.getTxtApellido().getText().trim() : "";
        String correo = vista.getTxtCorreo() != null ? vista.getTxtCorreo().getText().trim() : "";

        String textoBusqueda = id + " " + nombre + " " + apellido + " " + correo;
        
        if (textoBusqueda.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {    
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + id, 0));
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre, 1));
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + apellido, 2));
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + correo, 3));
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTblEmpleados().getSelectedRow();
        if (fila >= 0) {
            vista.getTxtId().setText(vista.getTblEmpleados().getValueAt(fila, 0).toString());
            vista.getTxtNombre().setText(vista.getTblEmpleados().getValueAt(fila, 1).toString());
            vista.getTxtApellido().setText(vista.getTblEmpleados().getValueAt(fila, 2).toString());
            vista.getTxtCorreo().setText(vista.getTblEmpleados().getValueAt(fila, 3).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegresar()) {
            regresarAVistaAdmin();
        } else if (e.getSource() == vista.getBtnAnadir()) {
            anadirEmpleado();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizarEmpleado();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarEmpleado();
        }
    }

    private void anadirEmpleado() {
        try {
            //int id = Integer.parseInt(vista.getTxtId().getText().trim());
            String id = vista.getTxtId().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            String apellido = vista.getTxtApellido().getText().trim();
            String correo = vista.getTxtCorreo().getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor complete todos los campos.");
                return;
            }

            Usuario nuevo = new Trabajador(id, nombre, apellido, correo, "1234", String.valueOf(RolUsuario.VENDEDOR));

            if (usuarioDAO.agregarUsuario(nuevo)) {
                JOptionPane.showMessageDialog(vista, "Empleado añadido correctamente.");
                cargarEmpleadosEnTabla();
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el empleado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El ID debe ser un número entero válido.");
        }
    }

    private void actualizarEmpleado() {
    try {
        //int id = Integer.parseInt(vista.getTxtId().getText().trim());
        String id = vista.getTxtId().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();

        List<Usuario> lista = usuarioDAO.getListaUsuarios();
        String contraAnterior = "1234";
        for (Usuario u : lista) {
            if (u.getId().equals(id)) {
                contraAnterior = u.getContrasena(); 
                break;
            }
        }

        Usuario editado = new Trabajador(id, nombre, apellido, correo, "1234", String.valueOf(RolUsuario.VENDEDOR));

       
        if (usuarioDAO.actualizarUsuario(editado)) {
            JOptionPane.showMessageDialog(vista, "Empleado actualizado correctamente.");
            cargarEmpleadosEnTabla();
            vista.limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró el empleado para actualizar.");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "Seleccione un empleado válido de la tabla o verifique el ID.");
    }
}

    private void eliminarEmpleado() {
        try {
            String id = vista.getTxtId().getText().trim();
            int confirm = JOptionPane.showConfirmDialog(vista, 
                    "¿Está seguro de eliminar al empleado con ID " + id + "?", 
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (usuarioDAO.eliminarUsuario(id)) {
                    JOptionPane.showMessageDialog(vista, "Empleado eliminado correctamente.");
                    cargarEmpleadosEnTabla();
                    vista.limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar el empleado.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Seleccione un empleado para eliminar.");
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