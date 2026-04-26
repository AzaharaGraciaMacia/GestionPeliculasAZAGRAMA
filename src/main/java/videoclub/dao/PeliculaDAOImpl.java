package videoclub.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import videoclub.model.Pelicula;

import java.util.List;

/**
 * Implementación para Hibernate del DAO de la clase {@link videoclub.model.Pelicula}
 */
public class PeliculaDAOImpl implements IPeliculaDAO {

    /**
     * Cuenta el número de películas insertadas en la BD
     *
     * @return número total de películas.
     */
    public static Long contarPeliculas() {
        EntityManager em = HibernateUtil.getEMF().createEntityManager();

        try {
            Long total = em.createNamedQuery(Pelicula.CONTAR_TODAS, Long.class)
                    .getSingleResult();
            return total;
        } catch (Exception e) {
            System.out.println("Error en la BD." + e.getMessage());
        }finally {
            em.close();
        }
        return 0L;
        }

    /**
     * Comprueba si hay una película con ese ID en la BD
     *
     * @param idPelicula texto con ID a buscar
     * @return true si ya existe
     */
    public static boolean existePelicula(String idPelicula){
            EntityManager em = HibernateUtil.getEMF().createEntityManager();
            boolean existe = false;
            try {
                existe = em.find(Pelicula.class, idPelicula) != null;
            }  catch (Exception e) {
                System.out.println("Error en la BD." + e.getMessage());
            } finally {
            em.close();}
            return existe;
        }

    /**
     * Busca una película por su ID.
     *
     * @param id texto con el ID de la película
     * @return la {@link Pelicula} con ese ID, o {@code null} si no existe
     */
    @Override
        public Pelicula buscarPorId(String id) {
        EntityManager em = HibernateUtil.getEMF().createEntityManager();
            Pelicula p = null;
            try {
                p = em.find(Pelicula.class, id);

                if (p == null) {
                    System.out.println("No existe la película con id: " + id);}

            } catch (Exception e) {
                System.out.println("Error en la BD." + e.getMessage());
            }finally {
                em.close();
            }
            return p;
        }

    /**
     * Recupera todas las películas almacenadas en la base de datos.
     *
     * @return lista con todas las películas, o lista vacía si no hay ninguna
     */
        @Override
        public List<Pelicula> listarTodas() {
            EntityManager em = HibernateUtil.getEMF().createEntityManager();
            try {
                return em.createNamedQuery(Pelicula.LISTAR_TODAS, Pelicula.class)
                        .getResultList();
            }catch (Exception e) {
                System.out.println("Error en la BD." + e.getMessage());
            }finally {
                em.close();
            }
            return null;
        }

    /**
     * Recupera las películas cuyo año de estreno coincide con el indicado.
     *
     * @param anyo el año de estreno a buscar
     * @return lista de películas del año {@code anyo}, o lista vacía si no hay coincidencias
     */
    @Override
    public List<Pelicula> listarPorAnyo(int anyo) {
        EntityManager em = HibernateUtil.getEMF().createEntityManager();
        try {
            return em.createNamedQuery(Pelicula.BUSCAR_POR_ANYO, Pelicula.class)
                    .setParameter(Pelicula.BUSCAR_POR_ANYO_anyo, anyo).getResultList();
        }catch (Exception e) {
            System.out.println("Error en la BD." + e.getMessage());
        }finally {
            em.close();
        }
        return null;
    }

    /**
     * Recupera las películas con la valoración indicada.
     *
     * @param valoracion la valoración a buscar
     * @return lista de películas con esa valoración {@code valoracion}, o lista vacía si no hay coincidencias
     */
        @Override
        public List<Pelicula> listarPorValoracion(int valoracion) {
            EntityManager em = HibernateUtil.getEMF().createEntityManager();
            try {
                return em.createNamedQuery(Pelicula.BUSCAR_POR_VALORACION, Pelicula.class)
                        .setParameter(Pelicula.BUSCAR_POR_VALORACION_valoracion, valoracion).getResultList();
            }catch (Exception e) {
                System.out.println("Error en la BD." + e.getMessage());
            }finally {
                em.close();
            }
            return null;
        }

    /**
     * Inserta una nueva película en la base de datos.
     *
     * @param p la película a insertar, no debe ser {@code null} ni tener un ID ya existente
     */
        @Override
        public void insertar(Pelicula p){
            EntityTransaction tx = null;
            EntityManager em = HibernateUtil.getEMF().createEntityManager();

            try {

            tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            tx.commit();

            } catch (Exception e) {
                HibernateUtil.rollbackSeguro(tx);
                System.out.println("Error al agregar." + e.getMessage());
            }finally {
                em.close();
            }
        }

    /**
     * Actualiza los datos de una película existente en la base de datos, salvo el ID.
     *
     * @param p la película con los datos actualizados, debe tener un ID válido ya existente y no debe ser {@code null}
     */
    @Override
    public void actualizar(Pelicula p) {
        EntityManager em = HibernateUtil.getEMF().createEntityManager();
        EntityTransaction tx = null;
        Pelicula peliculaAModificar = null;
        try {
            String id = p.getIdPelicula();
            peliculaAModificar = em.find(Pelicula.class, id);

            if (peliculaAModificar == null) {
                System.out.println("No existe la película con id: ." + id);
            } else {
                tx = em.getTransaction();
                tx.begin();

                em.merge(p);

                tx.commit();
            }
            } catch(Exception e){
                HibernateUtil.rollbackSeguro(tx);
                System.out.println("Error al modificar." + e.getMessage());
            } finally{
                em.close();
            }

    }

    /**
     * Elimina una película de la base de datos.
     *
     * @param p la película a eliminar, debe tener un ID válido ya existente y no debe ser {@code null}
     */
    @Override
    public void eliminar(Pelicula p) {
        EntityTransaction tx = null;
        EntityManager em = HibernateUtil.getEMF().createEntityManager();
        Pelicula peliculaAEliminar = null;
        try {
            String id = p.getIdPelicula();
            peliculaAEliminar = em.find(Pelicula.class, id);

            if (peliculaAEliminar == null) {

                System.out.println("No existe la película con id: ." + id);

            } else {
                tx = em.getTransaction();
                tx.begin();

                em.remove(peliculaAEliminar);

                tx.commit();

            }
        }catch (Exception e) {
            HibernateUtil.rollbackSeguro(tx);
            System.out.println("Error al eliminar." + e.getMessage());
        }finally {
            em.close();
        }
    }


}
