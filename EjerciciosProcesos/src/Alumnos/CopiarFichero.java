/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumnos;

/**
 *
 * @author guipe
 */
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CopiarFichero {



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
