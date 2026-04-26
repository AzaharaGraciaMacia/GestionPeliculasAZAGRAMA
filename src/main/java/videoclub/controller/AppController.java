package videoclub.controller;

import videoclub.dao.HibernateUtil;
import videoclub.model.Pelicula;
import videoclub.view.ConsolaView;

/**
 * Controlador principal de la aplicación
 *
 * <p>Presenta el menú general y delega la ejecución en el controlador específico de películas.</p>
 */
public class AppController {
    /**
     * Controlador de {@link Pelicula}, de clase {@link PeliculaController}
     */
    private PeliculaController peliculaController;

    /**
     * Arranca el menú principal
     */
    public void iniciar() {

        int opcion;
        try {
            peliculaController = new PeliculaController();

            do {
                PeliculaController.inicializarPeliculas();
                mostrarMenuPrincipal();
                opcion = ConsolaView.leerEntero("Opción: ");

                switch (opcion) {
                    case 1 -> peliculaController.iniciar();
                    case 0 -> ConsolaView.mostrar("Gracias por usar la aplicación.\nHasta pronto...");
                    default -> ConsolaView.mostrar("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (Exception e){
            ConsolaView.mostrarTitulo("ERROR EN LA APLICACIÓN");
            ConsolaView.mostrar("Error: "+e.getMessage());
            ConsolaView.mostrar(e.getStackTrace().toString());
            ConsolaView.pausar("Pulse intro para terminar...");
        }finally {
            HibernateUtil.cerrar();
            ConsolaView.cerrarScanner();
        }
    }

    /**
     * Muestra el menú principal de la aplicación.
     */
    private void mostrarMenuPrincipal() {
        ConsolaView.saltoLinea();
        ConsolaView.mostrarTitulo("PROYECTO VIDEOCLUB AZAHARA GRACIA MACIÁ - APLICACIÓN DE CONSOLA");
        ConsolaView.mostrar("1. Gestionar películas");
        ConsolaView.mostrar("0. Salir");
    }
}

