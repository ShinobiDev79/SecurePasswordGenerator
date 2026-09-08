public class Main {
    // Método principal: es el punto de arranque de cualquier programa en Java.
// Cuando ejecutas tu proyecto, Java busca este método para empezar a leer el código.
    public static void main(String[] args){

        // Creamos un nuevo objeto (una instancia) de tu clase GeneradorUI.
        // En este momento se preparan los botones y cajas en la memoria, pero la ventana aún no se ve.
        GeneratorUI ventana = new GeneratorUI();

        // Le indicamos a Java que muestre la ventana en la pantalla del usuario.
        // Sin esta línea, el programa se ejecutaría pero de forma "invisible".
        ventana.setVisible(true);
    }
}
