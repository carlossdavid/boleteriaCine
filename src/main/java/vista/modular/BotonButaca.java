package vista.modular;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import modelo.entidad.Butaca;
import modelo.enums.EstadoButaca;
/**
 *
 * @author carlo
 */
public class BotonButaca extends JButton {

    private Butaca butaca;

    private ImageIcon imagenBeige;
    private ImageIcon imagenRoja;

    public BotonButaca(Butaca butaca) {
        this.butaca = butaca;

        cargarImagenes();
        configurarBoton();
        actualizarImagen();
    }

    private void configurarBoton() {
        setText("");
        setToolTipText("Butaca " + butaca.getCodigo());

        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setCursor(
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );
    }

    private void cargarImagenes() {
        imagenBeige = cargarIcono(
                "/iconos/butacaBeige.png",
                38,
                30
        );

        imagenRoja = cargarIcono(
                "/iconos/butacaRoja.png",
                38,
                30
        );
    }

    private ImageIcon cargarIcono(
            String ruta,
            int ancho,
            int alto
    ) {
        URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            System.out.println(
                    "No se encontró la imagen: " + ruta
            );
            return null;
        }

        ImageIcon iconoOriginal =
                new ImageIcon(recurso);

        Image imagenEscalada =
                iconoOriginal.getImage().getScaledInstance(
                        ancho,
                        alto,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(imagenEscalada);
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void actualizarImagen() {

        if (butaca.getEstado()
                == EstadoButaca.DISPONIBLE) {

            setIcon(imagenBeige);
            setDisabledIcon(imagenBeige);
            setEnabled(true);
            setToolTipText(
                    "Butaca "
                    + butaca.getCodigo()
                    + " disponible"
            );

        } else if (butaca.getEstado()
                == EstadoButaca.SELECCIONADA) {

            setIcon(imagenRoja);
            setDisabledIcon(imagenRoja);
            setEnabled(true);
            setToolTipText(
                    "Butaca "
                    + butaca.getCodigo()
                    + " seleccionada"
            );

        } else if (butaca.getEstado() == EstadoButaca.OCUPADA) {

            setIcon(imagenRoja);
            setDisabledIcon(imagenRoja);
            setEnabled(false);
        }
    }
}