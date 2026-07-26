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
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
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
    
    private Pelicula peli;
    
    public VistaCompraBoleto() {
        initComponents();
        configurarPropiedadesVentana();
        cargarImagenes();
        
        // Estilos JCOMBO BOX 
        configurarEstilosJComboBox(jcbboxFecha);
        configurarEstilosJComboBox(jcbboxHorario);
        configurarEstilosJComboBox(jcbboxSala);
        
        // Estilos JSPINER 
        configurarEstilosSpiner(seleccionNBoletosAdulto);
        configurarEstilosSpiner(seleccionNBoletosNino);
        
        // Estructura básica de selectores (nada seleccionado) 
        selectoresReset();
    }
    
    // IMAGENES 
    public final void cargarImagenes() {
        //-- IMAGENES --// 
        // Añadir imagen de fondo 
        fondoPrincipal = new ImagenEnJLabel(fondoPrincipalJLabel, 
                "imagenesDeFondo/fondoPelicula.png");
    }
    
    // CARGAR DATOS DESDE EL CONTROLADOR 
    public void cargarDatosPeli(Pelicula pelicula) {
        peli = pelicula;
        portadaPeli = new ImagenEnJLabel(imagenPeliculaJLabel, pelicula.getRutaImagen());
        txtGeneroPeli.setText(pelicula.getGenero());
        txtClasificacionPeli.setText(pelicula.getClasificacion());
        txtNombrePeli.setText(pelicula.getTitulo().toUpperCase());
        txtDuracionPeli.setText("Dur: "+ pelicula.obtenerDuracionFormateada());
        txtSinopsis.setText(pelicula.getSinopsis().substring(1, pelicula.getSinopsis().length()-1));
        txtSinopsisContenedor.getViewport().setOpaque(false);
    }
    
    public final void selectoresReset() {
        // Desabilitar campos Horario, Sala y Cantidad de Boletos 
        activarDesactivarJCBHorario(false);
        activarDesactivarJCBSala(false);
        activarDesactivarCantBoletos(false);
    }
    
    public void cargarFechas(ArrayList<LocalDate> fechas) {
        jcbboxFecha.removeAllItems();

        //EJEMPLOS DE PRUEBA 
        jcbboxFecha.addItem("dd/mm/aa");
        
        for (LocalDate fecha : fechas) {
            jcbboxFecha.addItem(fecha.toString());
        }
    }
    
    
    public void activarDesactivarJCBHorario(boolean activo) {
        jcbboxHorario.setEnabled(activo);
        labelHorarios.setEnabled(activo);
        if (activo) {
            lineaHorario.setBackground(Tema.CREMA_CLARO);
        } else {
            lineaHorario.setBackground(Tema.TEXTO_OPACO_FONDOGRIS);
        }
    }
    
    public void activarDesactivarJCBSala(boolean activo) {
        jcbboxSala.setEnabled(activo);
        labelSalas.setEnabled(activo);
        if (activo) {
            lineaSalas.setBackground(Tema.CREMA_CLARO);
        } else {
            lineaSalas.setBackground(Tema.TEXTO_OPACO_FONDOGRIS);
        }
    }
    
    public void activarDesactivarCantBoletos(boolean activo) {
        seleccionNBoletosAdulto.setEnabled(activo);
        seleccionNBoletosNino.setEnabled(activo);
        labelninos.setEnabled(activo);
        labelAdultos.setEnabled(activo);
        labelCantBoletos.setEnabled(activo);
        
        if (activo) {
            lineaNino.setBackground(Tema.CREMA_CLARO);
            lineaAdultos.setBackground(Tema.CREMA_CLARO);
        } else {
            lineaNino.setBackground(Tema.TEXTO_OPACO_FONDOGRIS);
            lineaAdultos.setBackground(Tema.TEXTO_OPACO_FONDOGRIS);
        }
    }
    
    
    
    
    // ESTILOS VISUALES DE SPINNER Y JCOMBOBOX
    public final void configurarEstilosSpiner(JSpinner spiner) {
        Color fondo = Tema.GRIS_OSCURO;
        Color texto = Tema.CREMA_CLARO;

        spiner.setPreferredSize(new Dimension(80, 38));
        spiner.setMinimumSize(new Dimension(80, 38));
        spiner.setBackground(new Color(0, 0, 0, 0));
        spiner.setForeground(texto);
        spiner.setFocusable(false);
        spiner.setOpaque(false);
        spiner.setBorder(null);
        
        JSpinner.DefaultEditor editor =
                (JSpinner.DefaultEditor) spiner.getEditor();

        editor.setOpaque(false);
        editor.setBorder(null);
        
        JTextField campo = editor.getTextField();

        //campo.setBackground(fondo);
        campo.setHorizontalAlignment(JTextField.CENTER);

        campo.setFocusable(false);
        campo.setEditable(false);
        campo.setOpaque(false);
        campo.setBorder(null);
        campo.setForeground(texto);
        campo.setCaretColor(texto);
        campo.setBackground(new Color(0, 0, 0, 0));
        campo.setFont(Tema.FUENTE_NORMAL);
        
        personalizarBotonesSpinner(spiner);
    }
    
    private final void personalizarBotonesSpinner(JSpinner spiner) {

    for (Component componente : spiner.getComponents()) {

        if (componente instanceof JButton boton) {

            String nombre = boton.getName();
            boton.setForeground(Tema.CREMA_CLARO);

            if ("Spinner.nextButton".equals(nombre)) {
                boton.setText("▲");
            }

            if ("Spinner.previousButton".equals(nombre)) {
                boton.setText("▼");
            }

            configurarBotonSpinner(boton);
        }
    }
}
    
    private final void configurarBotonSpinner(JButton boton) {

        Color normal = Tema.GRIS_FONDO;
        Color hover = Tema.GIRS_FONDO_OSCURO;
        Color presionado = Tema.GRIS_FONDO;
        Color flecha = Tema.CREMA_CLARO;

        boton.setBackground(normal);
        boton.setForeground(flecha);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        boton.setBorder(BorderFactory.createEmptyBorder());
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFocusable(false);

        boton.setPreferredSize(new Dimension(30, 22));
        boton.setMinimumSize(new Dimension(30, 22));
        boton.setMargin(new Insets(0, 0, 0, 0));

        boton.getModel().addChangeListener(e -> {

            if (boton.getModel().isPressed()) {
                boton.setBackground(presionado);

            } else if (boton.getModel().isRollover()) {
                boton.setBackground(hover);

            } else {
                boton.setBackground(normal);
            }
        });
    }
    
    public final void configurarEstilosJComboBox(JComboBox cb){
        
        
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
                
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        label.setBackground(Tema.GRIS_FONDO);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        label.setBackground(Tema.GRIS_OSCURO);
                    }
                
                });

                return label;
            }
        });
    }

    
    // CONFIGURACIONES INICIALES 
    public final void configurarPropiedadesVentana() {
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

        btnComprarBoleto = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        panelButacas = new javax.swing.JPanel();
        labelButaca = new javax.swing.JLabel();
        labelCantBoletos = new javax.swing.JLabel();
        labelninos = new javax.swing.JLabel();
        seleccionNBoletosNinoContenedor = new javax.swing.JPanel();
        lineaNino = new javax.swing.JPanel();
        seleccionNBoletosNino = new javax.swing.JSpinner();
        labelAdultos = new javax.swing.JLabel();
        seleccionNBoletosAdultoContenedor = new javax.swing.JPanel();
        lineaAdultos = new javax.swing.JPanel();
        seleccionNBoletosAdulto = new javax.swing.JSpinner();
        labelHorarios = new javax.swing.JLabel();
        jcbboxHorarioContenedor = new javax.swing.JPanel();
        lineaHorario = new javax.swing.JPanel();
        jcbboxHorario = new javax.swing.JComboBox<>();
        labelSalas = new javax.swing.JLabel();
        jcbboxSalaContenedor = new javax.swing.JPanel();
        lineaSalas = new javax.swing.JPanel();
        jcbboxSala = new javax.swing.JComboBox<>();
        labelFecha = new javax.swing.JLabel();
        jcbboxFechaContenedor = new javax.swing.JPanel();
        lineaFecha = new javax.swing.JPanel();
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

        btnComprarBoleto.setBackground(Tema.ROJO_VIBRANTE);
        btnComprarBoleto.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnComprarBoleto.setForeground(Tema.BLANCO);
        btnComprarBoleto.setText("REALIZAR COMPRA");
        btnComprarBoleto.setToolTipText("");
        btnComprarBoleto.setAlignmentY(-0.5F);
        btnComprarBoleto.setBorder(null);
        btnComprarBoleto.setContentAreaFilled(false);
        btnComprarBoleto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprarBoleto.setFocusPainted(false);
        btnComprarBoleto.setOpaque(true);
        btnComprarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarBoletoActionPerformed(evt);
            }
        });
        getContentPane().add(btnComprarBoleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 180, 40));

        btnRegresar.setBackground(Tema.ROJO_VIBRANTE);
        btnRegresar.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnRegresar.setForeground(Tema.BLANCO);
        btnRegresar.setText("REGRESAR");
        btnRegresar.setToolTipText("");
        btnRegresar.setAlignmentY(-0.5F);
        btnRegresar.setBorder(null);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.setFocusPainted(false);
        btnRegresar.setOpaque(true);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, 180, 40));
        getContentPane().add(panelButacas, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 600, 340));

        labelButaca.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelButaca.setForeground(Tema.BLANCO);
        labelButaca.setText("Selecciona tus Asientos");
        getContentPane().add(labelButaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 140, -1));

        labelCantBoletos.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelCantBoletos.setForeground(Tema.BLANCO);
        labelCantBoletos.setText("Cantidad de Boletos");
        getContentPane().add(labelCantBoletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 140, -1));

        labelninos.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelninos.setForeground(Tema.CREMA_CLARO);
        labelninos.setText("Niños/ 3ra Edad");
        getContentPane().add(labelninos, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, 140, -1));

        seleccionNBoletosNinoContenedor.setBackground(Tema.GRIS_OSCURO);
        seleccionNBoletosNinoContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaNino.setBackground(Tema.CREMA_CLARO);
        seleccionNBoletosNinoContenedor.add(lineaNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        seleccionNBoletosNino.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        seleccionNBoletosNino.setBorder(null);
        seleccionNBoletosNino.setFocusable(false);
        seleccionNBoletosNinoContenedor.add(seleccionNBoletosNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        getContentPane().add(seleccionNBoletosNinoContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 90, 40));

        labelAdultos.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelAdultos.setForeground(Tema.CREMA_CLARO);
        labelAdultos.setText("Adultos");
        getContentPane().add(labelAdultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 140, -1));

        seleccionNBoletosAdultoContenedor.setBackground(Tema.GRIS_OSCURO);
        seleccionNBoletosAdultoContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaAdultos.setBackground(Tema.CREMA_CLARO);
        seleccionNBoletosAdultoContenedor.add(lineaAdultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        seleccionNBoletosAdulto.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        seleccionNBoletosAdulto.setBorder(null);
        seleccionNBoletosAdulto.setFocusable(false);
        seleccionNBoletosAdultoContenedor.add(seleccionNBoletosAdulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        getContentPane().add(seleccionNBoletosAdultoContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 90, 40));

        labelHorarios.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelHorarios.setForeground(Tema.BLANCO);
        labelHorarios.setText("Horarios Disponibles");
        getContentPane().add(labelHorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, -1));

        jcbboxHorarioContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxHorarioContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaHorario.setBackground(Tema.CREMA_CLARO);
        jcbboxHorarioContenedor.add(lineaHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        jcbboxHorario.setBackground(Tema.GRIS_FONDO);
        jcbboxHorario.setForeground(Tema.CREMA);
        jcbboxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbboxHorario.setBorder(null);
        jcbboxHorario.setFocusable(false);
        jcbboxHorarioContenedor.add(jcbboxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 38));

        getContentPane().add(jcbboxHorarioContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 150, 40));

        labelSalas.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelSalas.setForeground(Tema.BLANCO);
        labelSalas.setText("Salas Disponibles");
        getContentPane().add(labelSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 150, -1));

        jcbboxSalaContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxSalaContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaSalas.setBackground(Tema.CREMA_CLARO);
        jcbboxSalaContenedor.add(lineaSalas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

        jcbboxSala.setBackground(Tema.GRIS_FONDO);
        jcbboxSala.setForeground(Tema.CREMA);
        jcbboxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbboxSala.setBorder(null);
        jcbboxSala.setFocusable(false);
        jcbboxSalaContenedor.add(jcbboxSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 38));

        getContentPane().add(jcbboxSalaContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 150, 40));

        labelFecha.setFont(Tema.FUENTE_NORMAL_BOLD);
        labelFecha.setForeground(Tema.BLANCO);
        labelFecha.setText("Fecha Función");
        getContentPane().add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 120, -1));

        jcbboxFechaContenedor.setBackground(Tema.GRIS_OSCURO);
        jcbboxFechaContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaFecha.setBackground(Tema.CREMA_CLARO);
        jcbboxFechaContenedor.add(lineaFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 400, 2));

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

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnComprarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarBoletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarBoletoActionPerformed

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
    private javax.swing.JButton btnComprarBoleto;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel fondoPrincipalJLabel;
    private javax.swing.JLabel imagenPeliculaJLabel;
    private javax.swing.JComboBox<String> jcbboxFecha;
    private javax.swing.JPanel jcbboxFechaContenedor;
    private javax.swing.JComboBox<String> jcbboxHorario;
    private javax.swing.JPanel jcbboxHorarioContenedor;
    private javax.swing.JComboBox<String> jcbboxSala;
    private javax.swing.JPanel jcbboxSalaContenedor;
    private javax.swing.JLabel labelAdultos;
    private javax.swing.JLabel labelButaca;
    private javax.swing.JLabel labelCantBoletos;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHorarios;
    private javax.swing.JLabel labelSalas;
    private javax.swing.JLabel labelninos;
    private javax.swing.JPanel lineaAdultos;
    private javax.swing.JPanel lineaFecha;
    private javax.swing.JPanel lineaHorario;
    private javax.swing.JPanel lineaNino;
    private javax.swing.JPanel lineaSalas;
    private javax.swing.JPanel panelButacas;
    private javax.swing.JSpinner seleccionNBoletosAdulto;
    private javax.swing.JPanel seleccionNBoletosAdultoContenedor;
    private javax.swing.JSpinner seleccionNBoletosNino;
    private javax.swing.JPanel seleccionNBoletosNinoContenedor;
    private javax.swing.JLabel tituloSinopsis;
    private javax.swing.JLabel txtClasificacionPeli;
    private javax.swing.JLabel txtDuracionPeli;
    private javax.swing.JLabel txtGeneroPeli;
    private javax.swing.JLabel txtNombrePeli;
    private javax.swing.JTextArea txtSinopsis;
    private javax.swing.JScrollPane txtSinopsisContenedor;
    // End of variables declaration//GEN-END:variables
}
