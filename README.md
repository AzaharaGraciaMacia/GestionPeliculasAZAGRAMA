# GestionPeliculasAZAGRAMA


## Qué es y para qué sirve

Este proyecto es un CRUD con Hibernate para una base de datos MariaDB de un videoclub.

Con la aplicación de consola se pueden insertar, modificar o eliminar películas, así como consultar los datos de las mismas.


## Diseño

La aplicación está diseñada con un patrón modelo, vista, controlador para una vista desde consola


## Estructura del proyecto

```
app/            -> Capa donde se sitúa la clase Main, que inicia la aplicación.

controller/     -> Capa de controladores, que se encarga de unir y controlar al resto

dao/            -> Capa de "Data Access Object", que interactúa directamente con la BD

model/          -> Capa de declaración de entidades

view/           -> Capa de vistas, donde están los métodos para interactuar con la consola
```