
-----

# 🚀 Foro Hub - API REST

¡Bienvenido al repositorio de **Foro Hub**\! Este proyecto es una API REST robusta y segura, desarrollada como parte del desafío del curso **Java Back End** de Alura Latam y Oracle Next Education. Su objetivo principal es simular las funcionalidades básicas de un foro, permitiendo la gestión de tópicos.



## 📜 Descripción del Proyecto

Foro Hub es una API que facilita la interacción de usuarios a través de la creación, lectura, actualización y eliminación (**CRUD**) de tópicos en un foro. La aplicación está construida sobre una arquitectura sólida, utilizando **Java 17** y el ecosistema **Spring Boot**.

### 🔒 Seguridad y Autenticación

Para garantizar la integridad y la privacidad de los datos, la API implementa un sistema de seguridad robusto. Las operaciones que modifican el contenido del foro (crear, actualizar, eliminar) están protegidas con **JWT (JSON Web Tokens)**, lo que asegura que solo los usuarios autenticados puedan realizarlas.

-----

## 🛠️ Tecnologías Utilizadas

Este proyecto fue desarrollado utilizando las siguientes tecnologías:

  * **Java 17**: El lenguaje de programación principal.
  * **Spring Boot**: Framework que simplifica la creación de la API REST.
  * **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
  * **MySQL**: Base de datos relacional para el almacenamiento de los tópicos.
  * **Flyway**: Herramienta para la gestión de migraciones de la base de datos.
  * **Spring Security**: Módulo para la implementación de la seguridad y autenticación.
  * **JWT**: Sistema de autenticación basado en tokens con Auth0.
  * **Postman**: Herramienta utilizada para probar los endpoints de la API.

-----

## 📊 Flujo de la Aplicación

A continuación, se presenta un diagrama de flujo que ilustra el proceso de interacción de un usuario con la API.

```
+---------------------+           +---------------------+
|      Usuario        |           |      API REST       |
+---------------------+           +---------------------+
           |                                 |
           | 1. Solicita token JWT           |
           |-------------------------------->|
           |                                 |
           | 2. Valida credenciales          |
           |<--------------------------------|
           |                                 |
+---------------------+                      |
|      Usuario        |                      |
| (con token válido)  |                      |
+---------------------+                      |
           |                                 |
           | 3. Solicita ruta protegida      |
           |   (Ej: Crear tópico)            |
           |-------------------------------->|
           |                                 |
           |                         +----------------+
           |                         |   Base de Datos|
           |                         |     (MySQL)    |
           |                         +----------------+
           |                                 |
           | 4. Realiza operación CRUD       |
           |   (Crear, leer, actualizar,     |
           |    eliminar)                    |
           |<--------------------------------|
           |                                 |
           | 5. Recibe respuesta             |
           |<--------------------------------|
           |   (Éxito o error)               |
```

-----

## 🚀 Endpoints de la API

La API de Foro Hub expone los siguientes endpoints:

| Método | Endpoint                    | Descripción                                      | Autenticación |
| :----- | :-------------------------- | :----------------------------------------------- | :------------ |
| `POST` | `/autenticacion`            | Obtiene un token JWT.                            | No requerida  |
| `GET`  | `/topicos`                  | Obtiene la lista de todos los tópicos.           | No requerida  |
| `GET`  | `/topicos/{id}`             | Obtiene los detalles de un tópico específico.    | No requerida  |
| `POST` | `/topicos`                  | Crea un nuevo tópico.                            | **JWT requerido** |
| `PUT`  | `/topicos/{id}`             | Actualiza la información de un tópico.           | **JWT requerido** |
| `DELETE`| `/topicos/{id}`             | Elimina un tópico específico.                    | **JWT requerido** |

-----

## ⚙️ Instalación y Configuración

Sigue estos sencillos pasos para poner la aplicación en marcha en tu entorno local.

### 1\. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/foro-hub.git
cd foro-hub
```

### 2\. Configurar la base de datos MySQL

Crea una base de datos con el nombre `foro_hub` y configura las credenciales de conexión en el archivo `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 3\. Ejecutar las migraciones de Flyway

```bash
mvn flyway:migrate
```

### 4\. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

-----

## ✅ Ejemplos de Peticiones

Puedes utilizar Postman para probar cada uno de los endpoints de la API.

### ➡️ Obtener token JWT

`POST /autenticacion`
**Body:**

```json
{
  "username": "usuario",
  "password": "contraseña"
}
```

### ➡️ Crear un nuevo tópico

`POST /topicos`
**Header:** `Authorization: Bearer <tu_token_jwt>`
**Body:**

```json
{
  "titulo": "¿Cómo usar Spring Boot?",
  "descripcion": "Tengo dudas sobre cómo configurar Spring Boot para un proyecto."
}
```

-----

## 🤝 Contribuciones y Comunidad

Si tienes alguna sugerencia o quieres contribuir al proyecto, ¡no dudes en contactarme\! También puedes unirte a la comunidad de Discord de Alura para compartir tus avances y obtener ayuda.

-----

## 📄 Licencia

Este proyecto se encuentra bajo la **Licencia MIT**. Puedes revisar el archivo `LICENSE` para más detalles.


















































