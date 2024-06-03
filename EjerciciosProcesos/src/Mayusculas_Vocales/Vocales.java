package Mayusculas_Vocales;

import java.io.IOException;

/**
 *
 * @author laura
 */
public class Vocales {
    //************CONVERTIR A MAYÚSCULAS*************
    public static void main(String[] args) throws IOException {
        //*******PASARLE LOS ARGUMENTOS = número de proceso, vocal y texto************
        String proceso = args[0];
        char vocal = args[1].charAt(0); //Convertir  la vocal a char
        String texto = args[2];
        //********************
        
        //******TEXTO CONVERTIDO EN ARRAY DE CARÁCTERES******
        //Buscar cada carácter y ver si coincide
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == vocal || texto.charAt(i) == Character.toUpperCase(vocal)) {
                contador++;
            }
        }
        //Sacarlo por pantalla
        System.out.println("El proceso " + proceso + " ha encontrado " + contador + " veces la vocal " + vocal);
        //**********************************************
    }
}
