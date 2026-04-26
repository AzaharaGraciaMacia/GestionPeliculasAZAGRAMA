package videoclub.view;

import jakarta.persistence.PersistenceException;
import videoclub.dao.PeliculaDAOImpl;
import videoclub.model.Pelicula;

import java.time.LocalDate;
import java.util.List;

/**
 * Vista de la clase {@link Pelicula}
 */
public class PeliculaView {

    /**
     * Muestra una tabla con las películas
     *
     * @param peliculas Lista de películas a mostrar
     */
    public void mostrarPeliculas(List<Pelicula> peliculas) {
        System.out.println("Lista de Películas:\n");

        ConsolaView.mostrar(Pelicula.CABECERA_LISTADO);

        peliculas.forEach(System.out::println);

    }

    /**
     * Crea un objeto {@link Pelicula} a partir de la lectura de datos por consola
     *
     * @return objeto pelicula
     */
    public Pelicula crearPelicula() throws PersistenceException, Exception {

        System.out.println("\nALTA DE NUEVA PELÍCULA");

        String idPelicula = ConsolaView.leerTexto("Introduce el ID: ");
        while (PeliculaDAOImpl.existePelicula(idPelicula)){
            System.out.println("Error: ya existe una película con el ID: ." + idPelicula);
            idPelicula = ConsolaView.leerTexto("Introduce otro ID: ");
        }
        String titulo = ConsolaView.leerTexto("Introduce el título: ");
        String director = ConsolaView.leerTexto("Introduce el nombre del director: ");
        int anyo = ConsolaView.leerEntero("Introduce el año: ");
        while (anyo < 1900 || anyo > LocalDate.now().getYear()) {
            System.out.println("Error: año inválido.");
            anyo = ConsolaView.leerEntero("Introduce el año: ");
        }
        int valoracion = ConsolaView.leerEntero("Introduce la valoración: ");
        while (valoracion <= 0 || valoracion > 10) {
            System.out.println("Error: la valoración debe estar entre 1 e 10.");
            valoracion = ConsolaView.leerEntero("Introduce la valoración: ");
        }
            return new Pelicula(idPelicula, titulo, director, anyo, valoracion);
    }

    /**
     * Pide al usuario que introduzca por consola el ID de la película que desea eliminar
     *
     * @return texto ID de película
     */
    public String obtenerIdAEliminar() {
        return ConsolaView.leerTexto("Introduce el ID de la película a eliminar:");
    }

    /**
     * Crea un nuevo objeto {@link Pelicula} a partir de otro con los datos actualizados leídos por consola
     *
     * @param pelicula película que se desea actualizar
     * @return objeto película actualizada
     */
    public Pelicula obtenerDatosActualizados(Pelicula pelicula) throws PersistenceException, Exception  {

        System.out.println("\nMODIFICA PELÍCULA");

        String titulo = ConsolaView.leerTexto("Introduce el título: ");
        String director = ConsolaView.leerTexto("Introduce el nombre del director: ");
        int anyo = ConsolaView.leerEntero("Introduce el año: ");
        while (anyo < 1900 || anyo > LocalDate.now().getYear()) {
            System.out.println("Error: año inválido.");
            anyo = ConsolaView.leerEntero("Introduce el año: ");
        }
        int valoracion = ConsolaView.leerEntero("Introduce la valoración: ");
        while (valoracion <= 0 || valoracion > 10) {
            System.out.println("Error: la valoración debe estar entre 1 e 10.");
            valoracion = ConsolaView.leerEntero("Introduce la valoración: ");
        }
        return new Pelicula(pelicula.getIdPelicula(), titulo, director, anyo, valoracion);
    }

    /**
     * Pide al usuario que introduzca por consola el ID de la película que desea recuperar
     *
     * @return texto con ID de la película
     */
    public String obtenerIdPelicula() {
        return ConsolaView.leerTexto("Introduce el ID de la película:");
    }

    /**
     * Pide al usuario que introduzca por consola el año de las películas que desea recuperar
     *
     * @return entero con año válido
     */
    public int obtenerAnyo() {
        int anyo = 0;
        boolean valorValido = false;
        while (!valorValido) {
            int respuestaAnyo = ConsolaView.leerEntero("Introduce el año del que quieras mostrar las películas:");
            if (respuestaAnyo >= 1900 && respuestaAnyo <= LocalDate.now().getYear()) {
                anyo = respuestaAnyo;
                valorValido = true;}
            else
                System.out.println("Valor de año no válido.");
        }
        return anyo;
    }

    /**
     * Pide al usuario que introduzca por consola la valoración de las películas que desea recuperar
     *
     * @return entero con valoración válida
     */
    public int obtenerValoracion() {
        int valoracion = 0;
        boolean valorValido = false;
        while (!valorValido) {
            int respuestaValoracion = ConsolaView.leerEntero("Introduce la valoración de la que quieras mostrar las películas:");
            if (respuestaValoracion > 0 && respuestaValoracion <= 10) {
                valoracion = respuestaValoracion;
                valorValido = true;}
            else
                System.out.println("Valor no válido, debe ser entre 1 y 10.");
        }
        return valoracion;
    }

    /**
     * Muestra una tabla con la película solicitada
     *
     * @param pelicula película que se desea visualizar
     */
    public void mostrarPelicula(Pelicula pelicula) {
        if (pelicula != null){
            ConsolaView.mostrar(Pelicula.CABECERA_LISTADO);
            System.out.println(pelicula);}
        else
            System.out.println("La película no existe");
    }


    /**
     * Muestra el menú de películas
     */
    public void mostrarMenu() {
        ConsolaView.limpiarPantalla();
        ConsolaView.mostrarTitulo("GESTIÓN DE PELÍCULAS");
        System.out.println("1. Agregar película");
        System.out.println("2. Modificar película");
        System.out.println("3. Eliminar película");
        System.out.println("4. Consultar película");
        System.out.println("5. Listar todas las películas");
        System.out.println("6. Mostrar todas las películas de un año.");
        System.out.println("7. Mostrar todas las películas por nota de valoración.");
        System.out.println("0. Volver");
    }

}
