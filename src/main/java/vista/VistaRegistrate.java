/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import vista.util.*;

/**
 *
 * @author carlo
 */
public class VistaRegistrate extends javax.swing.JFrame {

    // CONSTANTES GLOBALES 
    private final int ANCHO_VENTANA = 1011; 
    private final int ALTO_VENTANA = 645;
    
    private final String PLACEHOLDER_TXT_NOMBRE    = "Ingresa tu primer nombre";
    private final String PLACEHOLDER_TXT_APELLIDO = "Ingresa tu primer apellido";
    private final String PLACEHOLDER_TXT_CORREO = "correo-ejemplo@algo.com";
    private final String PLACEHOLDER_TXT_CONTRA     = "**********";
    
    private boolean seVeLaContra = false;
    
    // IMAGENES 
    private ImagenEnJLabel imagenFondo;
    private ImagenEnJLabel iconoLogo; 
    private ImagenEnJLabel iconoOjo; 
    
    public VistaRegistrate() {
        initComponents();
        configurarPropiedadesVentana();
        configurarImagenes();
        configurarEventosVisuales(); 
    }
    
    // CONFIGURACIONES INICIALES
    public void configurarPropiedadesVentana() {
        //-- PROPIEDADES DE LA VENTANA --//
        this.setLocationRelativeTo(null); // Centrado
        this.setTitle("Sistema de Gestion Night Cine - Registrarse");
        this.setSize(ANCHO_VENTANA, ALTO_VENTANA); // Tamaño fijo 
        this.setResizable(false); // Tamaño fijo 
    }
    
    public void configurarImagenes() {
        imagenFondo = new ImagenEnJLabel(imagenFondoJLabel, 
                "imagenesDeFondo/fondoRegistrarse.png");
        iconoLogo = new ImagenEnJLabel(iconoLogoJLagel, 
                "iconos/logo.png");
        iconoOjo = new ImagenEnJLabel(iconOjoJLabel, 
                "iconos/ojo.png");
    }
    
