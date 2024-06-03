package Generar_Hash_Fichero_Asimetrica_Ejercicio4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author laura
 */


public class Descifrado {

    public static void main(String[] args) throws IOException {
        try {
            //Generar un par de claves RSA (para este ejemplo, se generan de nuevo; en una aplicación real, se almacenarían y recuperarían)
            KeyPair claveRSA = RSAUtils.generarClaves();

            //Leer el archivo cifrado
            String rutaArchivo = "archivos/nombre_cifrado.txt";
            Path path = Paths.get(rutaArchivo);
            byte[] textoCifrado = Files.readAllBytes(path);

            //Descifrar el texto
            String textoDescifrado = RSAUtils.descifrar(textoCifrado, claveRSA.getPrivate());

            //Mostrar confirmación
            System.out.println("Texto descifrado correctamente.");
            System.out.println("Texto descifrado: " + textoDescifrado);
            System.out.println("Clave privada (en hexadecimal): " + RSAUtils.valorHexadecimal(claveRSA.getPrivate().getEncoded()));

        } catch (NoSuchAlgorithmException e) {
            
            System.out.println("No disponible algoritmo de cifrado RSA");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
