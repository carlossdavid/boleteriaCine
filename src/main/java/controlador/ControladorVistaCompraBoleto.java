package controlador;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import modelo.DAO.AsientoFuncionDAO;
import modelo.DAO.CompraDAO;
import modelo.DAO.FuncionDAO;
import modelo.DAO.PeliculaDAO;
import modelo.DAO.SalaDAO;
import modelo.entidad.Butaca;
import modelo.entidad.Compra;
import modelo.entidad.Funcion;
import modelo.entidad.Pelicula;
import modelo.entidad.Sala;
import modelo.entidad.usuarios.Cliente;
import modelo.enums.EstadoButaca;
import vista.VistaCliente;
import vista.VistaCompraBoleto;
import vista.modular.BotonButaca;


/**
 *
 * @author carlo
 */
public class ControladorVistaCompraBoleto {
    private VistaCompraBoleto vista;
    private Pelicula pelicula; 
    private Cliente cliente; 
    private CompraDAO compraDAO; 
    private FuncionDAO funcionDAO;
    private Funcion funcionSeleccionada;
    private boolean cargandoSelectores = false;
    private VistaCliente vistaAnterior;
    private AsientoFuncionDAO asientoFuncionDAO;
    

    public ControladorVistaCompraBoleto(
        VistaCompraBoleto vista,
        VistaCliente vistaAnterior,
        Cliente cliente,
        Pelicula pelicula
    ) {
        this.cliente = cliente;
        this.vista = vista;
        this.pelicula = pelicula;
        this.vistaAnterior = vistaAnterior;
        this.compraDAO = new CompraDAO();

        SalaDAO salaDAO = new SalaDAO();
        PeliculaDAO peliculaDAO = new PeliculaDAO();

        this.funcionDAO =
                new FuncionDAO(salaDAO, peliculaDAO);
        
        this.asientoFuncionDAO = new AsientoFuncionDAO();

        configurarEventos();
    }
    
