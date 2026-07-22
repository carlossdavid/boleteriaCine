package vista.util;

import java.awt.Color;
import javax.swing.JTextField;

public class ManejadorEventosVisuales {
    /**
     * Configura un placeholder de manera sencilla para cualquier jTextField
     * 
     * @param campo
     * @param placeHolder
     * @param colorTextoNormal
     */
    
    public static void eliminarPlaceHolder(JTextField campo, String placeHolder, Color colorTextoNormal){
        boolean estaVacio = campo.getText().equals(placeHolder);
        
        campo.setFocusable(true);
        campo.requestFocus();
        
        if (estaVacio) {
            campo.setText("");
            campo.setForeground(colorTextoNormal);
        }
    }
    
    public static void restaurarPlaceHolder(JTextField campo, String placeHolder, Color colorTextoPlaceholder){
        if (campo.getText().trim().isEmpty()){ 
            campo.setText(placeHolder);
            campo.setForeground(colorTextoPlaceholder);
            campo.setFocusable(false);
        }
    }
}
