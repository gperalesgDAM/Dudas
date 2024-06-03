/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;

/**
 *
 * @author guipe
 */
public class Laura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // ESCRIBIR ARCHIVO BORRANDO LO EXISTENTE Y EMPEZAR DESDE EL PRINCIPIO
        try {
            // Crear un objeto FileWriter con el nombre del archivo
            FileWriter fileWriter = new FileWriter("prueba1.txt"); //SIEMPRE CREARA EL FICHERO NUEVO
            // FileWriter fileWriter = new FileWriter(nombreArchivo, true); //CONTINUARÁ ESCRIBIENDO SOBRE EL FICHERO EXISTENTE SI EXISTE

            // Envolver FileWriter en BufferedWriter para escribir de manera eficiente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir líneas en el archivo
            bufferedWriter.write("Hola, este es un ejemplo de escritura");
            bufferedWriter.newLine(); // Salto de línea
            bufferedWriter.write("Segunda linea de texto.");
            bufferedWriter.newLine(); // Salto de línea para continuar escribiendo en la linea siguiente

            // Cerrar BufferedWriter
            bufferedWriter.close();

            System.out.println("Se ha escrito en el archivo exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
//---------------------------------ESTO--------------------

        // Leer del archivo
        try {
            FileReader fileReader = new FileReader("prueba2.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("\nContenido del archivo:");
            String linea="";
            String resultado="";
            while ((linea = bufferedReader.readLine()) != null) {
                //System.out.println(linea);
                resultado += linea + "\n"; // Concatenar la línea al resultado
            }
            System.out.println(resultado);
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
//---------------------------------------------------

//------------------------------ESTO--------------------------
        try {
            FileReader fr = new FileReader("prueba3.txt");
            BufferedReader br = new BufferedReader(fr);
            System.out.println("\nContenido del archivo:");
            String linea;
            //Leer el archivo línea a línea
            StringBuilder resultado = new StringBuilder(); //Utilizar StringBuilder para construir el resultado
            while ((linea = br.readLine()) != null) {
                resultado.append(linea).append("\n"); //Agregar la línea al resultado
                
            }
            System.out.println(resultado);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
//-----------------------------------------------------------





    }

}
