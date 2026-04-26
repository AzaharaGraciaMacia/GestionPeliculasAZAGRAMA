package videoclub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NamedQueries({
        //Permite saber si hay películas en la BD para poder insertar unos ejemplos
        @NamedQuery(
                name = "Pelicula.contarTodas",
                query = "SELECT COUNT(p) FROM Pelicula p"
        ),
        //Permite obtener el listado de todas las películas
        @NamedQuery(
                name = "Pelicula.listarTodas",
                query = "SELECT p FROM Pelicula p"
        ),
        //Permite obtener el listado de películas por año
        @NamedQuery(
                name = "Pelicula.buscarAnyo",
                query = "SELECT p FROM Pelicula p WHERE p.anyo = :anyo"
        ),
        //Permite obtener el listado de películas por valoración
        @NamedQuery(
                name = "Pelicula.buscarValoracion",
                query = "SELECT p FROM Pelicula p WHERE p.valoracion = :valoracion"
        )
})

/**
 * La clase {@code Pelicula} representa a una película en el sistema.
 * Contiene información sobre la película y la declaración de entidad y NamedQueries.
 */
public class Pelicula implements Serializable {
        /**
         * Clave primaria
         */
        @Id
        private String idPelicula;

        private String titulo;
        private String director;
        private int anyo;
        private int valoracion;

       /**
        * Para no equivocarnos al llamar a las consultas por nombre, creamos
        * constantes con los nombres de las NamedQueries
        */
        public static final String CONTAR_TODAS="Pelicula.contarTodas";
        public static final String LISTAR_TODAS="Pelicula.listarTodas";
        public static final String BUSCAR_POR_ANYO="Pelicula.buscarAnyo";
        public static final String BUSCAR_POR_ANYO_anyo="anyo";
        public static final String BUSCAR_POR_VALORACION="Pelicula.buscarValoracion";
        public static final String BUSCAR_POR_VALORACION_valoracion="valoracion";

        /**
         * Cabecera para imprimir listados
         */
        public static final String FORMATO_IMPRESION="%-10s %-30s %-30s %-10s %-20s\n";
        public static final String CABECERA_LISTADO= String.format(FORMATO_IMPRESION,
                "ID", "Título", "Director", "Año", "Valoración (sobre 10)" +
                        "\n-----------------------------------------------------------------------------------------------------------");

        /**
         * Constructor vacío obligatorio para Hibernate
         */
        public Pelicula() {}

        /**
         * Constructor para crear una nueva instancia de la clase {@code Pelicula}.
         * Inicializa el ID, título, director, año y la valoración.
         *
         * @param idPelicula texto con el ID de la película
         * @param titulo texto con el título de la película
         * @param director texto con el nombre del director
         * @param anyo entero desde 1900 hasta hoy
         * @param valoracion entero entre 1 y 10
         */
        public Pelicula(String idPelicula, String titulo, String director, int anyo, int valoracion) {
                this.idPelicula = idPelicula;
                this.titulo = titulo;
                this.director = director;
                setAnyo(anyo);
                setValoracion(valoracion);
        }

        /**
         * Obtiene el ID de la película
         *
         * @return texto con el ID
         */
        public String getIdPelicula() {
                return idPelicula;
        }

        /**
         * Establece el ID de la película
         *
         * @param idPelicula texto con el nuevo ID
         */
        public void setIdPelicula(String idPelicula) {
                this.idPelicula = idPelicula;
        }

        /**
         * Obtiene el título de la película
         *
         * @return texto con el título
         */
        public String getTitulo() {
                return titulo;
        }

        /**
         * Establece el título de la película
         *
         * @param titulo texto con el nuevo título
         */
        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        /**
         * Obtiene el director de la película
         *
         * @return texto con el nombre del director
         */
        public String getDirector() {
                return director;
        }

        /**
         * Establece el director de la película
         *
         * @param director texto con el nuevo nombre del director
         */
        public void setDirector(String director) {
                this.director = director;
        }

        /**
         * Obtiene el año de la película
         *
         * @return entero con el año
         */
        public int getAnyo() {
                return anyo;
        }

        /**
         * Establece el año de la película, previa validación
         *
         * @param anyo entero entre 1900 y la fecha actual
         */
        public void setAnyo(int anyo) {
                if(anyo>=1900&&anyo<= LocalDate.now().getYear()){
                this.anyo = anyo;}
                else{
                        System.out.println("El año debe estar entre 1900 y la actualidad");
                }
        }

        /**
         * Obtiene la valoración de la película
         *
         * @return entero con la valoración
         */
        public int getValoracion() {
                return valoracion;
        }

        /**
         * Establece la valoración de la película, previa validación
         *
         * @param valoracion entero entre 1 y 10
         */
        public void setValoracion(int valoracion) {
                if (valoracion > 0 && valoracion <= 10) {
                this.valoracion = valoracion;}
                else{
                        System.out.println("La valoración debe estar entre 1 y 10");
                }
        }

        /**
         * Devuelve una representación en cadena del objeto {@code Pelicula}.
         * Actualmente, devuelve una cadena con formato de tabla.
         *
         * @return Una cadena que representa la película.
         */
        @Override
        public String toString() {
                return String.format(FORMATO_IMPRESION, idPelicula, titulo, director, anyo, valoracion);
        }
}
