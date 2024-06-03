package Generar_Hash_Fichero_Simetrica_Ejercicio4;


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

            Path path = Paths.get(rutaArchivo); //Obtener el Path del archivo
            byte[] bytes = Files.readAllBytes(path); //Leer todos los bytes del archivo
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //Crear una instancia de MessageDigest para SHA-256
            md.update(bytes); //Actualizar el MessageDigest con los bytes del archivo
            byte[] hash = md.digest(); //Calcular el hash del archivo

            System.out.printf("Hash del archivo: [%s]\n", valorHexadecimal2(hash)); //Mostrar el hash del archivo en hexadecimal
            System.out.println(""); //Línea en blanco para separación
            
        } catch (NoSuchAlgorithmException e) { 
            
            System.out.println("No disponible algoritmo de hash"); 
            
        }
    }

    //MÉTODO QUE TOMA UN ARRAY DE BYTES COMO ENTRADA Y DEVUELVE UNA REPRESENTACIÓN HEXADECIMAL DE ESOS BYTES EN FORMA DE CADENA DE CARÁCTERES
    static String valorHexadecimal2(byte[] bytes) {
        StringBuilder result = new StringBuilder(); //Crear un StringBuilder para construir la cadena hexadecimal
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); //Asegurar que cada byte se represente con dos caracteres hexadecimales
        }
        return result.toString(); 
    }
}