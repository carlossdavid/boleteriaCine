package vista;

import vista.modular.TarjetaPelicula;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelo.entidad.Pelicula;
import vista.util.*;

/**
 *
 * @author carlo
 */
public class VistaCliente extends javax.swing.JFrame {
    // Imagenes 
    private ImagenEnJLabel fondoPrincipal; 
    private ImagenEnJLabel iconoCartelera; 
    private ImagenEnJLabel iconoCompras; 
    private ImagenEnJLabel iconoCuenta; 
    private ImagenEnJLabel iconoLogo; 
    
    // Panel Scroll
    private JPanel panel; 
    
    // CONSTANTES GLOBALES 
    private final int ANCHO_VENTANA = 1280; 
    private final int ALTO_VENTANA = 720;

    
    /**
     * Creates new form Principal
     */
    public VistaCliente() {
        initComponents();
        configurarPropiedadesVentana();
        configurarEventosVisuales();
        configurarCartelera();
        cargarImagenes();
    }
    
    
    // IMAGENES 
    public void cargarImagenes() {
        //-- IMAGENES --// 
        // Añadir imagen de fondo 
        fondoPrincipal = new ImagenEnJLabel(fondoPrincipalJLabel, 
                "imagenesDeFondo/fondoPrincipalPrincipal.png");
        // Icono logo 
        iconoLogo = new ImagenEnJLabel(iconoLogoJLabel, 
                "iconos/logo.png");
        // Iconos Menu 
        iconoCartelera = new ImagenEnJLabel(iconoCaseteJLabel,
                "iconos/cartelera.png");
        iconoCompras = new ImagenEnJLabel(iconoCompraJLabel,
                "iconos/carrito.png");
        iconoCuenta = new ImagenEnJLabel(iconoCuentaJLabel,
                "iconos/cuenta.png");
        
        this.repaint();
    }
    
    
    // GETTERS 
    
    
    
    
    
    // CONFIGURACIONES INICIALES 
    public void configurarPropiedadesVentana() {
        //-- PROPIEDADES DE LA VENTANA --//
        this.setLocationRelativeTo(null); // Centrado
        this.setTitle("Sistema de Gestion Night Cine - Acceso al Sistema");
        this.setSize(ANCHO_VENTANA, ALTO_VENTANA); // Tamaño fijo 
        this.setResizable(false); // Tamaño fijo 
    }
    