    public void agregarListenersButacas() {
        for (BotonButaca boton : vista.getBotonesButacas()) {

            boton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    seleccionarButaca(boton);
                }
            });
        }
    }
    
    public void seleccionarButaca(BotonButaca boton) {

        Butaca butaca = boton.getButaca();

        int cantidadBoletos =
                vista.getNBoletosAdultosSeleccionados()
                + vista.getNBoletosNinosSeleccionados();

        if (cantidadBoletos <= 0) {
            vista.mostrarMensaje(
                    "Primero selecciona la cantidad de boletos"
            );
            return;
        }

        if (butaca.getEstado()
                == EstadoButaca.OCUPADA) {
            return;
        }

        if (butaca.getEstado()
                == EstadoButaca.SELECCIONADA) {

            butaca.setEstado(
                    EstadoButaca.DISPONIBLE
            );

            boton.actualizarImagen();
            return;
        }

        int seleccionadas =
                contarButacasSeleccionadas();

        if (seleccionadas >= cantidadBoletos) {
            vista.mostrarMensaje(
                    "Ya seleccionaste todas las butacas "
                    + "correspondientes a tus boletos"
            );
            return;
        }

        if (butaca.getEstado()
                == EstadoButaca.DISPONIBLE) {

            butaca.setEstado(
                    EstadoButaca.SELECCIONADA
            );

            boton.actualizarImagen();
        }
    }
    
    private int contarButacasSeleccionadas() {

        int cantidad = 0;

        for (BotonButaca boton
                : vista.getBotonesButacas()) {

            if (boton.getButaca().getEstado()
                    == EstadoButaca.SELECCIONADA) {

                cantidad++;
            }
        }

        return cantidad;
    }

    public void iniciar() {
        vista.cargarDatosPeli(pelicula);

        ArrayList<LocalDate> fechas = new ArrayList<>();
        ArrayList<Funcion> funciones =
                funcionDAO.getListaFuncionesPorPelicula(pelicula);

        for (Funcion funcion : funciones) {

            if (funcion.isActiva()
                    && !fechas.contains(funcion.getFecha())) {

                fechas.add(funcion.getFecha());
            }
        }

        cargandoSelectores = true;

        vista.cargarFechas(fechas);
        vista.limpiarSalas();
        vista.limpiarHorarios();
        vista.limpiarButacas();
        vista.reiniciarCantidadBoletos();

        vista.activarDesactivarJCBSala(false);
        vista.activarDesactivarJCBHorario(false);
        vista.activarDesactivarCantBoletos(false);
        vista.activarDesactivarSelectorButacas(false);

        if (cliente == null) {
            vista.activarDesactivarBotonComprar(false);
        } else {
            vista.activarDesactivarBotonComprar(true);
        }

        cargandoSelectores = false;

        vista.setVisible(true);
    }
    
    // METODOS PARA EVENTOS 
    public void configurarEventos() {
        vista.agregarListenerFecha(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarFecha();
            }
        });
        
        vista.agregarListenerSala(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarSala();
            }
        });
        
        vista.agregarListenerHorario(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarHorario();
            }
        });
        
        vista.agregarListenerTrailer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTrailer();
            }
        });
        vista.agregarListenerRegresar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresar();
            }
        });
        vista.agregarListenerComprarBoleto(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });
    }
    
    private ArrayList<Butaca> getButacasSeleccionadas() {

        ArrayList<Butaca> butacasSeleccionadas =
                new ArrayList<>();

        for (BotonButaca boton
                : vista.getBotonesButacas()) {

            Butaca butaca = boton.getButaca();

            if (butaca.getEstado()
                    == EstadoButaca.SELECCIONADA) {

                butacasSeleccionadas.add(butaca);
            }
        }

        return butacasSeleccionadas;
    }
    
    private String getCodigosButacas(
        ArrayList<Butaca> butacas
    ) {
        String codigos = "";

        for (int i = 0; i < butacas.size(); i++) {

            codigos += butacas.get(i).getCodigo();

            if (i < butacas.size() - 1) {
                codigos += "|";
            }
        }

        return codigos;
    }
    
    public void realizarCompra() {
        if (cliente == null) {
            vista.mostrarMensaje(
                    "Debes iniciar sesión como cliente para realizar una compra"
            );
            return;
        }


        int cantidadAdultos =
                vista.getNBoletosAdultosSeleccionados();

        int cantidadNinos =
                vista.getNBoletosNinosSeleccionados();

        int cantidadTotalBoletos =
                cantidadAdultos + cantidadNinos;

        if (cantidadTotalBoletos <= 0) {
            vista.mostrarMensaje(
                    "Selecciona al menos un boleto"
            );
            return;
        }

        ArrayList<Butaca> butacasSeleccionadas =
                getButacasSeleccionadas();

        if (butacasSeleccionadas.size()
                != cantidadTotalBoletos) {

            vista.mostrarMensaje(
                    "Debes seleccionar "
                    + cantidadTotalBoletos
                    + " butacas"
            );
            return;
        }

        double precioAdulto =
                funcionSeleccionada.getPrecio();

        // Niños y tercera edad pagan el 50 %
        double precioNino =
                funcionSeleccionada.getPrecio() * 0.50;

        double total =
                cantidadAdultos * precioAdulto
                + cantidadNinos * precioNino;

        String codigosButacas =
                getCodigosButacas(
                        butacasSeleccionadas
                );

        String mensajeConfirmacion =
                "Película: "
                + pelicula.getTitulo()
                + "\nSala: "
                + funcionSeleccionada.getSala().getNombre()
                + "\nFecha: "
                + funcionSeleccionada.getFecha()
                + "\nHorario: "
                + funcionSeleccionada.getHora()
                + "\nButacas: "
                + codigosButacas.replace("|", ", ")
                + "\nTotal: $"
                + String.format("%.2f", total)
                + "\n\n¿Está seguro de realizar la compra?";

        boolean confirmado =
                vista.confirmarCompra(
                        mensajeConfirmacion
                );

        if (!confirmado) {
            return;
        }

        String idCliente;

        if (cliente == null) {
            idCliente = "INVITADO";
        } else {
            idCliente = cliente.getId();
        }

        Compra compra = new Compra(
                null,
                idCliente,
                funcionSeleccionada.getId(),
                LocalDateTime.now(),
                cantidadAdultos,
                cantidadNinos,
                codigosButacas,
                total,
                "CONFIRMADA"
        );

        boolean compraGuardada =
                compraDAO.agregarCompra(compra);

        if (!compraGuardada) {
            vista.mostrarMensaje(
                    "No se pudo guardar la compra"
            );
            return;
        }
        boolean butacasGuardadas =
        asientoFuncionDAO.guardarButacasOcupadas(
                funcionSeleccionada.getId(),
                butacasSeleccionadas
        );

        if (!butacasGuardadas) {
            vista.mostrarMensaje(
                    "La compra se guardó pero no se pudieron "
                    + "guardar las butacas ocupadas"
            );
        }

        if (cliente != null) {
            cliente.agregarCompra(compra);
        }
        
        for (BotonButaca boton : vista.getBotonesButacas()) {

        if (boton.getButaca().getEstado()
                == EstadoButaca.SELECCIONADA) {

            boton.getButaca().setEstado(
                    EstadoButaca.OCUPADA
            );

            boton.actualizarImagen();
        }
    }

        String textoFactura =
                generarTextoFactura(
                        compra,
                        precioAdulto,
                        precioNino
                );

        String idFactura =
                compraDAO.guardarFactura(
                        compra,
                        textoFactura
                );

        // Las butacas seleccionadas pasan a ocupadas
        for (BotonButaca boton
                : vista.getBotonesButacas()) {

            if (boton.getButaca().getEstado()
                    == EstadoButaca.SELECCIONADA) {

                boton.getButaca().setEstado(
                        EstadoButaca.OCUPADA
                );

                boton.actualizarImagen();
            }
        }

        vista.reiniciarCantidadBoletos();
        vista.activarDesactivarCantBoletos(false);
        vista.activarDesactivarSelectorButacas(false);

        if (idFactura == null) {

            vista.mostrarMensaje(
                    "La compra se guardó pero ocurrió "
                    + "un error al guardar la factura"
            );

        } else {

            vista.mostrarFactura(
                    "NÚMERO DE FACTURA: "
                    + idFactura
                    + "\n"
                    + textoFactura
            );
        }
    }
    
    private String generarTextoFactura(
        Compra compra,
        double precioAdulto,
        double precioNino
    ) {
        String nombreCliente;

        if (cliente == null) {
            nombreCliente = "Invitado";
        } else {
            nombreCliente =
                    cliente.getNombre()
                    + " "
                    + cliente.getApellido();
        }

        StringBuilder factura =
                new StringBuilder();

        factura.append(
                "========================================\n"
        );

        factura.append(
                "              NIGHT CINE\n"
        );

        factura.append(
                "========================================\n"
        );

        factura.append(
                "Compra: "
                + compra.getId()
                + "\n"
        );

        factura.append(
                "Cliente: "
                + nombreCliente
                + "\n"
        );

        factura.append(
                "Fecha de compra: "
                + compra.getFechaCompra()
                + "\n"
        );

        factura.append(
                "----------------------------------------\n"
        );

        factura.append(
                "Película: "
                + pelicula.getTitulo()
                + "\n"
        );

        factura.append(
                "Sala: "
                + funcionSeleccionada.getSala().getNombre()
                + "\n"
        );

        factura.append(
                "Fecha de función: "
                + funcionSeleccionada.getFecha()
                + "\n"
        );

        factura.append(
                "Horario: "
                + funcionSeleccionada.getHora()
                + "\n"
        );

        factura.append(
                "Butacas: "
                + compra.getButacas().replace("|", ", ")
                + "\n"
        );

        factura.append(
                "----------------------------------------\n"
        );

        factura.append(
                "Adultos: "
                + compra.getCantidadAdultos()
                + " x $"
                + String.format("%.2f", precioAdulto)
                + "\n"
        );

        factura.append(
                "Niños/3ra edad: "
                + compra.getCantidadNinos()
                + " x $"
                + String.format("%.2f", precioNino)
                + "\n"
        );

        factura.append(
                "----------------------------------------\n"
        );

        factura.append(
                "TOTAL: $"
                + String.format("%.2f", compra.getTotal())
                + "\n"
        );

        factura.append(
                "========================================\n"
        );

        factura.append(
                "        Gracias por su compra\n"
        );

        return factura.toString();
    }
    
    
    public void regresar() {
        vistaAnterior.setVisible(true);
        vista.dispose();
    }
    
    
    
    public void abrirTrailer() {

        String urlTrailer = pelicula.getUrlTrailer();

        if (urlTrailer == null || urlTrailer.isBlank()) {
            vista.mostrarMensaje(
                    "Esta película no tiene un tráiler disponible"
            );
            return;
        }

        try {
            Desktop.getDesktop().browse(
                    new URI(urlTrailer)
            );

        } catch (IOException e) {
            vista.mostrarMensaje( "No se pudo abrir el navegador");

        } catch (URISyntaxException e) {
            vista.mostrarMensaje( "El enlace del tráiler no es válido");
        }
    }
    
    public void seleccionarFecha() {

        if (cargandoSelectores) {
            return;
        }

        LocalDate fechaSeleccionada =
                vista.getFechaSeleccionada();

        cargandoSelectores = true;

        vista.limpiarSalas();
        vista.limpiarHorarios();
        vista.limpiarButacas();
        vista.reiniciarCantidadBoletos();

        vista.activarDesactivarJCBHorario(false);
        vista.activarDesactivarCantBoletos(false);
        vista.activarDesactivarSelectorButacas(false);

        funcionSeleccionada = null;

        if (fechaSeleccionada == null) {
            vista.activarDesactivarJCBSala(false);
            cargandoSelectores = false;
            return;
        }

        ArrayList<Sala> salas = new ArrayList<>();

        ArrayList<Funcion> funciones =
                funcionDAO.getListaFuncionesPorPelicula(pelicula);

        for (Funcion funcion : funciones) {

            boolean mismaFecha =
                    funcion.getFecha().compareTo(
                            fechaSeleccionada
                    ) == 0;

            boolean salaRepetida = false;

            for (Sala sala : salas) {

                if (sala.getId().equalsIgnoreCase(
                        funcion.getSala().getId()
                )) {
                    salaRepetida = true;
                    break;
                }
            }

            if (mismaFecha
                    && funcion.isActiva()
                    && !salaRepetida) {

                salas.add(funcion.getSala());
            }
        }

        vista.cargarSalas(salas);
        vista.activarDesactivarJCBSala(!salas.isEmpty());

        cargandoSelectores = false;
    }
    
    public void seleccionarSala() {

        if (cargandoSelectores) {
            return;
        }

        LocalDate fechaSeleccionada =
                vista.getFechaSeleccionada();

        Sala salaSeleccionada =
                vista.getSalaSeleccionada();

        cargandoSelectores = true;

        vista.limpiarHorarios();
        vista.limpiarButacas();
        vista.reiniciarCantidadBoletos();

        vista.activarDesactivarCantBoletos(false);
        vista.activarDesactivarSelectorButacas(false);

        funcionSeleccionada = null;

        if (fechaSeleccionada == null
                || salaSeleccionada == null) {

            vista.activarDesactivarJCBHorario(false);
            cargandoSelectores = false;
            return;
        }

        ArrayList<LocalTime> horarios = new ArrayList<>();

        ArrayList<Funcion> funciones =
                funcionDAO.getListaFuncionesPorPelicula(pelicula);

        for (Funcion funcion : funciones) {

            boolean mismaFecha =
                    funcion.getFecha().compareTo(
                            fechaSeleccionada
                    ) == 0;

            boolean mismaSala =
                    funcion.getSala().getId()
                            .equalsIgnoreCase(
                                    salaSeleccionada.getId()
                            );

            if (mismaFecha
                    && mismaSala
                    && funcion.isActiva()
                    && !horarios.contains(funcion.getHora())) {

                horarios.add(funcion.getHora());
            }
        }

        vista.cargarHorarios(horarios);
        vista.activarDesactivarJCBHorario(
                !horarios.isEmpty()
        );

        cargandoSelectores = false;
    }
    
    public void seleccionarHorario() {

        if (cargandoSelectores) {
            return;
        }

        LocalDate fechaSeleccionada =
                vista.getFechaSeleccionada();

        Sala salaSeleccionada =
                vista.getSalaSeleccionada();

        LocalTime horarioSeleccionado =
                vista.getHorarioSeleccionado();

        funcionSeleccionada = null;

        vista.limpiarButacas();
        vista.reiniciarCantidadBoletos();

        vista.activarDesactivarCantBoletos(false);
        vista.activarDesactivarSelectorButacas(false);

        if (fechaSeleccionada == null
                || salaSeleccionada == null
                || horarioSeleccionado == null) {

            return;
        }

        ArrayList<Funcion> funciones =
                funcionDAO.getListaFuncionesPorPelicula(
                        pelicula
                );

        for (Funcion funcion : funciones) {

            boolean mismaFecha =
                    funcion.getFecha().compareTo(
                            fechaSeleccionada
                    ) == 0;

            boolean mismaSala =
                    funcion.getSala().getId()
                            .equalsIgnoreCase(
                                    salaSeleccionada.getId()
                            );

            boolean mismoHorario =
                    funcion.getHora().compareTo(
                            horarioSeleccionado
                    ) == 0;

            if (mismaFecha
                    && mismaSala
                    && mismoHorario
                    && funcion.isActiva()) {

                funcionSeleccionada = funcion;
                break;
            }
        }

        if (funcionSeleccionada == null) {
            return;
        }

        ArrayList<Butaca> butacas = crearButacasPrueba(funcionSeleccionada);

        vista.cargarButacas(butacas);

        agregarListenersButacas();

        vista.activarDesactivarCantBoletos(true);
        vista.activarDesactivarSelectorButacas(true);
    }
    

    private ArrayList<Butaca> crearButacasPrueba(
            Funcion funcion
    ) {
        ArrayList<Butaca> butacas =
                new ArrayList<>();

        ArrayList<String> codigosOcupados =
                asientoFuncionDAO
                        .getButacasOcupadasPorFuncion(
                                funcion.getId()
                        );

        String[] filas = {
            "A", "B", "C", "D", "E", "F"
        };

        int contadorId = 1;

        for (String fila : filas) {

            int cantidad;

            if (fila.equals("F")) {
                cantidad = 12;
            } else {
                cantidad = 9;
            }

            for (int numero = 1;
                    numero <= cantidad;
                    numero++) {

                String codigo = fila + numero;

                EstadoButaca estado =
                        EstadoButaca.DISPONIBLE;

                if (codigosOcupados.contains(codigo)) {
                    estado = EstadoButaca.OCUPADA;
                }

                Butaca butaca = new Butaca(
                        "B" + contadorId,
                        fila,
                        numero,
                        estado
                );

                butacas.add(butaca);
                contadorId++;
            }
        }

        return butacas;
    }
    
    
    public void iniciarInvitado() {
        iniciar();
    }
        
    
}
