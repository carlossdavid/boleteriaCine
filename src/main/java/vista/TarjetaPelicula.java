package vista;

import modelo.entidad.Pelicula;
import vista.util.*;

/**
 *
 * @author carlo
 */
public class TarjetaPelicula extends javax.swing.JPanel {

    
    private ImagenEnJLabel imagenPelicula;
    /**
     * Creates new form TarjetaPelicula
     */
    public TarjetaPelicula(Pelicula pelicula) {
        initComponents();
        this.setSize(250, 400);
        this.setVisible(true);
        this.setOpaque(false);
        
        jPanel1.setOpaque(false);
        imagenPeliculaJLabel.setSize(200,270);
        imagenPelicula = new ImagenEnJLabel(imagenPeliculaJLabel, pelicula.getRutaImagen());
        txtClasificacionPeli.setText(pelicula.getClasificacion());
        txtDuracionPeli.setText(pelicula.obtenerDuracionFormateada());
        txtNombrePeli.setText(pelicula.getTitulo().toUpperCase());
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPeliCartelera = new javax.swing.JButton();
        txtNombrePeli = new javax.swing.JLabel();
        txtClasificacionPeli = new javax.swing.JLabel();
        txtDuracionPeli = new javax.swing.JLabel();
        imagenPeliculaJLabel = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPeliCartelera.setBorder(null);
        btnPeliCartelera.setBorderPainted(false);
        btnPeliCartelera.setContentAreaFilled(false);
        btnPeliCartelera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeliCartelera.setFocusPainted(false);
        jPanel1.add(btnPeliCartelera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, 320));

        txtNombrePeli.setFont(Tema.FUENTE_TITULO_PELI);
        txtNombrePeli.setForeground(Tema.CREMA_CLARO);
        txtNombrePeli.setText("NOMBRE PELICULA ");
        jPanel1.add(txtNombrePeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 190, -1));

        txtClasificacionPeli.setFont(Tema.FUENTE_CLASIFICACION);
        txtClasificacionPeli.setForeground(Tema.CREMA_CLARO);
        txtClasificacionPeli.setText("Clasificacion");
        jPanel1.add(txtClasificacionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 137, -1));

        txtDuracionPeli.setFont(Tema.FUENTE_DURACION_PELI);
        txtDuracionPeli.setForeground(Tema.BLANCO);
        txtDuracionPeli.setText("DURACION");
        jPanel1.add(txtDuracionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 137, -1));
        jPanel1.add(imagenPeliculaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, 210, 300));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 400));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPeliCartelera;
    private javax.swing.JLabel imagenPeliculaJLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtClasificacionPeli;
    private javax.swing.JLabel txtDuracionPeli;
    private javax.swing.JLabel txtNombrePeli;
    // End of variables declaration//GEN-END:variables
}
