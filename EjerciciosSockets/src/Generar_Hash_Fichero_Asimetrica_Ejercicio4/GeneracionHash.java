package Generar_Hash_Fichero_Asimetrica_Ejercicio4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author laura
 */


public class GeneracionHash {

    public static void main(String[] args) throws IOException {
        try {
            //Ruta del archivo para generar el hash que nos dicen
            String rutaArchivo = "archivos/prueba.txt";

            //Obtener el Path del archivo
            Path path = Paths.get(rutaArchivo);

            //Leer todos los bytes del archivo
            byte[] bytes = Files.readAllBytes(path);

            //Crear una instancia de MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Actualizar el MessageDigest con los bytes del archivo
            md.update(bytes);

            //Calcular el hash del archivo
            byte[] hash = md.digest();

            //Mostrar el hash del archivo en hexadecimal
            System.out.printf("Hash del archivo: [%s]\n", valorHexadecimal2(hash));
            System.out.println(""); //Línea en blanco para separación

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No disponible algoritmo de hash");
        }
    }

    //MÉTODO PARA VONVERTIR UN ARRAY DE BYTES A SU REPRESENTACIÓN HEXADECIMAL
    static String valorHexadecimal2(byte[] bytes) {
        StringBuilder result = new StringBuilder(); //Más eficiente
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
