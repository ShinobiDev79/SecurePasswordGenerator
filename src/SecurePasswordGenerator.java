import java.security.SecureRandom;

// Importamos la clase SecureRandom de la librería de seguridad de Java.
// Es vital para generar números aleatorios imposibles de predecir (criptográficamente seguros).
import java.security.SecureRandom;

public class SecurePasswordGenerator {

    // DEFINICIÓN DE DICCIONARIOS (Constantes)
    // Usamos 'private' para que no se puedan modificar desde fuera de esta clase.
    // Usamos 'static final' porque estos textos nunca van a cambiar durante la ejecución del programa,
    // lo que ahorra memoria. Las constantes se escriben en MAYÚSCULAS por convención.
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "@#$%&*";

    // Declaramos nuestra variable para generar aleatoriedad.
    private SecureRandom random;

    // CONSTRUCTOR: Se ejecuta automáticamente cada vez que hacemos un 'new PasswordGenerator()'.
    public SecurePasswordGenerator() {
        // Inicializamos nuestro motor seguro. Aquí es donde lee el "ruido" del sistema operativo.
        this.random = new SecureRandom();
    }

    // MÉTODO PRINCIPAL: Construye la contraseña.
    // Recibe la longitud deseada y 4 booleanos (true/false) que actúan como "interruptores".
    public String generarPassword(int length, boolean usarMinuscula, boolean usarMayuscula, boolean usarNumeros, boolean usarSimbolos){

        // StringBuilder es como una caja eficiente para ir uniendo fragmentos de texto.
        // Aquí meteremos todos los caracteres que el usuario haya permitido usar.
        StringBuilder caracteresPermitidos = new StringBuilder();

        // Si el usuario marcó la casilla (true), añadimos ese diccionario a nuestra "caja" de permitidos.
        if(usarMinuscula) caracteresPermitidos.append(MINUSCULAS);
        if(usarMayuscula) caracteresPermitidos.append(MAYUSCULAS);
        if(usarNumeros) caracteresPermitidos.append(NUMEROS);
        if(usarSimbolos) caracteresPermitidos.append(SIMBOLOS);

        // CONTROL DE SEGURIDAD: Previene un error grave.
        // Si el usuario desmarcó todas las casillas, la longitud sería 0.
        // Si no lanzamos este error, el programa se colgaría al intentar dividir por 0 más adelante.
        if(caracteresPermitidos.length() == 0){
            throw new IllegalArgumentException("Error: Debes de seleccionar al menos un tipo de caracter.");
        }

        // Preparamos otra caja para ir construyendo la contraseña final, letra a letra.
        StringBuilder passwordFinal = new StringBuilder();
        // Este bucle da tantas vueltas como caracteres de longitud haya pedido el usuario.
        for(int i=0; i < length; i++){
            // Genera un número aleatorio entre 0 y el total de caracteres que hemos permitido.
            // Esto actúa como el número premiado de un sorteo.
            int indexRandom = random.nextInt(caracteresPermitidos.length());

            // Busca qué letra exacta está en esa posición (indexRandom) dentro de nuestros permitidos,
            // y la añade (append) a nuestra contraseña final.
            passwordFinal.append(caracteresPermitidos.charAt(indexRandom));
        }

        // Convertimos nuestra "caja" de texto de vuelta a un String normal (texto) y lo devolvemos.
        return passwordFinal.toString();
    }

    //Método que evalua la fuerza de la contraseña.
    //Recibe un texto que sería el password a evaluar.
    public String evaluarFuerza (String password){
        //Variable en formato cadena devolver en nivel evaluado.
        String evalua;
        int puntos = 0;

        // 1. Evaluar longitud
        if (password.length() >= 8) puntos++;
        if (password.length() >= 12) puntos++;
        if (password.matches(".*[a-z].*")) puntos++;
        if (password.matches(".*[A-Z].*")) puntos++;
        if (password.matches(".*[0-9].*")) puntos++;
        if (password.matches(".*[@#$%&*].*")) puntos++;

        // 2. Serie de if, else if y else para evaluar la fuerza de la contraseña.
        if (puntos <=2){
            evalua = "DÉBIL";
        }else if (puntos <=4){
            evalua = "MEDIA";
        }else{
            evalua = "FUERTE";
        }

        // 3. Devuelve el valor en una variable de cadena de texto.
        return evalua;
    }
}