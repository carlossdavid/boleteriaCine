/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import modelo.entidad.Pelicula;
import vista.modular.ComboOscuro;
import vista.util.*;

/**
 *
 * @author carlo
 */
public class VistaCompraBoleto extends javax.swing.JFrame {

    // CONSTANTES GLOBALES 
    private final int ANCHO_VENTANA = 1280; 
    private final int ALTO_VENTANA = 720;
    
    // Imagenes 
    private ImagenEnJLabel fondoPrincipal;
    private ImagenEnJLabel portadaPeli;
    
    public VistaCompraBoleto() {
        initComponents();
        configurarPropiedadesVentana();
        cargarImagenes();
        configurarEstilosJComboBox(jcbboxFecha);
    }
    
    // IMAGENES 
    public void cargarImagenes() {
        //-- IMAGENES --// 
        // Añadir imagen de fondo 
        fondoPrincipal = new ImagenEnJLabel(fondoPrincipalJLabel, 
                "imagenesDeFondo/fondoPelicula.png");
    }
    
    public void cargarDatosPeli(Pelicula pelicula) {
        portadaPeli = new ImagenEnJLabel(imagenPeliculaJLabel, pelicula.getRutaImagen());
        txtGeneroPeli.setText(pelicula.getGenero());
        txtClasificacionPeli.setText(pelicula.getClasificacion());
        txtNombrePeli.setText(pelicula.getTitulo().toUpperCase());
        txtDuracionPeli.setText("Dur: "+ pelicula.obtenerDuracionFormateada());
        txtSinopsis.setText(pelicula.getSinopsis().substring(1, pelicula.getSinopsis().length()-1));
        txtSinopsisContenedor.getViewport().setOpaque(false);
    }
    
