package vista;

import modelo.entidad.Pelicula;
import vista.util.ImagenEnJLabel;

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
        imagenPelicula = new ImagenEnJLabel(imagenPeliculaJLabel, pelicula.getRutaImagen());
        txtClasificacionPeli.setText(pelicula.getClasificacion());
        txtDuracionPeli.setText(pelicula.obtenerDuracionFormateada());
        txtNombrePeli.setText(pelicula.getTitulo());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNombrePeli = new javax.swing.JLabel();
        txtClasificacionPeli = new javax.swing.JLabel();
        txtDuracionPeli = new javax.swing.JLabel();
        imagenPeliculaJLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombrePeli.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        txtNombrePeli.setText("NOMBRE PELICULA ");
        jPanel1.add(txtNombrePeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 190, -1));

        txtClasificacionPeli.setFont(new java.awt.Font("Montserrat Light", 0, 10)); // NOI18N
        txtClasificacionPeli.setText("Clasificacion");
        jPanel1.add(txtClasificacionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 137, -1));

        txtDuracionPeli.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        txtDuracionPeli.setText("DURACION");
        jPanel1.add(txtDuracionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 137, -1));
        jPanel1.add(imagenPeliculaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 20, 200, 270));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 400));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagenPeliculaJLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtClasificacionPeli;
    private javax.swing.JLabel txtDuracionPeli;
    private javax.swing.JLabel txtNombrePeli;
    // End of variables declaration//GEN-END:variables
}