    public void configurarEventosVisuales() {
        
        MouseAdapter eventoTamanoCartelera = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                redimensionarIcono(iconoCaseteJLabel, true, 8);
                iconoCartelera.actualizarDimensiones();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                redimensionarIcono(iconoCaseteJLabel, false, 8);
                iconoCartelera.actualizarDimensiones();
            }
        };
        
        MouseAdapter eventoTamanoCompras = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                redimensionarIcono(iconoCompraJLabel, true, 8);
                iconoCompras.actualizarDimensiones();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                redimensionarIcono(iconoCompraJLabel, false, 8);
                iconoCompras.actualizarDimensiones();
            }
        };
        
        MouseAdapter eventoTamanoCuenta = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                redimensionarIcono(iconoCuentaJLabel, true, 8);
                iconoCuenta.actualizarDimensiones();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                redimensionarIcono(iconoCaseteJLabel, false, 8);
                iconoCuenta.actualizarDimensiones();
            }
        };
                
        btnCasete.addMouseListener(eventoTamanoCartelera);
        btnCompras.addMouseListener(eventoTamanoCompras);
        btnCuenta.addMouseListener(eventoTamanoCuenta);
    }
    
    public void configurarCartelera() {
        panelPeliculas.setLayout(
            new GridLayout(0, 3, 35, 15)
        );

        scrollPeliculas.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_NEVER
        );

        scrollPeliculas.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPeliculas.getVerticalScrollBar().setUnitIncrement(16);
        
        scrollPeliculas.setOpaque(false);
        scrollPeliculas.getViewport().setOpaque(false);
        panelPeliculas.setOpaque(false);
    }
    
    
    public ArrayList<TarjetaPelicula> mostrarPeliculas(ArrayList<Pelicula> peliculas) {
        panelPeliculas.removeAll();
        ArrayList<TarjetaPelicula> tarjetas = new ArrayList<>();
        
        for (Pelicula pelicula : peliculas) {
            if(!pelicula.isActiva()) continue;
            TarjetaPelicula tarjeta = new TarjetaPelicula(pelicula);
            
            panelPeliculas.add(tarjeta);
            tarjetas.add(tarjeta);
        }

        panelPeliculas.revalidate();
        panelPeliculas.repaint();
        
        return tarjetas;
    }
    
    // Métodos de la vista
    public void cerrar() {
        this.setVisible(false);
    }
    
    
    
    // EVENTOS VISUALES 
    /**
     * @param jlabel imagen que se quiere redimensionar 
     * @param direccion true para aumentar, false para disminuir
     * @param cantidad cuanto se desea aumentar o disminuir
     */
    public void redimensionarIcono(JLabel jlabel, boolean direccion, int cantidad) {
        if (!direccion) {
            cantidad = cantidad * (-1);
        }
        
        int width = jlabel.getWidth();
        int height = jlabel.getHeight();
        int posx = jlabel.getX();
        int posy = jlabel.getY(); 
        
        jlabel.setBounds(posx-cantidad, posy-cantidad, width+(cantidad*2), height+(cantidad*2));
    }
   
    // -- METODOS PARA MENSAJES DE ERROR -- // 
    public void mostrarError(String ERROR) {
        JOptionPane.showMessageDialog(this, ERROR, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // CONFIGURACIONES POR USUARIO 
    public void configurarInvitado(){
        
    }
    
    public void addBtnCuentaListener(ActionListener l){
        btnCuenta.addActionListener(l);
    }
    
    public void addBtnComprasListener(ActionListener l) {
        btnCompras.addActionListener(l);
    }

    public void addBtnCarteleraListener(ActionListener l) {
        btnCasete.addActionListener(l);
    }

    public void addBtnCerrarSesionListener(ActionListener l) {
        btnCerrarSesion.addActionListener(l);
    }
    
    // GETTERS 

    public JButton getBtnCasete() {
        return btnCasete;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JButton getBtnCompras() {
        return btnCompras;
    }

    public JButton getBtnCuenta() {
        return btnCuenta;
    }

    public JLabel getTxtBienvenidaNombre() {
        return txtBienvenidaNombre;
    }
    
    public void setTxtBienvenidaNombre(String nombre) {
        txtBienvenidaNombre.setText(nombre);
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("iconos/iconoLogo.jpg"));
        return retValue;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCerrarSesion = new javax.swing.JButton();
        txtCarteleraJLabel = new javax.swing.JLabel();
        txtBienvenidaNombre = new javax.swing.JLabel();
        txtBienvenidaJLabel = new javax.swing.JLabel();
        iconoLogoJLabel = new javax.swing.JLabel();
        btnCasete = new javax.swing.JButton();
        iconoCaseteJLabel = new javax.swing.JLabel();
        btnCompras = new javax.swing.JButton();
        iconoCompraJLabel = new javax.swing.JLabel();
        btnCuenta = new javax.swing.JButton();
        iconoCuentaJLabel = new javax.swing.JLabel();
        scrollPeliculas = new javax.swing.JScrollPane();
        panelPeliculas = new javax.swing.JPanel();
        fondoPrincipalJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrarSesion.setFont(Tema.FUENTE_ICONOS);
        btnCerrarSesion.setForeground(new java.awt.Color(255, 0, 0));
        btnCerrarSesion.setText("Cerrar Sesion X");
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 230, 20));

        txtCarteleraJLabel.setBackground(Tema.ROJO_VIBRANTE);
        txtCarteleraJLabel.setFont(Tema.FUENTE_FUERTE_PLAIN);
        txtCarteleraJLabel.setForeground(Tema.CREMA_CLARO);
        txtCarteleraJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCarteleraJLabel.setText("CARTELERA");
        txtCarteleraJLabel.setOpaque(true);
        getContentPane().add(txtCarteleraJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 170, 50));

        txtBienvenidaNombre.setFont(Tema.FUENTE_FUERTE_PLAIN);
        txtBienvenidaNombre.setForeground(Tema.BLANCO);
        txtBienvenidaNombre.setText("ANÓNIMO :)");
        getContentPane().add(txtBienvenidaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 150, -1));

        txtBienvenidaJLabel.setFont(Tema.FUENTE_FUERTE_PLAIN);
        txtBienvenidaJLabel.setForeground(Tema.ROJO_VIBRANTE);
        txtBienvenidaJLabel.setText("BIENVENID@,");
        getContentPane().add(txtBienvenidaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 170, -1));

        iconoLogoJLabel.setBackground(new java.awt.Color(204, 204, 204));
        iconoLogoJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoLogoJLabel.setText("Logo Empresa");
        getContentPane().add(iconoLogoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 120, 120));

        btnCasete.setFont(Tema.FUENTE_ICONOS);
        btnCasete.setText("Cartelera");
        btnCasete.setBorderPainted(false);
        btnCasete.setContentAreaFilled(false);
        btnCasete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCasete.setFocusPainted(false);
        btnCasete.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(btnCasete, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 140, 140));

        iconoCaseteJLabel.setBackground(new java.awt.Color(204, 204, 204));
        iconoCaseteJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(iconoCaseteJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 120, 120));

        btnCompras.setFont(Tema.FUENTE_ICONOS);
        btnCompras.setText("Compras");
        btnCompras.setBorderPainted(false);
        btnCompras.setContentAreaFilled(false);
        btnCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompras.setFocusPainted(false);
        btnCompras.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(btnCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 140, 140));

        iconoCompraJLabel.setBackground(new java.awt.Color(204, 204, 204));
        iconoCompraJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(iconoCompraJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 120, 120));

        btnCuenta.setFont(Tema.FUENTE_ICONOS);
        btnCuenta.setText("Mi Cuenta");
        btnCuenta.setBorderPainted(false);
        btnCuenta.setContentAreaFilled(false);
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCuenta.setFocusPainted(false);
        btnCuenta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 140, 140));

        iconoCuentaJLabel.setBackground(new java.awt.Color(204, 204, 204));
        iconoCuentaJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(iconoCuentaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 120, 120));

        scrollPeliculas.setBorder(null);
        scrollPeliculas.setOpaque(false);

        panelPeliculas.setOpaque(false);
        panelPeliculas.setLayout(new java.awt.GridLayout(1, 0));
        scrollPeliculas.setViewportView(panelPeliculas);

        getContentPane().add(scrollPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 830, 510));
        getContentPane().add(fondoPrincipalJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCasete;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JLabel fondoPrincipalJLabel;
    private javax.swing.JLabel iconoCaseteJLabel;
    private javax.swing.JLabel iconoCompraJLabel;
    private javax.swing.JLabel iconoCuentaJLabel;
    private javax.swing.JLabel iconoLogoJLabel;
    private javax.swing.JPanel panelPeliculas;
    private javax.swing.JScrollPane scrollPeliculas;
    private javax.swing.JLabel txtBienvenidaJLabel;
    private javax.swing.JLabel txtBienvenidaNombre;
    private javax.swing.JLabel txtCarteleraJLabel;
    // End of variables declaration//GEN-END:variables
}
