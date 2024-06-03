package Mayusculas_Vocales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Lanzador {

    public static void main(String[] args) throws IOException, InterruptedException {

        //*****************INGRESAR EL TEXTO A ANALIZAR********************
        Scanner scanner = new Scanner(System.in);
        File directorio = new File(".\\build\\classes");
        System.out.print("Ingrese el texto a analizar: ");
        String texto = scanner.nextLine();
        scanner.close();
        //***************************

        //**********CONTROLAR QUE TODOS LOS PROCESO HAN FINALIZADO --> EN ESTE CASO ES EL DE LA CLASE VOCALES*************
        //Crear un Array de procesos = llamarlo cuando haces el start() y en el wait()
        Process[] procesosVocales = new Process[5];
        //****************************************

        //**************CREAR CINCO PROCESOS***************
        for (int numProceso = 1; numProceso <= 5; numProceso++) {
            try {
                //Me creo una variable y le voy pasando los valores
                String vocal = " ";

                switch (numProceso) {
                    case 1:
                        vocal = "a";
                        break;
                    case 2:
                        vocal = "e";
                        break;
                    case 3:
                        vocal = "i";
                        break;
                    case 4:
                        vocal = "o";
                        break;
                    case 5:
                        vocal = "u";
                }
                //************************************

                //****************PASAR LOS ARGUMENTOS AL PROCESO HIJO*******************
                //Ubicarnos en el directorio y con el ProcessBuilder pasarle los argumentos --> número del proceso, vocal, texto
                ProcessBuilder pb = new ProcessBuilder("java", "Mayusculas_Vocales.Vocales", String.valueOf(numProceso), vocal, texto);
                //Pasarle la dirección del directorio
                pb.directory(directorio);
                //Habilitar la entrada y salida para comunicar el padre con el hijo
                pb.inheritIO();
                //Comenzar el proceso hijo
                procesosVocales[numProceso] = pb.start();
                //****************************

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //*************ESPERAR A QUE TODOS LOS PROCESOS SE COMPLETEN************
        for (Process proceso : procesosVocales) {
            try {
                proceso.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //*************************************************

        //************CREAR UNA NUEVA CARPETA Y EN LA UBICACIÓN QUE ME DICEN --> EN ESTE CASO ES EL DE CLASE MAYÚSCULAS*************
        //user.dir = ubicación que estoy ahora mismo
        String rutaCarpeta = System.getProperty("user.dir") + "//Archivos"; //Nombre de la carpeta
        String ruta = rutaCarpeta + "\\mayusculas.txt"; //Ruta con nombre del fichero
        //**************************

        //************CREAR NUEVA CARPETA Y PASARLE LOS ARGUMENTOS --> EN ESTE CASO ES EL DE CLASE MAYÚSCULAS************
        if (nuevaCarpeta(rutaCarpeta)) {
            //Ubicarnos en el directorio y con el ProcessBuilder pasarle los argumentos --> texto, ruta del fichero
            ProcessBuilder pb = new ProcessBuilder("java", "Mayusculas_Vocales.Mayusculas", texto, ruta);
            pb.directory(directorio);
            pb.inheritIO();
            Process procesoMayusculas;
            try {
                procesoMayusculas = pb.start();
                procesoMayusculas.waitFor();
            } catch (IOException ex) {
                Logger.getLogger(Mayusculas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Lanzador lanzador = new Lanzador();
            String textoMay = lanzador.getResultadoFichero(ruta);
            System.out.println("El texto escrito en el archivo es: " + textoMay);
        } else {
            System.out.println("Existe algún problema con la ruta de la carpeta");
        }

    }

    //***************MÉTODO TODO PARA CREAR NUEVAS CARPETAS Y VER SI EXISTEN O NO. SI NO HAY CREARLA*************
    public static Boolean nuevaCarpeta(String ruta) {
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
            System.out.println("La arpeta " + carpeta.getName() + " ya existe");
            return true;
        }
    }
    //*******************

    //*******MÉTODO PARA SACAR EL RESULTADO FINAL**************
    public String getResultadoFichero(String ruta) {
        String linea = "";
        BufferedReader br = null; //Declarar BufferedReader fuera del try para que sea accesible en el bloque finally

        try {
            FileReader fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            //Leer el archivo línea a línea
            StringBuilder resultado = new StringBuilder(); //Utilizar StringBuilder para construir el resultado
            while ((linea = br.readLine()) != null) {
                resultado.append(linea).append("\n"); //Agregar la línea al resultado
            }
            return resultado.toString(); //Devolver el resultado al finalizar la lectura del archivo

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex); //Cambiar el nombre de la clase aquí
        } catch (IOException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ""; //En caso de error, devolver una cadena vacía
    }
    //**********************
}

//----------------------------------------------
//List<Process> procesosAscendentes = new ArrayList<>();
 //for (int numProceso = 0; numProceso < Integer.MAX_VALUE; numProceso++) {

    //    }