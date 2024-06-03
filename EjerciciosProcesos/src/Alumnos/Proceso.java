/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumnos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author guipe
 */
public class Proceso {

    public static void main(String[] args) {

        String nombre = args[0];
        String edad = args[1];
        String ficheroNombre = args[2];
        String ficheroEdad = args[3];
        Escribir(nombre,ficheroNombre);
        Escribir(edad,ficheroEdad);
    }

    public static void Escribir(String texto, String ruta) {
        try {
            // Crear un objeto FileWriter con el nombre del archivo
            //FileWriter fileWriter = new FileWriter(nombreArchivo); //SIEMPRE CREARA EL FICHERO NUEVO
            FileWriter fileWriter = new FileWriter(ruta, true); //CONTINUARÁ ESCRIBIENDO SOBRE EL FICHERO EXISTENTE SI EXISTE

            // Envolver FileWriter en BufferedWriter para escribir de manera eficiente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir líneas en el archivo
            bufferedWriter.write(texto);
            bufferedWriter.newLine(); // Salto de línea
            // Cerrar BufferedWriter
            bufferedWriter.close();

            System.out.println("Se ha escrito en el archivo exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }

}
