/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.util;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagenEnJLabel extends JFrame {
    private JLabel label; 
    private String ruta;
    
    /**
     * @param label Campo que se quiere modificar 
     * @param ruta: 
     * <p>
     *  Especifica la ruta dentro de la carpeta src/main/resources 
     *  sin el "/" inicial, por ejemplo:
     * <p>
     *     imagenes/imagen.png
     * <p>
     * Ejemplo de mal uso      
     * <p>
     *      - /imagenes/imagen.png
     * <p>
     *      - src/main/resources/imagenes/imagen.png
    */
    public ImagenEnJLabel(JLabel label, String ruta) {
        this.label = label;
        this.ruta = ruta;
        
        this.setIcono(ruta);
    }
    
    public void setIcono(String ruta) {
        ImageIcon original = new ImageIcon(getClass().getResource("/" + ruta));
       
        ImageIcon icono = new ImageIcon(original.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        
        label.setIcon(icono);
    }
    
    public void actualizarDimensiones() {
        ImageIcon original = new ImageIcon(getClass().getResource("/" + this.ruta));
        ImageIcon nueva = new ImageIcon(original.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(nueva);
    }


}