/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import vista.util.*;

/**
 *
 * @author carlo
 */
public class VistaCartelera extends javax.swing.JFrame {
    // Imagenes 
    private ImagenEnJLabel fondoPrincipal; 
    private ImagenEnJLabel iconoCartelera; 
    private ImagenEnJLabel iconoCompras; 
    private ImagenEnJLabel iconoCuenta; 
    private ImagenEnJLabel iconoLogo; 
    
    // CONSTANTES GLOBALES 
    private final int ANCHO_VENTANA = 1280; 
    private final int ALTO_VENTANA = 720;
    /**
     * Creates new form Principal
     */
    public VistaCartelera() {
        initComponents();
        configurarPropiedadesVentana();
        configurarEventosVisuales();
        cargarImagenes();
    }
    
    public VistaCartelera(boolean invitado) {
        this();
        
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
    
    
    // Métodos de la vista
    public void cerrar() {
        dispose();
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
        fondoPrincipalJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(fondoPrincipalJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCasete;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JLabel fondoPrincipalJLabel;
    private javax.swing.JLabel iconoCaseteJLabel;
    private javax.swing.JLabel iconoCompraJLabel;
    private javax.swing.JLabel iconoCuentaJLabel;
    private javax.swing.JLabel iconoLogoJLabel;
    private javax.swing.JLabel txtBienvenidaJLabel;
    private javax.swing.JLabel txtBienvenidaNombre;
    private javax.swing.JLabel txtCarteleraJLabel;
    // End of variables declaration//GEN-END:variables
}
