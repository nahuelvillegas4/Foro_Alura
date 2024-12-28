# Foro_Alura
Ultimo challenge de la especializacion Backend de Alura

# Foro API Challenge

Este challenge consistió en la creación de nuestra primera API REST, la cual gestiona un foro para la creación de tópicos de discusión. La API incluye verificación de usuario mediante la generación de un token JWT.

## Endpoints

### `/login`
- **POST**: Requiere el envío de un archivo JSON con los campos `login` y `clave`. Este endpoint valida al usuario y retorna un token que será utilizado para autenticar las siguientes solicitudes.
  
  **Ejemplo de solicitud JSON**:
  ```json
  {
    "login": "usuarioEjemplo",
    "clave": "claveSecreta"
  }

### `/cursos`

- **POST**: Permite crear un nuevo curso de discusión para generar tópicos. Requiere un JSON con los siguientes campos:
  - `nombre`: Nombre del curso.
  - `categoria`: La categoría del curso, que debe coincidir con uno de los valores definidos en el enum de categorías.

  **Ejemplo de solicitud JSON**:
  ```json
  {
    "nombre": "Curso de Java",
    "categoria": "Programación"
  }

- **GET**: Devuelve la lista de todos los cursos disponibles. Esto es útil para obtener el nombre del curso necesario al crear un tópico.

---

### `/topicos`

- **POST**: Permite la creación de un nuevo tópico. Requiere los siguientes campos en el cuerpo de la solicitud:
  - `idUsuario`: El ID del usuario que crea el tópico.
  - `titulo`: El título del tópico.
  - `mensaje`: El contenido del tópico.
  - `nombreDelCurso`: El nombre del curso al que pertenece el tópico.  
    Además, es obligatorio enviar el token en el encabezado de autorización.

  **Ejemplo de solicitud JSON**:
  ```json
  {
    "idUsuario": 1,
    "titulo": "¿Cómo aprender Java?",
    "mensaje": "Estoy buscando recursos para aprender Java.",
    "nombreDelCurso": "Curso de Java"
  }


- **GET**: Permite obtener todos los tópicos creados. Se requiere el token en el encabezado de autorización.

---

### `/topicos/{id}`

- **GET**: Permite ver un tópico específico, identificando el tópico por su `id`. Requiere el token de autorización.

- **PUT**: Permite actualizar el título y el mensaje de un tópico específico. Requiere el token de autorización.

---

## Autenticación

La API utiliza un sistema de autenticación basado en un token JWT. El token se obtiene a través del endpoint `/login` y debe incluirse en los encabezados de autorización de las solicitudes posteriores para acceder a los endpoints que requieren autenticación.
