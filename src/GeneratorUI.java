// Importamos toda la librería Swing de Java.
// El asterisco (*) indica que traemos todas las herramientas gráficas (JFrame, JButton, etc.)
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.BoxLayout;

// Al usar 'extends JFrame', le decimos a Java que nuestra clase GeneradorUI
// "es una" ventana y hereda todos los comportamientos de una ventana de sistema operativo.
public class GeneratorUI extends JFrame {

    // 1. DECLARACIÓN DE VARIABLES (Los "Muebles")
    // Las declaramos aquí arriba (y privadas) para que cualquier parte de esta clase pueda usarlas.
    // Si las declaráramos dentro del constructor, desaparecerían al terminar de construirse la ventana.

    // Cajas de texto para mostrar la contraseña y recibir la longitud
    private JTextField password;
    private JTextField longitud;

    // Casillas de verificación para que el usuario elija qué caracteres usar
    private JCheckBox minusculas;
    private JCheckBox mayusculas;
    private JCheckBox numeros;
    private JCheckBox simbolos;

    private JLabel etiquetaFuerza;
    private JLabel etiquetaPassword;
    private JLabel etiquetaLongitud;

    // El botón que accionará todo el proceso
    private JButton generar;

    // El botón para copiar la contraseña.
    private JButton copiar;

