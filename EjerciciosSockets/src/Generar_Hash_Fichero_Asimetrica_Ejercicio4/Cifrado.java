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


public class Cifrado {

    public static void main(String[] args) throws IOException {
        try {
            //Generar un par de claves RSA
            KeyPair claveRSA = RSAUtils.generarClaves();

            //Texto a cifrar, en este caso nombre y apellidos
            String textoClaro = "Tu Nombre Apellidos";

            //Cifrar el texto
            byte[] textoCifrado = RSAUtils.cifrar(textoClaro, claveRSA.getPublic());

            //Guardar el texto cifrado en un archivo
            String rutaArchivo = "archivos/nombre_cifrado.txt";
            Path path = Paths.get(rutaArchivo);
            Files.write(path, textoCifrado);

            //Mostrar confirmación
            System.out.println("Texto cifrado correctamente.");
            System.out.println("Clave pública (en hexadecimal): " + RSAUtils.valorHexadecimal(claveRSA.getPublic().getEncoded()));

        } catch (NoSuchAlgorithmException e) {
            
            System.out.println("No disponible algoritmo de cifrado RSA");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
