
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


public class Cifrado {

    public static void main(String[] args) throws IOException {
        try {
            
            //Texto a cifrar, en este caso nombre y apellidos
            String textoClaro = "Tu Nombre Apellidos";

            //Generar la clave simétrica a partir del hash SHA3-256 de tu nombre
            byte[] claveHash = AESTools.generarHash(textoClaro);

            //Cifrar el texto
            byte[] textoCifrado = AESTools.cifrar(textoClaro, claveHash);

            //Guardar el texto cifrado en un archivo
            String rutaArchivo = "archivos/nombre_cifrado.txt"; //Ruta del archivo cifrado que nos dicen
            Path path = Paths.get(rutaArchivo); //Obtener el Path del archivo
            Files.write(path, textoCifrado); //Escribir el texto cifrado en el archivo

            //Mostrar hash de la clave y confirmación
            //Mostrar el hash de la clave en hexadecimal
            System.out.println("Clave de cifrado (hash) en hexadecimal: " + AESTools.valorHexadecimal(claveHash)); 
            System.out.println("Texto cifrado correctamente."); 
            
        } catch (NoSuchAlgorithmException e) {
            
            System.out.println("No disponible algoritmo de hash"); 
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
