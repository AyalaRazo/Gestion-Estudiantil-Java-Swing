/*
 */
package Laboratorio.Practica9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionEstudiantil extends JFrame {
    private ArrayList<Estudiante> estudiantes;
    private DefaultTableModel tableModel;
    private JTable tablaEstudiantes;
    
    // Componentes de la interfaz
    private JTextField txtNombre, txtMatricula, txtGrado, txtPromedio;
    private JButton btnAgregar, btnBuscar, btnModificar, btnMostrarTodos, btnFiltrar;
    private JComboBox<String> comboBuscarPor;
    private JTextField txtBusqueda;
    
    public GestionEstudiantil() {
        estudiantes = new ArrayList<>();
        crearInterfaz();
    }
    
    private void crearInterfaz() {
        setTitle("Gestión Estudiantil");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
         // Cargar iconos
        ImageIcon addIcon = cargarIcono("add.png", 20, 20);
        ImageIcon searchIcon = cargarIcono("search.png", 20, 20);
        ImageIcon editIcon = cargarIcono("edit.png", 20, 20);
        ImageIcon listIcon = cargarIcono("list.png", 20, 20);
        ImageIcon filterIcon = cargarIcono("filter.png", 20, 20);
    
        // Crear botones con iconos
        btnAgregar = new JButton("Agregar", addIcon);
        btnBuscar = new JButton("Buscar", searchIcon);
        btnModificar = new JButton("Modificar", editIcon);
        btnMostrarTodos = new JButton("Mostrar Todos", listIcon);
        btnFiltrar = new JButton("Filtrar", filterIcon);

         // Configurar tooltips
        btnAgregar.setToolTipText("Agregar nuevo estudiante");
        btnBuscar.setToolTipText("Buscar estudiante");
        btnModificar.setToolTipText("Modificar estudiante");
        btnMostrarTodos.setToolTipText("Mostrar todos los estudiantes");
        btnFiltrar.setToolTipText("Filtrar a los estudiantes");
        
        // Panel superior para ingreso de datos
        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 5, 5));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelSuperior.add(new JLabel("Nombre completo:"));
        txtNombre = new JTextField();
        panelSuperior.add(txtNombre);
        
        panelSuperior.add(new JLabel("Número de matrícula:"));
        txtMatricula = new JTextField();
        panelSuperior.add(txtMatricula);
        
        panelSuperior.add(new JLabel("Grado escolar:"));
        txtGrado = new JTextField();
        panelSuperior.add(txtGrado);
        
        panelSuperior.add(new JLabel("Promedio académico:"));
        txtPromedio = new JTextField();
        panelSuperior.add(txtPromedio);
        

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });
        panelSuperior.add(btnAgregar);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con tabla de estudiantes
        String[] columnas = {"Matrícula", "Nombre", "Grado", "Promedio"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablaEstudiantes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior para búsquedas y modificaciones
        JPanel panelInferior = new JPanel(new GridLayout(2, 3, 5, 5));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        comboBuscarPor = new JComboBox<>(new String[]{"Matrícula (Interpolada)", "Nombre (Binaria)"});
        panelInferior.add(comboBuscarPor);
        
        txtBusqueda = new JTextField();
        panelInferior.add(txtBusqueda);
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });
        panelInferior.add(btnBuscar);
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstudiante();
            }
        });
        panelInferior.add(btnModificar);
        
        btnMostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodos();
            }
        });
        panelInferior.add(btnMostrarTodos);
        
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarEstudiantes();
            }
        });
        panelInferior.add(btnFiltrar);
        
        add(panelInferior, BorderLayout.SOUTH);
        

    }
    
    // Método auxiliar para cargar y escalar iconos
