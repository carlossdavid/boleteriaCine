package vista.modular;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import vista.util.Tema;
/**
 *
 * @author carlo
 */
public class ComboOscuro extends BasicComboBoxUI{
    @Override
    protected JButton createArrowButton() {
        JButton boton = new JButton("▼");
        
        boton.setForeground(Tema.CREMA_CLARO);
        boton.setBackground(Tema.GRIS_OSCURO);

        boton.setBorder(BorderFactory.createEmptyBorder());
        boton.setFocusPainted(false);
        boton.setFocusable(false);
        boton.setContentAreaFilled(true);
        boton.setOpaque(true);

        // Ancho y alto preferido del botón
        boton.setPreferredSize(new Dimension(45, 45));

        // Reduce espacios internos
        boton.setMargin(new Insets(0, 0, 0, 0));

        /*
         * Colores según el estado del botón.
         */
        boton.getModel().addChangeListener(e -> {

            if (boton.getModel().isPressed() || boton.getModel().isRollover()) {
                boton.setBackground(Tema.GRIS_FONDO);
            } else {
                boton.setBackground(Tema.GRIS_OSCURO);
            }
        });
        
      
        return boton;
    }
    
    @Override
    public void installUI(JComponent componente) {
        super.installUI(componente);

        JComboBox<?> combo = (JComboBox<?>) componente;

        combo.setBackground(new Color(31, 31, 31));
        combo.setForeground(new Color(190, 175, 160));
        combo.setBorder(null);
        combo.setFocusable(false);
    }


}