    // IA 
    public void configurarEstilosJComboBox(JComboBox cb){
        cb.removeAllItems();

        //EJEMPLOS DE PRUEBA 
        cb.addItem("dd/mm/aa");
        cb.addItem("26/07/26");
        cb.addItem("27/07/26");
        cb.addItem("28/07/26");
        
        // ESTILOS JCOMBOBOX
        cb.setEditable(true);
        cb.setOpaque(false);
        cb.setFocusable(false);
        cb.setBorder(null);
        cb.setUI(new ComboOscuro());

        JTextField campo = (JTextField) cb.getEditor().getEditorComponent();

        campo.setEditable(false);
        campo.setOpaque(false);
        campo.setFocusable(false);
        campo.setBorder(null);
        campo.setForeground(Tema.CREMA_CLARO);
        campo.setBackground(new Color(0, 0, 0, 0));
        
        cb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus
            ) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list,
                        value,
                        index,
                        false,
                        false
                );

                label.setPreferredSize(new Dimension(150, 38));
                
                label.setOpaque(false);
                label.setForeground(Tema.CREMA_CLARO);
                label.setBorder(
                        BorderFactory.createEmptyBorder(0, 10, 0, 10)
                );

                return label;
            }
        });
  
        
    }

    
    // CONFIGURACIONES INICIALES 
    public void configurarPropiedadesVentana() {
        //-- PROPIEDADES DE LA VENTANA --//
        this.setLocationRelativeTo(null); // Centrado
        this.setTitle("Sistema de Gestion Night Cine - Compra de Boletos");
        this.setSize(ANCHO_VENTANA, ALTO_VENTANA); // Tamaño fijo 
        this.setResizable(false); // Tamaño fijo 
    }
    
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("iconos/iconoLogo.jpg"));
        return retValue;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelHorarios = new javax.swing.JLabel();
        jcbboxHorarioContenedor = new javax.swing.JPanel();
        baseLinetxt3 = new javax.swing.JPanel();
        jcbboxFecha2 = new javax.swing.JComboBox<>();
        labelSalas = new javax.swing.JLabel();
        jcbboxSalaContenedor = new javax.swing.JPanel();
        baseLinetxt2 = new javax.swing.JPanel();
        jcbboxFecha1 = new javax.swing.JComboBox<>();
        labelFecha = new javax.swing.JLabel();
        jcbboxFechaContenedor = new javax.swing.JPanel();
        baseLinetxt1 = new javax.swing.JPanel();
        jcbboxFecha = new javax.swing.JComboBox<>();
        btnIngresar = new javax.swing.JButton();
        tituloSinopsis = new javax.swing.JLabel();
        txtSinopsisContenedor = new javax.swing.JScrollPane();
        txtSinopsis = new javax.swing.JTextArea();
        txtNombrePeli = new javax.swing.JLabel();
        txtGeneroPeli = new javax.swing.JLabel();
        txtClasificacionPeli = new javax.swing.JLabel();
        txtDuracionPeli = new javax.swing.JLabel();
        imagenPeliculaJLabel = new javax.swing.JLabel();
        fondoPrincipalJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelHorarios.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelHorarios.setForeground(Tema.BLANCO);
        labelHorarios.setText("Horarios Disponibles");
        getContentPane().add(labelHorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, -1));

        jcbboxHorarioContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxHorarioContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt3.setBackground(Tema.CREMA_CLARO);
        jcbboxHorarioContenedor.add(baseLinetxt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        jcbboxFecha2.setBackground(Tema.GRIS_FONDO);
        jcbboxFecha2.setForeground(Tema.CREMA);
        jcbboxFecha2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbboxFecha2.setBorder(null);
        jcbboxFecha2.setFocusable(false);
        jcbboxHorarioContenedor.add(jcbboxFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 38));

        getContentPane().add(jcbboxHorarioContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 150, 40));

        labelSalas.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelSalas.setForeground(Tema.BLANCO);
        labelSalas.setText("Salas Disponibles");
        getContentPane().add(labelSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 150, -1));

        jcbboxSalaContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxSalaContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt2.setBackground(Tema.CREMA_CLARO);
        jcbboxSalaContenedor.add(baseLinetxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        jcbboxFecha1.setBackground(Tema.GRIS_FONDO);
        jcbboxFecha1.setForeground(Tema.CREMA);
        jcbboxFecha1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbboxFecha1.setBorder(null);
        jcbboxFecha1.setFocusable(false);
        jcbboxSalaContenedor.add(jcbboxFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 38));

        getContentPane().add(jcbboxSalaContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 150, 40));

        labelFecha.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelFecha.setForeground(Tema.BLANCO);
        labelFecha.setText("Fecha Función");
        getContentPane().add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 120, -1));

        jcbboxFechaContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxFechaContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt1.setBackground(Tema.CREMA_CLARO);
        jcbboxFechaContenedor.add(baseLinetxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        jcbboxFecha.setBackground(Tema.GRIS_FONDO);
        jcbboxFecha.setForeground(Tema.CREMA);
        jcbboxFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbboxFecha.setBorder(null);
        jcbboxFecha.setFocusable(false);
        jcbboxFechaContenedor.add(jcbboxFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 38));

        getContentPane().add(jcbboxFechaContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 150, 40));

        btnIngresar.setBackground(Tema.ROJO_VIBRANTE);
        btnIngresar.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnIngresar.setForeground(Tema.BLANCO);
        btnIngresar.setText("Trailer");
        btnIngresar.setToolTipText("");
        btnIngresar.setAlignmentY(-0.5F);
        btnIngresar.setBorder(null);
        btnIngresar.setContentAreaFilled(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setOpaque(true);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 120, 30));

        tituloSinopsis.setFont(Tema.FUENTE_PEQUE__BOLD);
        tituloSinopsis.setForeground(Tema.BLANCO);
        tituloSinopsis.setText("Sinopsis");
        getContentPane().add(tituloSinopsis, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        txtSinopsisContenedor.setBorder(null);
        txtSinopsisContenedor.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtSinopsisContenedor.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        txtSinopsisContenedor.setOpaque(false);

        txtSinopsis.setColumns(20);
        txtSinopsis.setFont(Tema.FUENTE_PEQUE);
        txtSinopsis.setForeground(Tema.BLANCO);
        txtSinopsis.setLineWrap(true);
        txtSinopsis.setRows(5);
        txtSinopsis.setText("Aqui debería ir una sinopsis");
        txtSinopsis.setBorder(null);
        txtSinopsis.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtSinopsis.setOpaque(false);
        txtSinopsisContenedor.setViewportView(txtSinopsis);

        getContentPane().add(txtSinopsisContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 210, 80));

        txtNombrePeli.setFont(Tema.FUENTE_TITULO_PELI_GRANDE);
        txtNombrePeli.setForeground(Tema.BLANCO);
        txtNombrePeli.setText("NOMBRE PELICULA ");
        getContentPane().add(txtNombrePeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 190, -1));

        txtGeneroPeli.setBackground(Tema.GRIS_OSCURO);
        txtGeneroPeli.setFont(new java.awt.Font("Montserrat Medium", 0, 10)); // NOI18N
        txtGeneroPeli.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtGeneroPeli.setText("Genero");
        getContentPane().add(txtGeneroPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 80, -1));

        txtClasificacionPeli.setBackground(Tema.GRIS_OSCURO);
        txtClasificacionPeli.setFont(new java.awt.Font("Montserrat Medium", 0, 10)); // NOI18N
        txtClasificacionPeli.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtClasificacionPeli.setText("Clasificacion");
        getContentPane().add(txtClasificacionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 90, -1));

        txtDuracionPeli.setFont(Tema.FUENTE_DURACION_GRANDE);
        txtDuracionPeli.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtDuracionPeli.setText("DURACION");
        getContentPane().add(txtDuracionPeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 137, -1));
        getContentPane().add(imagenPeliculaJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 210, 300));
        getContentPane().add(fondoPrincipalJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCompraBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCompraBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCompraBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCompraBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCompraBoleto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel baseLinetxt1;
    private javax.swing.JPanel baseLinetxt2;
    private javax.swing.JPanel baseLinetxt3;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel fondoPrincipalJLabel;
    private javax.swing.JLabel imagenPeliculaJLabel;
    private javax.swing.JComboBox<String> jcbboxFecha;
    private javax.swing.JComboBox<String> jcbboxFecha1;
    private javax.swing.JComboBox<String> jcbboxFecha2;
    private javax.swing.JPanel jcbboxFechaContenedor;
    private javax.swing.JPanel jcbboxHorarioContenedor;
    private javax.swing.JPanel jcbboxSalaContenedor;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHorarios;
    private javax.swing.JLabel labelSalas;
    private javax.swing.JLabel tituloSinopsis;
    private javax.swing.JLabel txtClasificacionPeli;
    private javax.swing.JLabel txtDuracionPeli;
    private javax.swing.JLabel txtGeneroPeli;
    private javax.swing.JLabel txtNombrePeli;
    private javax.swing.JTextArea txtSinopsis;
    private javax.swing.JScrollPane txtSinopsisContenedor;
    // End of variables declaration//GEN-END:variables
}
