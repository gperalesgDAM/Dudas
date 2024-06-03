/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumnos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author guipe
 */
public class UtilidadesFicheros {

    public ArrayList getLineasFichero(String nombreFichero) throws IOException {
        ArrayList<String> lineas = new ArrayList<String>();
        BufferedReader bfr = getBufferedReader(nombreFichero);
        String linea = bfr.readLine();
        while (linea != null) {
            lineas.add(linea);
            linea = bfr.readLine();
        }
        return lineas;
    }

    public int getResultadoFichero(String rutaCompleta) {
        int resultado = 0;
        try {
            FileInputStream fichero = new FileInputStream(rutaCompleta);
            InputStreamReader fir = new InputStreamReader(fichero);
            BufferedReader br = new BufferedReader(fir);
            String linea = br.readLine();
            // Convertir la línea a entero
            resultado = Integer.parseInt(linea);

            return resultado;
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir " + rutaCompleta);
        } catch (IOException e) {
            System.out.println("No hay nada en " + rutaCompleta);
        }
        return resultado;
    }

    public BufferedReader getBufferedReader(String nombreFichero) throws FileNotFoundException {
        FileReader lector = new FileReader(nombreFichero);
        BufferedReader bufferedReader = new BufferedReader(lector);
        return bufferedReader;

    }

    public PrintWriter getPrintWriter(String nombreFichero) throws IOException {
        PrintWriter printWriter;
        FileWriter fileWriter;
        fileWriter = new FileWriter(nombreFichero);
        printWriter = new PrintWriter(fileWriter);
        return printWriter;
    }

    //***************MÉTODO TODO PARA CREAR NUEVAS CARPETAS Y VER SI EXISTEN O NO. SI NO HAY CREARLA*************
    public Boolean nuevaCarpeta(String ruta) {
        File carpeta = new File(ruta);

        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("La carpeta " + carpeta.getName() + " se ha creado correctamente");
                return true;
            } else {
                System.out.println("La carpeta " + carpeta.getName() + " no se ha podido crear");
                return false;
            }
        } else {
            System.out.println("La carpeta " + carpeta.getName() + " ya existe");
            return true;
        }
    }

}
