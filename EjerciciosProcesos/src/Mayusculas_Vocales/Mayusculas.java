package Mayusculas_Vocales;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Mayusculas {
    //************CONVERTIR A MAYÚSCULAS*************
    public static void main(String[] args) throws IOException {
        
        //*******PASARLE LOS ARGUMENTOS = texto y la ruta************
        String mayusculas = args[0].toUpperCase(); //Convertirlo a mayúsculas
        String ruta = args[1];
        //********************
        
        //INICIALIZAR PARA ESCRIBIR Y LEER******
        FileWriter fw = null;
        BufferedWriter bw = null;
        //*****************
        
        //***************ESCRIBIR EN UN FICHERO***********
        try {
            //Escribir en un fichero esa frase
            fw = new FileWriter(ruta);
            bw = new BufferedWriter(fw);
            fw.write(mayusculas);
            //Cerrar
            bw.close();
            fw.close();
            System.out.println("El texto se ha convertido a may�sculas correctamente");
            //*************
            
        } catch (IOException ex) {
            Logger.getLogger(Mayusculas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //****************************
    } 
    //**********************************
}
