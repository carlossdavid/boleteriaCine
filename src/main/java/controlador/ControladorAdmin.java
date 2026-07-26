package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaAdmin;
import vista.VistaGestionEmpleados;

public class ControladorAdmin implements ActionListener {

    private VistaAdmin vistaAdmin;

    public ControladorAdmin(VistaAdmin vistaAdmin) {
        this.vistaAdmin = vistaAdmin;

        // Escuchar el clic en btnEmpleados
        if (this.vistaAdmin.getBtnEmpleados() != null) {
            this.vistaAdmin.getBtnEmpleados().addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent l) {
        if (l.getSource() == vistaAdmin.getBtnEmpleados()) {
            abrirGestionEmpleados();
        }
    }

    private void abrirGestionEmpleados() {
        // Cierra la vista actual
        vistaAdmin.dispose();

        // Crea la nueva vista y su controlador
        VistaGestionEmpleados vistaGestion = new VistaGestionEmpleados();
        ControladorGestionEmpleados ctrlGestion = new ControladorGestionEmpleados(vistaGestion);
        
        // Centra y muestra la ventana
        vistaGestion.setLocationRelativeTo(null);
        vistaGestion.setVisible(true);
    }

    void iniciar() {
        vistaAdmin.setVisible(true);
    }
}