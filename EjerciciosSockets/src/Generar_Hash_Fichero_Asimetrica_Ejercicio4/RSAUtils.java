package Generar_Hash_Fichero_Asimetrica_Ejercicio4;


import java.security.*;
import javax.crypto.Cipher;

/**
 *
 * @author laura
 */

//Para las operaciones de cifrado y descifrado asimétrico


public class RSAUtils {

    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048; // Tamaño de la clave RSA

    //MÉTODO PARA GENERAR UN PAR DE CLAVES RSA
    public static KeyPair generarClaves() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(KEY_SIZE);
        return keyGen.generateKeyPair();
    }

    //MÉTODO PARA CIFRAR TEXTO USANDO LA CLAVE PÚBLICA
    public static byte[] cifrar(String textoClaro, PublicKey clavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
        return cipher.doFinal(textoClaro.getBytes());
    }

    //MÉTODO PARA DESCIFRAR TEXTO USANDO LA CLAVE PRIVADA
    public static String descifrar(byte[] textoCifrado, PrivateKey clavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
        byte[] textoDesencriptado = cipher.doFinal(textoCifrado);
        return new String(textoDesencriptado);
    }

    //MÉTODO PARA CONVERTIR UN ARRAY DE BYTES A SU REPRESENTACIÓN HEXADECIMAL
    public static String valorHexadecimal(byte[] bytes) {
        StringBuilder result = new StringBuilder(); //// Crear un StringBuilder para construir la cadena hexadecimal  
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); //Asegurar que cada byte se represente con dos caracteres hexadecimales
        }
        return result.toString();
    }
}