    public void configurarEventosVisuales() {
        // Botones 
        btnMostrarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {btnMostrarContraPresionado();}
        });
        
        btnRegistrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnRegistrarse.setBackground(Tema.ROJO_OPACO);}
            @Override
            public void mouseReleased(MouseEvent e) {
                btnRegistrarse.setBackground(Tema.ROJO_VIBRANTE);}
        });
        
        
        // Campos Place holders 
        txtNombreCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtNombreCampo, PLACEHOLDER_TXT_NOMBRE);}
        });

        txtApellidoCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtApellidoCampo, PLACEHOLDER_TXT_APELLIDO);}
        });
        
        txtCorreoCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtCorreoCampo, PLACEHOLDER_TXT_CORREO);}
        });
        
        txtContraCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtContraCampo, PLACEHOLDER_TXT_CONTRA);}
        });
        
        // ------- FOCUS LOST 
        txtNombreCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtNombreCampo, PLACEHOLDER_TXT_NOMBRE);}
        });

        txtApellidoCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtApellidoCampo, PLACEHOLDER_TXT_APELLIDO);}
        });
        
        txtCorreoCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtCorreoCampo, PLACEHOLDER_TXT_CORREO);}
        });
        
        txtContraCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtContraCampo, PLACEHOLDER_TXT_CONTRA);}
        });
        

    }
    
    // -- EVENTOS VISUALES -- // 
    private void txtMousePresionado (JTextField campo, String PLACEHOLDER) {
        ManejadorEventosVisuales.eliminarPlaceHolder(campo, 
                PLACEHOLDER, 
                Tema.CREMA_CLARO);
    }
    
    private void txtPierdeFoco(JTextField campo, String PLACEHOLDER) {
        ManejadorEventosVisuales.restaurarPlaceHolder(
                campo, 
                PLACEHOLDER, 
                Tema.TEXTO_OPACO_FONDOGRIS);
    }
    
     private void btnMostrarContraPresionado() {
        if (seVeLaContra) {
            txtContraCampo.setEchoChar('•'); 
            jLabelMostrar.setText("Mostrar"); 
            iconoOjo.setIcono("iconos/ojo.png");
            seVeLaContra = false;
        } else {
            txtContraCampo.setEchoChar((char) 0); 
            jLabelMostrar.setText("Ocultar"); 
                        iconoOjo.setIcono("iconos/ojoAbierto.png");
            seVeLaContra = true;
        }
    }
    
    // Icono Ventana 
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("iconos/iconoLogo.jpg"));
        return retValue;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabelRegistro = new javax.swing.JLabel();
        jlabelNombreContainer = new javax.swing.JLabel();
        txtNombreContainer = new javax.swing.JPanel();
        baseLinetxt2 = new javax.swing.JPanel();
        txtNombreCampo = new javax.swing.JTextField();
        jlabelApellidoContainer = new javax.swing.JLabel();
        txtApellidoContainer = new javax.swing.JPanel();
        baseLinetxt3 = new javax.swing.JPanel();
        txtApellidoCampo = new javax.swing.JTextField();
        jlabelCorreoContainer = new javax.swing.JLabel();
        txtcorreoContainer = new javax.swing.JPanel();
        baseLinetxt5 = new javax.swing.JPanel();
        txtCorreoCampo = new javax.swing.JTextField();
        jlabelContraContainer = new javax.swing.JLabel();
        txtContraContainer = new javax.swing.JPanel();
        btnMostrarContra = new javax.swing.JButton();
        iconOjoJLabel = new javax.swing.JLabel();
        baseLinetxt = new javax.swing.JPanel();
        txtContraCampo = new javax.swing.JPasswordField();
        jLabelMostrar = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        btnIniciarSesion = new javax.swing.JButton();
        iconoLogoJLagel = new javax.swing.JLabel();
        imagenFondoJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlabelRegistro.setFont(Tema.FUENTE_FUERTE_PLAIN);
        jlabelRegistro.setForeground(Tema.ROJO_VIBRANTE);
        jlabelRegistro.setText("REGISTRO DE USUARIO");
        getContentPane().add(jlabelRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 280, -1));

        jlabelNombreContainer.setFont(Tema.FUENTE_NORMAL_BOLD);
        jlabelNombreContainer.setForeground(Tema.CREMA_CLARO);
        jlabelNombreContainer.setText("Nombre");
        getContentPane().add(jlabelNombreContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        txtNombreContainer.setBackground(Tema.GRIS_OSCURO);
        txtNombreContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt2.setBackground(Tema.CREMA_CLARO);
        txtNombreContainer.add(baseLinetxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        txtNombreCampo.setBackground(Tema.GRIS_OSCURO);
        txtNombreCampo.setFont(Tema.FUENTE_NORMAL);
        txtNombreCampo.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtNombreCampo.setText(this.PLACEHOLDER_TXT_NOMBRE);
        txtNombreCampo.setBorder(null);
        txtNombreCampo.setFocusable(false);
        txtNombreContainer.add(txtNombreCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 37));

        getContentPane().add(txtNombreContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 340, 40));

        jlabelApellidoContainer.setFont(Tema.FUENTE_NORMAL_BOLD);
        jlabelApellidoContainer.setForeground(Tema.CREMA_CLARO);
        jlabelApellidoContainer.setText("Apellido");
        getContentPane().add(jlabelApellidoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        txtApellidoContainer.setBackground(Tema.GRIS_OSCURO);
        txtApellidoContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt3.setBackground(Tema.CREMA_CLARO);
        txtApellidoContainer.add(baseLinetxt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        txtApellidoCampo.setBackground(Tema.GRIS_OSCURO);
        txtApellidoCampo.setFont(Tema.FUENTE_NORMAL);
        txtApellidoCampo.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtApellidoCampo.setText(this.PLACEHOLDER_TXT_APELLIDO);
        txtApellidoCampo.setBorder(null);
        txtApellidoCampo.setFocusable(false);
        txtApellidoContainer.add(txtApellidoCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 37));

        getContentPane().add(txtApellidoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 340, 40));

        jlabelCorreoContainer.setFont(Tema.FUENTE_NORMAL_BOLD);
        jlabelCorreoContainer.setForeground(Tema.CREMA_CLARO);
        jlabelCorreoContainer.setText("Correo");
        getContentPane().add(jlabelCorreoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        txtcorreoContainer.setBackground(Tema.GRIS_OSCURO);
        txtcorreoContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baseLinetxt5.setBackground(Tema.CREMA_CLARO);
        txtcorreoContainer.add(baseLinetxt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        txtCorreoCampo.setBackground(Tema.GRIS_OSCURO);
        txtCorreoCampo.setFont(Tema.FUENTE_NORMAL);
        txtCorreoCampo.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtCorreoCampo.setText(this.PLACEHOLDER_TXT_CORREO);
        txtCorreoCampo.setBorder(null);
        txtCorreoCampo.setFocusable(false);
        txtcorreoContainer.add(txtCorreoCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 37));

        getContentPane().add(txtcorreoContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 340, 40));

        jlabelContraContainer.setFont(Tema.FUENTE_NORMAL_BOLD);
        jlabelContraContainer.setForeground(Tema.CREMA_CLARO);
        jlabelContraContainer.setText("Contraseña");
        getContentPane().add(jlabelContraContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, -1, -1));

        txtContraContainer.setBackground(Tema.GRIS_OSCURO);
        txtContraContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMostrarContra.setBorder(null);
        btnMostrarContra.setBorderPainted(false);
        btnMostrarContra.setContentAreaFilled(false);
        btnMostrarContra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarContra.setFocusPainted(false);
        txtContraContainer.add(btnMostrarContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 13, 100, 20));

        iconOjoJLabel.setText("icon");
        txtContraContainer.add(iconOjoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 40, 40));

        baseLinetxt.setBackground(Tema.CREMA_CLARO);
        txtContraContainer.add(baseLinetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        txtContraCampo.setBackground(Tema.GRIS_OSCURO);
        txtContraCampo.setFont(Tema.FUENTE_NORMAL);
        txtContraCampo.setForeground(new java.awt.Color(93, 86, 79));
        txtContraCampo.setText(this.PLACEHOLDER_TXT_CONTRA);
        txtContraCampo.setBorder(null);
        txtContraCampo.setFocusable(false);
        txtContraContainer.add(txtContraCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 220, 37));

        jLabelMostrar.setFont(Tema.FUENTE_NORMAL);
        jLabelMostrar.setForeground(Tema.CREMA_CLARO);
        jLabelMostrar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMostrar.setText("Mostrar");
        txtContraContainer.add(jLabelMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 60, 40));

        getContentPane().add(txtContraContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 340, 40));

        btnRegistrarse.setBackground(Tema.ROJO_VIBRANTE);
        btnRegistrarse.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        btnRegistrarse.setForeground(Tema.BLANCO);
        btnRegistrarse.setText("REGISTRARSE");
        btnRegistrarse.setToolTipText("");
        btnRegistrarse.setAlignmentY(-0.5F);
        btnRegistrarse.setBorder(null);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.setFocusPainted(false);
        btnRegistrarse.setOpaque(true);
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 340, 40));

        btnIniciarSesion.setFont(Tema.FUENTE_NORMAL);
        btnIniciarSesion.setForeground(Tema.ROJO_VIBRANTE);
        btnIniciarSesion.setText("Iniciar Sesion →");
        btnIniciarSesion.setToolTipText("");
        btnIniciarSesion.setBorder(null);
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setContentAreaFilled(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 220, 30));
        getContentPane().add(iconoLogoJLagel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 100, 100));
        getContentPane().add(imagenFondoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel baseLinetxt;
    private javax.swing.JPanel baseLinetxt2;
    private javax.swing.JPanel baseLinetxt3;
    private javax.swing.JPanel baseLinetxt5;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnMostrarContra;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel iconOjoJLabel;
    private javax.swing.JLabel iconoLogoJLagel;
    private javax.swing.JLabel imagenFondoJLabel;
    private javax.swing.JLabel jLabelMostrar;
    private javax.swing.JLabel jlabelApellidoContainer;
    private javax.swing.JLabel jlabelContraContainer;
    private javax.swing.JLabel jlabelCorreoContainer;
    private javax.swing.JLabel jlabelNombreContainer;
    private javax.swing.JLabel jlabelRegistro;
    private javax.swing.JTextField txtApellidoCampo;
    private javax.swing.JPanel txtApellidoContainer;
    private javax.swing.JPasswordField txtContraCampo;
    private javax.swing.JPanel txtContraContainer;
    private javax.swing.JTextField txtCorreoCampo;
    private javax.swing.JTextField txtNombreCampo;
    private javax.swing.JPanel txtNombreContainer;
    private javax.swing.JPanel txtcorreoContainer;
    // End of variables declaration//GEN-END:variables
}
