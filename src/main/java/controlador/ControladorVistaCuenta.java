package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.DAO.CompraDAO;
import modelo.DAO.PeliculaDAO;
import modelo.DAO.UsuarioDAO;
import modelo.entidad.usuarios.Cliente;
import vista.VistaCliente;
import vista.VistaCompras;
import vista.VistaCuenta;


public class ControladorVistaCuenta implements ActionListener {
    private VistaCuenta vista;
    private Cliente clienteUsuario;
    private UsuarioDAO usuarioDAO;

    public ControladorVistaCuenta(VistaCuenta vista, Cliente clienteUsuario) {
        this.vista = vista;
        this.clienteUsuario = clienteUsuario;
        this.usuarioDAO = new UsuarioDAO();
        if (this.vista.getBtnGuardar() != null) {
            this.vista.getBtnGuardar().addActionListener(this);
        }
        // Usamos btnCasete para regresar a la Cartelera/Cliente
        if (this.vista.getBtnCasete() != null) {
            this.vista.getBtnCasete().addActionListener(this);
        }
        
        if (this.vista.getBtnCompras() != null) {
        this.vista.getBtnCompras().addActionListener(this);
        }
    }

    public void iniciar() {
        if (clienteUsuario != null) {
            if (vista.getTxtNombre() != null) vista.getTxtNombre().setText(clienteUsuario.getNombre());
            if (vista.getTxtApellido() != null) vista.getTxtApellido().setText(clienteUsuario.getApellido());
            if (vista.getTxtCorreo() != null) vista.getTxtCorreo().setText(clienteUsuario.getCorreo());
            if (vista.getTxtContrasena() != null) vista.getTxtContrasena().setText(clienteUsuario.getContrasena());
        }
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarCambios();
        } else if (e.getSource() == vista.getBtnCasete()) {
            regresarCartelera();
        } else if (e.getSource() == vista.getBtnCompras()) { 
            abrirVistaCompras();
        }
    }
    
    public void abrirVistaCompras() {
        vista.dispose();
        VistaCompras vistaComp = new VistaCompras();
        CompraDAO compraDAO = new CompraDAO();
        ControladorVistaCompras ctrl = new ControladorVistaCompras(vistaComp, compraDAO, clienteUsuario);
        ctrl.iniciar();
    }

    private void guardarCambios() {
        String nuevoNombre = vista.getTxtNombre().getText().trim();
        String nuevoApellido = vista.getTxtApellido().getText().trim();
        String nuevoCorreo = vista.getTxtCorreo().getText().trim();
        String nuevaContra = vista.getTxtContrasena().getText().trim();

        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoCorreo.isEmpty() || nuevaContra.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor no deje campos vacíos.");
            return;
        }

        // 1. Guardamos en el archivo/BD pasándole el correo original como identificador
        boolean actualizado = usuarioDAO.actualizarUsuario(clienteUsuario.getCorreo(), nuevoNombre, nuevoApellido, nuevoCorreo, nuevaContra);

        if (actualizado) {
            // 2. Actualizamos el objeto en memoria tras confirmar el guardado exitoso
            clienteUsuario.setNombre(nuevoNombre);
            clienteUsuario.setApellido(nuevoApellido);
            clienteUsuario.setCorreo(nuevoCorreo);
            clienteUsuario.setContrasena(nuevaContra);

            JOptionPane.showMessageDialog(vista, "Datos actualizados correctamente.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar los datos en el sistema.");
        }
    }

    private void regresarCartelera() {
        vista.dispose();
        
        VistaCliente vistaCartelera = new VistaCliente();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        
        ControladorVistaCliente ctrl = new ControladorVistaCliente(vistaCartelera, peliculaDAO, clienteUsuario);
        ctrl.iniciar(); // <--- Vuelve a cargar las películas en la cartelera
    }
}