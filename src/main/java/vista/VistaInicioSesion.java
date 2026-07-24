package vista;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vista.util.*;
/**
 *
 * @author carlo
 */
public class VistaInicioSesion extends javax.swing.JFrame {
    // Definicion de imagenes 
    private ImagenEnJLabel iconoOjo;
    private ImagenEnJLabel imagenFondoPrincipal;
    private ImagenEnJLabel iconoLogo;
    private ImagenEnJLabel iconoUsuario;
    private ImagenEnJLabel iconoCandado;
    
    
    // CONSTANTES GLOBALES 
    private final int ANCHO_VENTANA = 1011; 
    private final int ALTO_VENTANA = 645;
    
    private final String PLACEHOLDER_TXT_USUARIO    = "Ingresa el Usuario";
    private final String PLACEHOLDER_TXT_CONTRA     = "**********";
    
    private boolean seVeLaContra = false; 
    
    /**
     * Creates new form InicioSesion
     */
    public VistaInicioSesion() {
        initComponents();
        configurarPropiedadesVentana();
        cargarImagenes();
        configurarEventosVisuales(); 
    }
    
    // Configuraciones inciales 
    public void configurarEventosVisuales() {
        // Botones 
        btnMostrarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {btnMostrarContraPresionado();}
        });
        
        btnIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnIngresar.setBackground(Tema.ROJO_OPACO);}
            @Override
            public void mouseReleased(MouseEvent e) {
                btnIngresar.setBackground(Tema.ROJO_VIBRANTE);}
        });
        
        txtUserCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtUserCampo, PLACEHOLDER_TXT_USUARIO);}
        });
        
        txtContraCampo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {txtMousePresionado(txtContraCampo, PLACEHOLDER_TXT_CONTRA);}
        });
        
        txtUserCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtUserCampo, PLACEHOLDER_TXT_USUARIO);}
        });
        
        txtContraCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent f) {txtPierdeFoco(txtContraCampo, PLACEHOLDER_TXT_CONTRA);}
        });
        
        
        
    }
    public void configurarPropiedadesVentana() {
        //-- PROPIEDADES DE LA VENTANA --//
        this.setLocationRelativeTo(null); // Centrado
        this.setTitle("Sistema de Gestion Night Cine - Acceso al Sistema");
        this.setSize(ANCHO_VENTANA, ALTO_VENTANA); // Tamaño fijo 
        this.setResizable(false); // Tamaño fijo 
        this.setVisible(true);
    }
    public void cargarImagenes() {
        //-- IMAGENES --// 
        // Añadir imagen de fondo 
        imagenFondoPrincipal = new ImagenEnJLabel(bgPrincipalJLabel, 
                "imagenesDeFondo/fondoPrincipalLogin.png");
        // Icono logo 
        iconoLogo = new ImagenEnJLabel(iconoLogoJLabel, 
                "iconos/logo.png");
        // Iconos de cuadros de texto
        iconoUsuario = new ImagenEnJLabel(iconUserJLabel, 
                "iconos/usuario.png");
        iconoCandado = new ImagenEnJLabel(iconContraJLabel, 
                "iconos/candado.png");
        iconoOjo = new ImagenEnJLabel(iconOjoJLabel,
                "iconos/ojo.png");
        this.repaint();
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
    
    // -- EVENTOS DATOS -- // 
    public void addBtnIngresarListener (ActionListener l){
        btnIngresar.addActionListener(l);
    }
    
    public void addBtnRegistrateListener (ActionListener l){
        btnRegistrate.addActionListener(l);
    }
    
    public void addBtnSinSesionListener (ActionListener l){
        btnSinSesion.addActionListener(l);
    }
    
    
    // -- GETTERS && SETTERS --//
    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public JButton getBtnRegistrate() {
        return btnRegistrate;
    }

    public JButton getBtnSinSesion() {
        return btnSinSesion;
    }

    public JPasswordField getTxtContraCampo() {
        return txtContraCampo;
    }

    public JTextField getTxtUserCampo() {
        return txtUserCampo;
    }

    public String getPLACEHOLDER_TXT_USUARIO() {
        return PLACEHOLDER_TXT_USUARIO;
    }

    public String getPLACEHOLDER_TXT_CONTRA() {
        return PLACEHOLDER_TXT_CONTRA;
    }
    
    // Métodos de la vista
    public void cerrar() {
        dispose();
    }
    
    // -- METODOS PARA MENSAJES DE ERROR -- // 
    public void mostrarError(String ERROR) {
        JOptionPane.showMessageDialog(this, ERROR, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Método sobreescrito para establecer icono en la ventana 
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

        btnSinSesion = new javax.swing.JButton();
        btnRegistrate = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        txtNoCuenta = new javax.swing.JLabel();
        iconoLogoJLabel = new javax.swing.JLabel();
        txtContraContainer = new javax.swing.JPanel();
        btnMostrarContra = new javax.swing.JButton();
        iconOjoJLabel = new javax.swing.JLabel();
        baseLinetxt = new javax.swing.JPanel();
        txtContraCampo = new javax.swing.JPasswordField();
        iconContraJLabel = new javax.swing.JLabel();
        jLabelMostrar = new javax.swing.JLabel();
        txtUserContainer = new javax.swing.JPanel();
        iconUserJLabel = new javax.swing.JLabel();
        baseLinetxt1 = new javax.swing.JPanel();
        txtUserCampo = new javax.swing.JTextField();
        jLabelInciarSesion = new javax.swing.JLabel();
        bgPrincipalJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSinSesion.setFont(Tema.FUENTE_NORMAL);
        btnSinSesion.setForeground(Tema.ROJO_VIBRANTE);
        btnSinSesion.setText("Continuar sin iniciar Sesión →");
        btnSinSesion.setToolTipText("");
        btnSinSesion.setBorder(null);
        btnSinSesion.setBorderPainted(false);
        btnSinSesion.setContentAreaFilled(false);
        btnSinSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSinSesion.setFocusPainted(false);
        getContentPane().add(btnSinSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 220, 30));

        btnRegistrate.setFont(Tema.FUENTE_NORMAL_BOLD);
        btnRegistrate.setForeground(Tema.ROJO_VIBRANTE);
        btnRegistrate.setText("Regístrate");
        btnRegistrate.setToolTipText("");
        btnRegistrate.setBorder(null);
        btnRegistrate.setBorderPainted(false);
        btnRegistrate.setContentAreaFilled(false);
        btnRegistrate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrate.setFocusPainted(false);
        getContentPane().add(btnRegistrate, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, 70, 30));

        btnIngresar.setBackground(Tema.ROJO_VIBRANTE);
        btnIngresar.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        btnIngresar.setForeground(Tema.BLANCO);
        btnIngresar.setText("INGRESAR");
        btnIngresar.setToolTipText("");
        btnIngresar.setAlignmentY(-0.5F);
        btnIngresar.setBorder(null);
        btnIngresar.setContentAreaFilled(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setOpaque(true);
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 340, 40));

        txtNoCuenta.setFont(Tema.FUENTE_NORMAL);
        txtNoCuenta.setForeground(Tema.BLANCO);
        txtNoCuenta.setText("¿No tienes cuenta?");
        getContentPane().add(txtNoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, -1, 30));

        iconoLogoJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoLogoJLabel.setText("Logo Empresa");
        getContentPane().add(iconoLogoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 160, 160));

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
        txtContraContainer.add(txtContraCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 170, 37));

        iconContraJLabel.setText("icon");
        txtContraContainer.add(iconContraJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabelMostrar.setFont(Tema.FUENTE_NORMAL);
        jLabelMostrar.setForeground(Tema.CREMA_CLARO);
        jLabelMostrar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMostrar.setText("Mostrar");
        txtContraContainer.add(jLabelMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 60, 40));

        getContentPane().add(txtContraContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 340, 40));

        txtUserContainer.setBackground(Tema.GRIS_OSCURO);
        txtUserContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconUserJLabel.setText("icon");
        txtUserContainer.add(iconUserJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        baseLinetxt1.setBackground(Tema.CREMA_CLARO);
        txtUserContainer.add(baseLinetxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        txtUserCampo.setBackground(Tema.GRIS_OSCURO);
        txtUserCampo.setFont(Tema.FUENTE_NORMAL);
        txtUserCampo.setForeground(Tema.TEXTO_OPACO_FONDOGRIS);
        txtUserCampo.setText(this.PLACEHOLDER_TXT_USUARIO);
        txtUserCampo.setBorder(null);
        txtUserCampo.setFocusable(false);
        txtUserContainer.add(txtUserCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 250, 37));

        getContentPane().add(txtUserContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 340, 40));

        jLabelInciarSesion.setFont(Tema.FUENTE_FUERTE);
        jLabelInciarSesion.setForeground(Tema.ROJO_VIBRANTE);
        jLabelInciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInciarSesion.setText("INICIA SESIÓN");
        jLabelInciarSesion.setToolTipText("");
        jLabelInciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabelInciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 340, 30));

        bgPrincipalJLabel.setBackground(new java.awt.Color(102, 102, 102));
        getContentPane().add(bgPrincipalJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1011, 645));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel baseLinetxt;
    private javax.swing.JPanel baseLinetxt1;
    private javax.swing.JLabel bgPrincipalJLabel;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnMostrarContra;
    private javax.swing.JButton btnRegistrate;
    private javax.swing.JButton btnSinSesion;
    private javax.swing.JLabel iconContraJLabel;
    private javax.swing.JLabel iconOjoJLabel;
    private javax.swing.JLabel iconUserJLabel;
    private javax.swing.JLabel iconoLogoJLabel;
    private javax.swing.JLabel jLabelInciarSesion;
    private javax.swing.JLabel jLabelMostrar;
    private javax.swing.JPasswordField txtContraCampo;
    private javax.swing.JPanel txtContraContainer;
    private javax.swing.JLabel txtNoCuenta;
    private javax.swing.JTextField txtUserCampo;
    private javax.swing.JPanel txtUserContainer;
    // End of variables declaration//GEN-END:variables
}
