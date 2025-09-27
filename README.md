# 📚 Gestión Estudiantil - Java Swing

Este proyecto es una **aplicación de escritorio en Java** desarrollada con **Swing**, que permite gestionar un registro de estudiantes.  
Lo más destacado es que incluye **búsquedas avanzadas** con **algoritmos clásicos de búsqueda binaria e interpolada**, aplicados sobre la lista de estudiantes.

---

## ✨ Funcionalidades principales

- **Agregar estudiantes**
  - Registra nombre, matrícula, grado y promedio.
  - Valida campos obligatorios, valores numéricos y evita duplicados.
  - Ordena automáticamente la lista por matrícula para optimizar búsquedas.

- **Búsqueda de estudiantes**
  - 🔎 **Por matrícula** → usando **búsqueda interpolada**.  
  - 🔎 **Por nombre** → usando **búsqueda binaria**.  

- **Modificar estudiantes**
  - Localiza por matrícula.
  - Permite actualizar nombre, grado o promedio con validaciones.

- **Mostrar todos**
  - Restaura la tabla con el listado completo.

- **Filtrar estudiantes**
  - Por grado escolar.
  - Por rango de matrículas.

- **Interfaz gráfica**
  - Basada en `JFrame`, `JTable`, `JPanel`, `JTextField`, `JButton` y `JComboBox`.
  - Botones con iconos y tooltips para mejorar la experiencia de usuario.

---

## 🧑‍💻 Clases principales

### `GestionEstudiantil`
- Controla la interfaz gráfica y las operaciones CRUD.
- Implementa los algoritmos de búsqueda **interpolada** y **binaria**.
- Contiene la lista de estudiantes en un `ArrayList`.

### `Estudiante`
- Clase modelo que representa a un estudiante con:
  - `nombre` (String)
  - `matricula` (int)
  - `grado` (String)
  - `promedio` (double)

---

## 📊 Algoritmos de búsqueda en el proyecto

- **Búsqueda interpolada (`busquedaInterpolada`)**  
  Calcula la posición estimada del valor buscado dentro de un rango usando una fórmula de interpolación.  
  Más eficiente que la búsqueda binaria en listas numéricas distribuidas uniformemente (como las matrículas).

- **Búsqueda binaria (`busquedaBinariaNombre`)**  
  Divide la lista ordenada por nombre en mitades sucesivas hasta encontrar el elemento.  
  Es un algoritmo clásico y rápido para listas ordenadas.

---

## ▶️ Ejecución

Compilar y ejecutar con:

```bash
javac GestionEstudiantil.java
java GestionEstudiantil
