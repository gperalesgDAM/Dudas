package Ejercicio2_UDP; 

import java.io.*; 
import java.net.*; 

public class Cliente { 

    //Declarar todas las variables que neceistamos
    private static final String HOST = "localhost"; //Dirección del servidor
    private static final int PUERTO = 6000; //Puerto de conexión
    private static final int BUFFER_SIZE = 1024; //Tamaño del búfer para la lectura de datos

    public static void main(String[] args) {

        //************************ESTABLECER CONEXIÓN CON EL SERVIDOR***********************************
        try (Socket socket = new Socket(HOST, PUERTO); 
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream()); //Para enviar datos al servidor
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); //Para recibir datos del servidor
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) { //Para leer entrada del usuario

            //**********BUCLE PARA MENTENER LA COMUNICACIÓN CON EL SERVIDOR***************
            while (true) { 
                
                //*******PEDIR LOS DATOS***********
                System.out.println("Introduce la URL deseada:"); 
                String url = teclado.readLine(); 
                System.out.println("Introduce la cadena a buscar:"); 
                String cadena = teclado.readLine(); 
                //***************
                
                //--------------OBJETO --> URL Y CADENA----------------------------
                //************************
                //Crear el objeto
                DatosBusqueda datosBusqueda = new DatosBusqueda(url, cadena); 
                
                //*********ENVIAR AL SERVIDOR********
                salida.writeObject(datosBusqueda);
                //Limpiar flujo de salida
                salida.flush();
                //*************************
               

                //********RECIBIR DEL SERVIDOR**************
                //Leer la cantidad de datos almacenados de la cadena recibida del servidor
                int datosalmacenados = entrada.readInt(); 
                System.out.println("La cadena aparece " + datosalmacenados + " veces en la URL proporcionada."); 
                System.out.println("¿Deseas realizar una nueva operación? (si/no)"); 
                //Leer la respuesta del usuario
                String respuesta = teclado.readLine(); 
                //************
                //------------------------------------
                
                //***********ENVIAR RESPUESTA AL SERVIDOR************
                salida.writeUTF(respuesta); 
                //Limpiar el flujo de salida
                salida.flush(); 
                
                //Si la respuesta del usuario no es "si", salir del bucle
                if (!respuesta.equalsIgnoreCase("si")) { 
                    break;
                }
                //**************************
            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}