private ImageIcon cargarIcono(String ruta, int width, int height) {
    try {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(ruta));
        Image img = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    } catch (Exception e) {
        System.err.println("No se pudo cargar el icono: " + ruta);
        return null; // o return new ImageIcon() para un icono vacío
    }
}
    
    private void agregarEstudiante() {
        try {
            String nombre = txtNombre.getText().trim();
            String matriculaStr = txtMatricula.getText().trim();
            String grado = txtGrado.getText().trim();
            String promedioStr = txtPromedio.getText().trim();
            
            // Validar campos vacíos
            if (nombre.isEmpty() || matriculaStr.isEmpty() || grado.isEmpty() || promedioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar matrícula numérica y única
            int matricula;
            try {
                matricula = Integer.parseInt(matriculaStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La matrícula debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (existeMatricula(matricula)) {
                JOptionPane.showMessageDialog(this, "La matrícula ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar promedio numérico
            double promedio;
            try {
                promedio = Double.parseDouble(promedioStr);
                if (promedio < 0 || promedio > 10) {
                    JOptionPane.showMessageDialog(this, "El promedio debe estar entre 0 y 10", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El promedio debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear y agregar estudiante
            Estudiante nuevo = new Estudiante(nombre, matricula, grado, promedio);
            estudiantes.add(nuevo);
            
            // Ordenar la lista por matrícula para búsquedas
            Collections.sort(estudiantes, Comparator.comparingInt(Estudiante::getMatricula));
            
            // Actualizar tabla
            actualizarTabla(estudiantes);
            
            // Limpiar campos
            txtNombre.setText("");
            txtMatricula.setText("");
            txtGrado.setText("");
            txtPromedio.setText("");
            
            JOptionPane.showMessageDialog(this, "Estudiante agregado con éxito");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean existeMatricula(int matricula) {
        for (Estudiante e : estudiantes) {
            if (e.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }
    
    private void buscarEstudiante() {
        try {
            String busqueda = txtBusqueda.getText().trim();
            if (busqueda.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor de búsqueda", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int opcion = comboBuscarPor.getSelectedIndex();
            ArrayList<Estudiante> resultados = new ArrayList<>();
            
            if (opcion == 0) { // Búsqueda por matrícula (interpolada)
                try {
                    int matricula = Integer.parseInt(busqueda);
                    Estudiante encontrado = busquedaInterpolada(matricula);
                    if (encontrado != null) {
                        resultados.add(encontrado);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró estudiante con esa matrícula", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Para buscar por matrícula debe ingresar un número", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else { // Búsqueda por nombre (binaria)
                // Ordenar por nombre para la búsqueda binaria
                Collections.sort(estudiantes, Comparator.comparing(Estudiante::getNombre));
                
                Estudiante encontrado = busquedaBinariaNombre(busqueda);
                if (encontrado != null) {
                    resultados.add(encontrado);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró estudiante con ese nombre", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            
            actualizarTabla(resultados);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Estudiante busquedaInterpolada(int matricula) {
        if (estudiantes.isEmpty()) return null;
        
        int low = 0;
        int high = estudiantes.size() - 1;
        
        while (low <= high && matricula >= estudiantes.get(low).getMatricula() && matricula <= estudiantes.get(high).getMatricula()) {
            if (low == high) {
                if (estudiantes.get(low).getMatricula() == matricula) {
                    return estudiantes.get(low);
                }
                return null;
            }
            
            // Fórmula de interpolación
            int pos = low + (((high - low) / 
                (estudiantes.get(high).getMatricula() - estudiantes.get(low).getMatricula())) * 
                (matricula - estudiantes.get(low).getMatricula()));
            
            if (estudiantes.get(pos).getMatricula() == matricula) {
                return estudiantes.get(pos);
            }
            
            if (estudiantes.get(pos).getMatricula() < matricula) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return null;
    }
    
    private Estudiante busquedaBinariaNombre(String nombre) {
        int low = 0;
        int high = estudiantes.size() - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Estudiante midEst = estudiantes.get(mid);
            
            int comparacion = midEst.getNombre().compareToIgnoreCase(nombre);
            
            if (comparacion == 0) {
                return midEst;
            } else if (comparacion < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
    
    private void modificarEstudiante() {
        try {
            String busqueda = txtBusqueda.getText().trim();
            if (busqueda.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una matrícula para modificar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Solo permitir modificación por matrícula (búsqueda interpolada)
            int matricula;
            try {
                matricula = Integer.parseInt(busqueda);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Para modificar debe ingresar una matrícula numérica", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Estudiante encontrado = busquedaInterpolada(matricula);
            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró estudiante con esa matrícula", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener nuevos datos (los campos vacíos no se modifican)
            String nuevoNombre = txtNombre.getText().trim();
            String nuevoGrado = txtGrado.getText().trim();
            String nuevoPromedioStr = txtPromedio.getText().trim();
            
            if (nuevoNombre.isEmpty() && nuevoGrado.isEmpty() && nuevoPromedioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe modificar al menos un campo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aplicar cambios
            if (!nuevoNombre.isEmpty()) {
                encontrado.setNombre(nuevoNombre);
            }
            
            if (!nuevoGrado.isEmpty()) {
                encontrado.setGrado(nuevoGrado);
            }
            
            if (!nuevoPromedioStr.isEmpty()) {
                try {
                    double nuevoPromedio = Double.parseDouble(nuevoPromedioStr);
                    if (nuevoPromedio < 0 || nuevoPromedio > 10) {
                        JOptionPane.showMessageDialog(this, "El promedio debe estar entre 0 y 10", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    encontrado.setPromedio(nuevoPromedio);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El promedio debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Actualizar tabla
            actualizarTabla(estudiantes);
            
            JOptionPane.showMessageDialog(this, "Estudiante modificado con éxito");
            
            // Limpiar campos
            txtNombre.setText("");
            txtMatricula.setText("");
            txtGrado.setText("");
            txtPromedio.setText("");
            txtBusqueda.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarTodos() {
        actualizarTabla(estudiantes);
    }
    
    private void filtrarEstudiantes() {
        try {
            // Diálogo para obtener filtros
            JPanel panelFiltro = new JPanel(new GridLayout(3, 2, 5, 5));
            
            JTextField txtGradoFiltro = new JTextField();
            JTextField txtMatriculaInicio = new JTextField();
            JTextField txtMatriculaFin = new JTextField();
            
            panelFiltro.add(new JLabel("Grado (dejar vacío para todos):"));
            panelFiltro.add(txtGradoFiltro);
            panelFiltro.add(new JLabel("Matrícula inicial:"));
            panelFiltro.add(txtMatriculaInicio);
            panelFiltro.add(new JLabel("Matrícula final:"));
            panelFiltro.add(txtMatriculaFin);
            
            int resultado = JOptionPane.showConfirmDialog(this, panelFiltro, "Filtrar Estudiantes", JOptionPane.OK_CANCEL_OPTION);
            if (resultado != JOptionPane.OK_OPTION) {
                return;
            }
            
            String gradoFiltro = txtGradoFiltro.getText().trim();
            String matriculaInicioStr = txtMatriculaInicio.getText().trim();
            String matriculaFinStr = txtMatriculaFin.getText().trim();
            
            ArrayList<Estudiante> filtrados = new ArrayList<>();
            
            for (Estudiante e : estudiantes) {
                boolean cumpleGrado = gradoFiltro.isEmpty() || e.getGrado().equalsIgnoreCase(gradoFiltro);
                
                boolean cumpleMatricula = true;
                if (!matriculaInicioStr.isEmpty() || !matriculaFinStr.isEmpty()) {
                    try {
                        int matriculaInicio = matriculaInicioStr.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(matriculaInicioStr);
                        int matriculaFin = matriculaFinStr.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(matriculaFinStr);
                        
                        cumpleMatricula = e.getMatricula() >= matriculaInicio && e.getMatricula() <= matriculaFin;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Las matrículas deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                if (cumpleGrado && cumpleMatricula) {
                    filtrados.add(e);
                }
            }
            
            actualizarTabla(filtrados);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTabla(ArrayList<Estudiante> lista) {
        tableModel.setRowCount(0); // Limpiar tabla
        
        for (Estudiante e : lista) {
            Object[] fila = {
                e.getMatricula(),
                e.getNombre(),
                e.getGrado(),
                e.getPromedio()
            };
            tableModel.addRow(fila);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionEstudiantil().setVisible(true);
            }
        });
    }
}

class Estudiante {
    private String nombre;
    private int matricula;
    private String grado;
    private double promedio;
    
    public Estudiante(String nombre, int matricula, String grado, double promedio) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.grado = grado;
        this.promedio = promedio;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getMatricula() {
        return matricula;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public String getGrado() {
        return grado;
    }
    
    public void setGrado(String grado) {
        this.grado = grado;
    }
    
    public double getPromedio() {
        return promedio;
    }
    
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}