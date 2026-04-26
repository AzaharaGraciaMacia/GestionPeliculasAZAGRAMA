package videoclub.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import videoclub.dao.HibernateUtil;
import videoclub.dao.PeliculaDAOImpl;
import videoclub.model.Pelicula;
import videoclub.view.ConsolaView;
import videoclub.view.PeliculaView;

import java.util.List;

/**
 * Controlador de la clase {@link Pelicula}
 */
public class PeliculaController {

    /**
     * Vista de película
     */
    private PeliculaView peliculaView =  new PeliculaView();

    /**
     * Utilidad de consola
     */
    private ConsolaView consolaView =  new ConsolaView();

    /**
     * Implementación DAO para Hibernate de película
     */
    private PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl();

    /**
     * Inicializa la tabla películas con ejemplos si está vacía
     */
    public static void inicializarPeliculas() {
        EntityTransaction tx = null;

        try {
            Long total = PeliculaDAOImpl.contarPeliculas();
            EntityManager em = HibernateUtil.getEMF().createEntityManager();

            if (total == 0) {
                System.out.println("No hay películas en la base de datos.");
                System.out.println("Insertando 6 películas de ejemplo...");

                tx = em.getTransaction();
                tx.begin();

                em.persist(new Pelicula("2026P1", "Título Ejemplo 1", "Director ejemplo 1", 1995, 7));
                em.persist(new Pelicula("2026P2", "Título Ejemplo 2", "Director ejemplo 2", 2001, 8));
                em.persist(new Pelicula("2026P3", "Título Ejemplo 3", "Director ejemplo 1", 2021, 5));
                em.persist(new Pelicula("2026P4", "Título Ejemplo 4", "Director ejemplo 2", 2007, 10));
                em.persist(new Pelicula("2026P5", "Título Ejemplo 5", "Director ejemplo 1",1983,9));
                em.persist(new Pelicula("2026P6", "Título Ejemplo 6", "Director ejemplo 2", 1995,5));
                em.persist(new Pelicula("2026P7", "Título Ejemplo 7", "Director ejemplo 1",1983,6));
                em.persist(new Pelicula("2026P8", "Título Ejemplo 8", "Director ejemplo 2", 1995,3));
                em.persist(new Pelicula("2026P9", "Título Ejemplo 9", "Director ejemplo 1",2021,7));
                em.persist(new Pelicula("2026P10", "Título Ejemplo 10", "Director ejemplo 2", 1995,4));

                tx.commit();

                System.out.println("Películas de ejemplo insertadas correctamente.");
            }
        } catch (Exception e) {
            HibernateUtil.rollbackSeguro(tx);
            System.out.println("Error al inicializar las películas de ejemplo.");
        }
    }

    /**
     * Muestra todas las peliculas
     */
    public void mostrarTodasLasPeliculas() {
        try {
            List<Pelicula> peliculas = peliculaDAO.listarTodas();
            peliculaView.mostrarPeliculas(peliculas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra una película por su ID
     */
    public void mostrarPeliculaID() {
        try {
            String idPelicula = peliculaView.obtenerIdPelicula();
            Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
            peliculaView.mostrarPelicula(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra todas las películas de un año
     */
    public void mostrarPeliculasAnyo() {
        try {
            int anyo = peliculaView.obtenerAnyo();
            List<Pelicula> peliculas = peliculaDAO.listarPorAnyo(anyo);
            peliculaView.mostrarPeliculas(peliculas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra todas las películas con una valoración específica
     */
    public void mostrarPeliculasValoracion() {
        try {
            int valoracion = peliculaView.obtenerValoracion();
            List<Pelicula> peliculas = peliculaDAO.listarPorValoracion(valoracion);
            peliculaView.mostrarPeliculas(peliculas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Agrega una nueva película
     */
    public void agregarPelicula() {
        try {
            Pelicula pelicula = peliculaView.crearPelicula();
            peliculaDAO.insertar(pelicula);
            System.out.println("Película insertada correctamente en la BD.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifica una película existente
     */
    public void modificarPelicula() {
        try {
            String idPelicula = peliculaView.obtenerIdPelicula();
            Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
            if(pelicula != null) {
            Pelicula peliculaModificada = peliculaView.obtenerDatosActualizados(pelicula);
            peliculaDAO.actualizar(peliculaModificada);
            System.out.println("Película modificada correctamente.");}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina una película existente
     */
    public void eliminarPelicula() {
        try {
            String idPelicula = peliculaView.obtenerIdAEliminar();
            Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
            if(pelicula != null) {
                peliculaDAO.eliminar(pelicula);
                System.out.println("Película eliminada correctamente.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Inicia el menú de películas
     */
    public void iniciar() {
        int opcion;

        do {
            peliculaView.mostrarMenu();
            opcion = consolaView.leerEntero("Opción: ");

            switch (opcion) {

                case 1 -> agregarPelicula();
                case 2 -> modificarPelicula();
                case 3 -> eliminarPelicula();
                case 4 -> mostrarPeliculaID();
                case 5 -> mostrarTodasLasPeliculas();
                case 6 -> mostrarPeliculasAnyo();
                case 7 -> mostrarPeliculasValoracion();
                case 0 -> consolaView.mostrar("Volviendo...");
                default -> consolaView.mostrar("Opción no válida.");
            }

        } while (opcion != 0);
    }


}
