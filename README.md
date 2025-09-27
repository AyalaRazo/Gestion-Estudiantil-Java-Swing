# 📚 Student Management / Gestión Estudiantil - Java Swing

---

## English

This project is a **Java desktop application** developed with **Swing**, designed to manage a student registry.  
It features **advanced search functionalities** using **classic binary and interpolation search algorithms** applied to the student list.

### ✨ Main Features

- **Add Students**  
  - Registers name, student ID, grade, and GPA.  
  - Validates required fields, numeric values, and prevents duplicates.  
  - Automatically sorts the list by student ID to optimize searches.

- **Search Students**  
  - 🔎 **By Student ID** → using **interpolation search**.  
  - 🔎 **By Name** → using **binary search**.  

- **Edit Students**  
  - Locate by student ID.  
  - Allows updating name, grade, or GPA with validations.

- **Show All**  
  - Restores the table with the complete student list.

- **Filter Students**  
  - By grade.  
  - By student ID range.

- **Graphical Interface**  
  - Built with `JFrame`, `JTable`, `JPanel`, `JTextField`, `JButton`, and `JComboBox`.  
  - Buttons include icons and tooltips for better user experience.

### 🧑‍💻 Main Classes

- `GestionEstudiantil`  
  Controls the GUI and CRUD operations.  
  Implements **interpolation** and **binary search** algorithms.  
  Stores the student list in an `ArrayList`.

- `Estudiante`  
  Model class representing a student with:  
  - `name` (String)  
  - `studentID` (int)  
  - `grade` (String)  
  - `GPA` (double)

### 📊 Search Algorithms

- **Interpolation Search (`busquedaInterpolada`)**  
  Estimates the position of the target value within a range using an interpolation formula.  
  More efficient than binary search for uniformly distributed numeric lists (like student IDs).

- **Binary Search (`busquedaBinariaNombre`)**  
  Splits the ordered list by name into successive halves until the element is found.  
  A classic and fast algorithm for ordered lists.

### ▶️ How to Run

Compile and execute:

```bash
javac GestionEstudiantil.java
java GestionEstudiantil
```
<a href="#"><img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif"></a>

## Español

Este proyecto es una **aplicación de escritorio en Java** desarrollada con **Swing**, que permite gestionar un registro de estudiantes.  
Incluye **búsquedas avanzadas** con **algoritmos clásicos de búsqueda binaria e interpolada**, aplicados sobre la lista de estudiantes.

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

## 📊 Algoritmos de búsqueda en el proyecto

- **Búsqueda interpolada (`busquedaInterpolada`)**  
  Calcula la posición estimada del valor buscado dentro de un rango usando una fórmula de interpolación.  
  Más eficiente que la búsqueda binaria en listas numéricas distribuidas uniformemente (como las matrículas).

- **Búsqueda binaria (`busquedaBinariaNombre`)**  
  Divide la lista ordenada por nombre en mitades sucesivas hasta encontrar el elemento.  
  Es un algoritmo clásico y rápido para listas ordenadas.

## ▶️ Ejecución

Compilar y ejecutar con:

```bash
javac GestionEstudiantil.java
java GestionEstudiantil
```

## Images
<div align=center>
  <img width="1169" height="876" alt="image" src="https://github.com/user-attachments/assets/14a42253-56c8-48a4-9f37-f2e046f3bfbe" />

  ---

  <img width="1172" height="888" alt="image" src="https://github.com/user-attachments/assets/5cb1bee3-1209-43d3-b6a0-5477adc68ab8" />

  ---

  <img width="1166" height="887" alt="image" src="https://github.com/user-attachments/assets/4045a97a-4562-40c0-b9f9-e4256b50d301" />

  ---

  <img width="1179" height="552" alt="image" src="https://github.com/user-attachments/assets/5e8d7057-00f7-4e07-b42e-a066ca3c971d" />

</div>

