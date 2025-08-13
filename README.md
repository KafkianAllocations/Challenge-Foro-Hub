Foro Hub - API REST

Foro Hub es una API REST creada como parte del desafío del curso Java Back End de Alura Latam y Oracle Next Education. En este proyecto, implementé funcionalidades CRUD para la creación, visualización, actualización y eliminación de tópicos en un foro, utilizando Java 17, Spring Boot, Maven, MySQL, Flyway, JWT, y Spring Security.
Descripción del Proyecto

La API permite a los usuarios interactuar con un foro en el que pueden:

    Crear nuevos tópicos.

    Visualizar todos los tópicos creados.

    Consultar un tópico específico.

    Actualizar o eliminar tópicos existentes.

Además, se implementa seguridad a través de tokens JWT para proteger ciertas rutas, como la creación, actualización y eliminación de tópicos.
Tecnologías Utilizadas

    Java 17: Lenguaje de programación utilizado para desarrollar la API.

    Spring Boot: Framework para la creación de la API REST.

    Maven: Herramienta de gestión de dependencias y construcción de proyectos.

    MySQL: Base de datos relacional para almacenar la información de los tópicos.

    Flyway: Herramienta de migración de base de datos para gestionar cambios en el esquema.

    Spring Security: Framework para implementar seguridad en la API.

    JWT: Sistema de autenticación basado en tokens para proteger las rutas.

    Postman: Herramienta para probar los endpoints de la API.

Diagrama del Proyecto

A continuación, se presenta un diagrama ASCII básico que describe cómo los usuarios interactúan con la API y cómo las peticiones pasan a través del servidor y la base de datos.




+---------------+      +-------------------+      +------------------+
|   Usuario    | ---> |     API REST      | ---> |    Base de Datos |
|   (Cliente)  |      |  (Spring Boot)    |      |  (MySQL)         |
+---------------+      +-------------------+      +------------------+
       |                      |                           |
       |  1. Solicita Token   |  2. Valida JWT             |
       |--------------------->|--------------------------->|
       |                      |                           |
       |  3. Crea/Actualiza   |  4. CRUD en Base de Datos  |
       |     Tópico           | <--------------------------|
       |--------------------->|                           |
       |                      |                           |
       |  5. Responde a Tópico|  6. Guarda Respuesta      |
       |--------------------->|--------------------------->|
       |                      |                           |
       |  7. Consulta Tópicos |  8. Devuelve la lista     |
       |--------------------->| <--------------------------|





Descripción del flujo:

    El usuario solicita un token JWT a través del endpoint de autenticación.

    La API valida el JWT y, si es válido, permite la interacción con las rutas protegidas.

    El usuario puede crear o actualizar un tópico, el cual se guarda en la base de datos.

    La base de datos (MySQL) realiza operaciones CRUD para persistir los datos.

    El usuario responde a un tópico. La respuesta se guarda en la base de datos.

    El usuario consulta la lista de tópicos y recibe una respuesta con los tópicos almacenados.

Endpoints
1. Listar Tópicos

GET /topicos

    Descripción: Obtiene la lista de todos los tópicos existentes en la base de datos.

    Autenticación: No es requerida.

2. Crear un Tópico

POST /topicos

    Descripción: Crea un nuevo tópico. El cuerpo de la solicitud debe contener el título y la descripción del tópico.

    Autenticación: Requiere un token JWT válido.

    Campos:

        titulo: String (Requerido)

        descripcion: String (Requerido)

3. Mostrar un Tópico Específico

GET /topicos/{id}

    Descripción: Obtiene los detalles de un tópico específico por su ID.

    Autenticación: No es requerida.

4. Actualizar un Tópico

PUT /topicos/{id}

    Descripción: Actualiza la información de un tópico existente. Se puede actualizar el título y la descripción.

    Autenticación: Requiere un token JWT válido.

    Campos:

        titulo: String (Opcional)

        descripcion: String (Opcional)

5. Eliminar un Tópico

DELETE /topicos/{id}

    Descripción: Elimina un tópico específico por su ID.

    Autenticación: Requiere un token JWT válido.

6. Autenticación

POST /autenticacion

    Descripción: Obtiene un token JWT para acceder a las rutas protegidas.

    Cuerpo de la solicitud:

        username: String (Requerido)

        password: String (Requerido)

    Respuesta:

        token: String (JWT que debe usarse para autenticar las peticiones posteriores)



        Instalación y Configuración
1. Clonar el repositorio:

git clone https://github.com/tu-usuario/foro-hub.git
cd foro-hub

2. Configurar la base de datos MySQL

Crea una base de datos en MySQL con el nombre foro_hub (o el que prefieras) y asegúrate de que las credenciales coincidan con las que has configurado en el archivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=usuario
spring.datasource.password=contraseña



3. Ejecutar las migraciones

Usa Flyway para crear la estructura de la base de datos ejecutando:

mvn flyway:migrate


4. Ejecutar la aplicación

Para iniciar el servidor local, simplemente ejecuta:


mvn spring-boot:run


La API estará disponible en http://localhost:8080.
Pruebas de la API

Puedes probar los endpoints utilizando Postman o Insomnia. Aquí tienes algunos ejemplos de cómo hacer las peticiones:

    Crear un tópico (POST):

    POST /topicos
{
  "titulo": "¿Cómo uso Spring Boot?",
  "descripcion": "Tengo dudas sobre cómo configurar Spring Boot para un proyecto."
}


GET /topicos

GET /topicos/{id}


PUT /topicos/{id}
{
  "titulo": "Actualización sobre Spring Boot",
  "descripcion": "Ahora tengo más claridad sobre Spring Boot."
}


DELETE /topicos/{id}


POST /autenticacion
{
  "username": "usuario",
  "password": "contraseña"
}


Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxMjM0NTY3ODkwfQ.sJ0Ig1w-Dn..."
}



Consideraciones y Buenas Prácticas

    Asegúrate de manejar los errores de forma adecuada con respuestas HTTP claras (404 para no encontrado, 400 para malas solicitudes, etc.).

    La autenticación JWT es necesaria para la creación, actualización y eliminación de tópicos.

    Se recomienda probar la API utilizando herramientas como Postman o Insomnia.

Recomendaciones

    Trello: Utiliza un tablero de Trello para gestionar las tareas y dividir el proyecto en partes más pequeñas.

    Alura: Repasa los conceptos de APIs que puedes encontrar en los cursos de Alura.

    Personaliza el Proyecto: ¡Dale tu toque personal y comparte tu progreso en la comunidad de Discord!

Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo LICENSE para más detalles.
