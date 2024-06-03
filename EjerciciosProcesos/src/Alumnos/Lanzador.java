package Alumnos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Lanzador {

    public static void main(String[] args) throws IOException, InterruptedException {
        UtilidadesFicheros utilidades =new UtilidadesFicheros ();
        String rutaCarpeta = System.getProperty("user.dir") + "\\Archivos"; //Nombre de la carpeta
        if (utilidades.nuevaCarpeta(rutaCarpeta)){
        String rutaficheroEdad = rutaCarpeta + "\\alumnosEdad.txt"; //Ruta con nombre del fichero
        String rutaficheroNombre = rutaCarpeta + "\\alumnosNombre.txt"; //Ruta con nombre del fichero
        File directorio = new File(".\\build\\classes");

        //List<Process> procesosAlumnos = new ArrayList<>();
        Process proceso = null;
        List<Datos_Alumno> ListaAlumnos = new ArrayList<>();
        String FinAlumnos = null;
        String nombre = "*";
        int edad = 0;
        do {
            //INTRODUCCION DE ALUMNOS
            Datos_Alumno datosalumno = new Datos_Alumno();

            nombre = datosalumno.PedirNombre();
            if (!nombre.equals("*")) {
                datosalumno.PedirEdad();
                nombre=datosalumno.getNombre();
                edad=datosalumno.getEdad();
                ListaAlumnos.add(datosalumno);
                ProcessBuilder pb = new ProcessBuilder("java", "Alumnos.Proceso", nombre, String.valueOf(edad),rutaficheroNombre,rutaficheroEdad );
                pb.directory(directorio);
                pb.inheritIO();
               proceso = pb.start();
            }

        } while (nombre.equals("*"));

        //*************************************************
        System.out.println("\nProceso de lectura de datos finalizado.");
        Leer(rutaficheroEdad,rutaficheroNombre);
        }
        
        System.out.println("\nError con el directorio.");
    }


    
        public static void Leer(String ruta1, String ruta2) {
        try {
            FileReader fileReader1 = new FileReader(ruta1);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            FileReader fileReader2 = new FileReader(ruta1);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

            System.out.println("\nContenido del archivo: ");
            String linea;
            int minAge = Integer.MAX_VALUE;
            int maxAge = Integer.MIN_VALUE;
            String nombre_minAge = "";
            String nombre_maxAge = "";
            int indice_minAge = 0;
            int indice_maxAge = 0;
            int i = 1;
            while ((linea = bufferedReader1.readLine()) != null) {
                int age = Integer.parseInt(linea.trim());
                if (age < minAge) {
                    minAge = age;
                    indice_minAge = i;
                }
                if (age > maxAge) {
                    maxAge = age;
                    indice_maxAge = i;
                }
                i++;
                System.out.println(linea);

            }
            bufferedReader1.close();
            i = 1;
            linea = "";
            while ((linea = bufferedReader2.readLine()) != null) {
                if (i == indice_minAge) {
                    nombre_minAge = bufferedReader2.readLine();

                }
                if (i == indice_maxAge) {
                    nombre_maxAge = bufferedReader2.readLine();

                }
                i++;
                System.out.println(linea);

            }
            bufferedReader2.close();
            System.out.println("Se han almacenado " + (i - 1) + " alumnos");
            System.out.println("El alumno " + nombre_maxAge + " es el mayor y tiene una edad de: " + maxAge);
            System.out.println("El alumno " + nombre_minAge + " es el mayor y tiene una edad de: " + minAge);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }
    
}