    // 2. EL CONSTRUCTOR
    // Este código se ejecuta automáticamente cuando hacemos el 'new GeneradorUI()' en el Main.
    public GeneratorUI(){

        // --- Configuraciones de la Ventana Principal ---
        setTitle("GeneradorUI");
        setSize(400,500);

        // MUY IMPORTANTE: Le dice al programa que se detenga por completo cuando cerramos la ventana.
        // Si no lo pones, la ventana se cierra pero el programa sigue consumiendo RAM de fondo.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Poniendo 'null' hacemos que la ventana aparezca centrada en la pantalla de tu ordenador.
        setLocationRelativeTo(null);

        // --- Inicialización de los componentes ---
        // El (10) indica el tamaño aproximado de la caja en columnas de texto
        password = new JTextField(10);
        password.setMaximumSize(new Dimension(250, 30)); // Fija el ancho y el alto máximo

        // Añade esta línea para hacerla de solo lectura:
        password.setEditable(false);

        longitud = new JTextField(10);
        longitud.setMaximumSize(new Dimension(250, 30));

        // Creamos las casillas y les ponemos el texto que leerá el usuario
        minusculas = new JCheckBox("Minusculas");
        mayusculas = new JCheckBox("Mayusculas");
        numeros = new JCheckBox("Numeros");
        simbolos = new JCheckBox("Simbolos");

        // Creamos el botón
        generar = new JButton("Generar Password");
        copiar = new JButton("Copiar Password");

        // Creamos una etiqueta de texto simple para evaluar la fuerza.
        etiquetaFuerza = new JLabel("Fuerza: -");
        etiquetaPassword = new JLabel("Password Generado");
        etiquetaLongitud = new JLabel("Longitud Password");

        // --- Creación del Lienzo (JPanel) ---
        // JFrame es la ventana de cristal, JPanel es el corcho donde pinchamos las cosas.
        JPanel panel = new JPanel();

        // 1. Ordena los elementos en un eje vertical (de arriba a abajo)
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 2. Añade un margen interior (arriba, izquierda, abajo, derecha) de 20 píxeles
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Vamos añadiendo (pinchando) los elementos al panel.
        // Por defecto, Java los coloca uno al lado del otro de izquierda a derecha.
        JPanel filaLongitud = new JPanel();
        filaLongitud.setLayout(new FlowLayout(FlowLayout.LEFT));
        filaLongitud.add(etiquetaLongitud);
        filaLongitud.add(longitud);
        panel.add(filaLongitud);

        JPanel filaMinusculas = new JPanel();
        filaMinusculas.setLayout(new FlowLayout(FlowLayout.LEFT));
        filaMinusculas.add(minusculas);
        filaMinusculas.add(mayusculas);
        panel.add(filaMinusculas);

        JPanel filaNumeros = new JPanel();
        filaNumeros.setLayout(new FlowLayout(FlowLayout.LEFT));
        filaNumeros.add(numeros);
        filaNumeros.add(simbolos);
        panel.add(filaNumeros);

        // 1. Creamos un mini-panel para la fila del botón y la etiqueta
        JPanel filaGenerar = new JPanel();

        // 2. Le decimos que ordene sus elementos de izquierda a derecha y los centre
        filaGenerar.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 3. Metemos el botón y la etiqueta dentro de este mini-panel
        filaGenerar.add(generar);
        filaGenerar.add(etiquetaFuerza);

        // 4. Añadimos el mini-panel (que ya contiene ambas cosas en línea) al panel principal
        panel.add(filaGenerar);

        JPanel filaPassword = new JPanel();
        filaPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
        filaPassword.add(etiquetaPassword);
        filaPassword.add(password);
        panel.add(filaPassword);

        JPanel filaCopiar = new JPanel();
        filaCopiar.setLayout(new FlowLayout(FlowLayout.LEFT));
        filaCopiar.add(copiar);
        panel.add(filaCopiar);

        // Colgamos el corcho (panel) ya lleno de cosas dentro de nuestra ventana principal.
        add(panel);

        // --- El Evento del Botón (La acción) ---
        // Le añadimos un "escuchador". El código de dentro solo se ejecutará cuando el usuario haga clic.
        generar.addActionListener(evento ->{

            // Intentamos ejecutar el código normal
            try {
                // 1. Instanciamos el motor criptográfico que creamos en la otra clase.
                SecurePasswordGenerator motor = new SecurePasswordGenerator();

                // 2. Leemos la caja de texto 'longitud'.
                // Como getText() nos devuelve un String (ej: "12"), usamos Integer.parseInt()
                // para traducirlo a un número real con el que podamos trabajar.
                // Si la caja está vacía o tiene letras, Java lanzará un error aquí y saltará directamente al primer 'catch'
                int size = Integer.parseInt(longitud.getText());

                // Leemos el estado de cada casilla. isSelected() devuelve 'true' si está marcada.
                boolean usaMin = minusculas.isSelected();
                boolean usaMay = mayusculas.isSelected();
                boolean usaNum = numeros.isSelected();
                boolean usaSim = simbolos.isSelected();

                // 3. ¡Llamamos al motor!
                // Le pasamos las 5 variables que acabamos de leer y guardamos el String que nos devuelve.
                // Si ninguna casilla está marcada, tu motor lanzará el error de la Fase 1 y saltará al segundo 'catch'
                String claveGenerada = motor.generarPassword(size, usaMin, usaMay, usaNum, usaSim);

                // 4. Escribimos la contraseña generada en la interfaz gráfica para que el usuario la vea.
                password.setText(claveGenerada);

                // 5. Evaluamos la fuerza y la mostramos al usuario.
                String fuerza = motor.evaluarFuerza(claveGenerada);
                etiquetaFuerza.setText("Fuerza: " + fuerza);
            }
            // Capturamos el error de conversión de texto a número
            catch (NumberFormatException excepcion) {

                // Mostramos un popup de error en pantalla
                JOptionPane.showMessageDialog(null,
                        "Por favor, introduce un número válido para la longitud.",
                        "Error de número",
                        JOptionPane.ERROR_MESSAGE);
            }
            // Capturamos el error de las casillas vacías que TÚ creaste en tu clase PasswordGenerator
            catch (IllegalArgumentException excepcion) {

                // Usamos excepcion.getMessage() para mostrar exactamente el texto que escribiste en la Fase 1
                JOptionPane.showMessageDialog(null,
                        excepcion.getMessage(),
                        "Error de opciones",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        copiar.addActionListener( evento ->{

            // 1. Cogemos el texto que haya en la caja de la contraseña
            String textoACopiar = password.getText();

            // 2. Comprobamos que no esté vacía para no copiar la nada
            if (!textoACopiar.isEmpty()) {
                // 3. Estas tres líneas son la "magia" de Java para usar el portapapeles de Windows/Mac/Linux
                StringSelection seleccion = new StringSelection(textoACopiar);
                Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
                portapapeles.setContents(seleccion, null);

                // (Opcional) Podemos cambiar el texto del botón un segundo para que el usuario sepa que ha funcionado
                copiar.setText("¡Copiado!");
            }
        });
    }
}
