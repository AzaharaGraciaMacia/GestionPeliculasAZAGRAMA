package videoclub.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Clase para crear las conexiones compartidas con la BD en Hibernate
 */
public class HibernateUtil {
    /**
     * Fábrica de EntityManager (crea conexiones), inicializada una única vez
     * al cargar la clase (patrón Singleton).
     */
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("videoclub");

    /**
     * Devuelve la instancia compartida de EntityManagerFactory
     *
     * @return {@code EntityManagerFactory} inicializada con la unidad de persistencia {@code "videoclub"}
     */
    public static EntityManagerFactory getEMF() {
        return emf;
    }

    /**
     * Métod0 auxiliar para hacer rollback si algo falla.
     * Se usa cuando ocurre un error en una transacción.
     *
     * @param tx transacción que da error
     */
    public static void rollbackSeguro(EntityTransaction tx) {
        try {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } catch (Exception e) {
            System.out.println("Error al deshacer la transacción.");
        }
    }

    /**
     * Cierra la instancia compartida de EntityManagerFactory
     */
    public static void cerrar() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}
