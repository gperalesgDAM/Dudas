package Generar_Hash_Fichero_Simetrica_Ejercicio4;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  

/**
 *
 * @author laura
 */


public class AESTools {

    //Algoritmos que vamos a utilizar
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding"; //De cifrado que se va a utilizar
    private static final String HASH_ALGORITHM = "SHA3-256"; //De hash que se va a utilizar para generar la clave simétrica 

    //MÉTODO PARA CIFRAR EL TEXTO CLARO USANDO LA CLAVE
    public static byte[] cifrar(String textoClaro, byte[] clave) throws Exception {
        SecretKey claveSecreta = generarClaveSecreta(clave); //Generar la clave secreta AES
        Cipher cipher = Cipher.getInstance(ALGORITHM); //Crear una instancia del cifrador con el algoritmo especificado
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta); //Inicializar el cifrador en modo cifrado con la clave secreta
        return cipher.doFinal(textoClaro.getBytes()); //Cifrar el texto claro y devolver el texto cifrado en bytes
    }

    //MÉTODO PARA DESCIFRAR EL TEXTO CIFRADO USANDO LA CLAVE
    public static String descifrar(byte[] textoCifrado, byte[] clave) throws Exception {
        SecretKey claveSecreta = generarClaveSecreta(clave); //Generar la clave secreta AES
        Cipher cipher = Cipher.getInstance(ALGORITHM); //Crear una instancia del cifrador con el algoritmo especificado
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta); //Inicializar el cifrador en modo descifrado con la clave secreta
        byte[] textoDesencriptado = cipher.doFinal(textoCifrado); //Descifrar el texto cifrado y devolver el texto claro en bytes
        return new String(textoDesencriptado); //Convertir los bytes del texto claro a cadena y devolverlo
    }

    //MÉTODO PARA GENERAR UNA CLAVE SECRETA AES A PARTIR DE LA CLAVE EN BYTES
    private static SecretKey generarClaveSecreta(byte[] clave) {
        return new SecretKeySpec(clave, "AES"); //Crear una clave secreta AES a partir de los bytes de la clave
    }

    //MÉTODO PARA GENERAR EL HASH SHA3-256 DE UN TEXTO
    public static byte[] generarHash(String texto) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM); //Crear una instancia de MessageDigest para SHA3-256
        return digest.digest(texto.getBytes()); //Calcular y devolver el hash del texto en bytes
    }

    //MÉOTOD PARA CONVERTIR UN ARRAY DE BYTES A SU REPRESENTACIÓN HEXADECIMAL
    public static String valorHexadecimal(byte[] bytes) {
        StringBuilder result = new StringBuilder(); //Más eficiente
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); //Asegurar que cada byte se represente con dos caracteres hexadecimales
        }
        return result.toString(); //Devolver la cadena hexadecimal
    }
}