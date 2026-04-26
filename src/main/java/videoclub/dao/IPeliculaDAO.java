package videoclub.dao;

import videoclub.model.Pelicula;

import java.util.List;

/**
 * Interfaz DAO de la clase {@link videoclub.model.Pelicula}
 */
public interface IPeliculaDAO {

    /**
     * Inserta una nueva película en la base de datos.
     *
     * @param p la película a insertar, no debe ser {@code null} ni tener un ID ya existente
     */
    public void insertar(Pelicula p);

    /**
     * Actualiza los datos de una película existente en la base de datos, salvo el ID.
     *
     * @param p la película con los datos actualizados, debe tener un ID válido ya existente y no debe ser {@code null}
     */
    public void actualizar(Pelicula p);

    /**
     * Elimina una película de la base de datos.
     *
     * @param p la película a eliminar, debe tener un ID válido ya existente y no debe ser {@code null}
     */
    public void eliminar(Pelicula p);

    /**
     * Busca una película por su ID.
     *
     * @param id texto con el ID de la película
     * @return la {@link Pelicula} con ese ID, o {@code null} si no existe
     */
    public Pelicula buscarPorId(String id);

    /**
     * Recupera todas las películas almacenadas en la base de datos.
     *
     * @return lista con todas las películas, o lista vacía si no hay ninguna
     */
    public List<Pelicula> listarTodas();

    /**
     * Recupera las películas cuyo año de estreno coincide con el indicado.
     *
     * @param anyo el año de estreno a buscar
     * @return lista de películas del año {@code anyo}, o lista vacía si no hay coincidencias
     */
    public List<Pelicula> listarPorAnyo(int anyo);

    /**
     * Recupera las películas con la valoración indicada.
     *
     * @param valoracion la valoración a buscar
     * @return lista de películas con esa valoración {@code valoracion}, o lista vacía si no hay coincidencias
     */
    public List<Pelicula> listarPorValoracion(int valoracion);
}
