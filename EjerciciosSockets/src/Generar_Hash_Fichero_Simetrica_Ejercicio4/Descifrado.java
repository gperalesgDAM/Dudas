package Generar_Hash_Fichero_Simetrica_Ejercicio4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author laura
 */


public class Descifrado {

    public static void main(String[] args) throws IOException {
        try {
            
            // Leer el archivo cifrado
            //Ruta del archivo cifrado que nos han dicho
            String rutaArchivo = "archivos/nombre_cifrado.txt"; 
            Path path = Paths.get(rutaArchivo); //Obtener el Path del archivo
            byte[] textoCifrado = Files.readAllBytes(path); //Leer el contenido del archivo cifrado

            //Generar la clave simétrica a partir del hash SHA3-256 de tu nombre
            String textoClaro = "Tu Nombre Apellidos"; //Texto claro usado para generar el hash
            byte[] claveHash = AESTools.generarHash(textoClaro); //Generar el hash

            //Descifrar el texto
            String textoDescifrado = AESTools.descifrar(textoCifrado, claveHash); 

            //Mostrar hash de la clave y confirmación
            System.out.println("Clave de descifrado (hash) en hexadecimal: " + AESTools.valorHexadecimal(claveHash)); //Mostrar el hash de la clave en hexadecimal
            System.out.println("Texto descifrado correctamente."); 
            System.out.println("Texto descifrado: " + textoDescifrado); //Mostrar el texto descifrado
            
        } catch (NoSuchAlgorithmException e) {
            
            System.out.println("No disponible algoritmo de hash"); 
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
