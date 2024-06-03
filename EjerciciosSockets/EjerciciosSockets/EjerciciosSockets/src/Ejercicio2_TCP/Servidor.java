package Ejercicio2_TCP;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Servidor {

    //Declarar todas las variables que neceistamos
    private static final int PUERTO = 6000; //Puerto de conexión
    private static final int NUM_HILOS = 10; //Número máximo de hilos para manejar las conexiones de los clientes

    public static void main(String[] args) {

        //******MANEJAR LA CONEXIÓN CON EL CLIENTE************
        ExecutorService numhilos = Executors.newFixedThreadPool(NUM_HILOS);

        //Crear un socket servidor y vincularlo al puerto
        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor TCP esperando conexiones...");

            //Bucle para aceptar conexiones de clientes
            while (true) {
                //Aceptar la conexión entrante del cliente
                Socket socketCliente = servidorSocket.accept();
                numhilos.execute(new ManejadorCliente(socketCliente));
            }
            //*******************

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //*******MÉTODO PARA CONTAR LOS DATOS ALMACENADOS DE UNA CADENA EN UNA LÍNEA****************
    private static int contarDatosAlamenados(String line, String stringToSearch) {

        int contador = 0;
        //Obtener el índice de los primeros datos almacenados de la cadena
        int i = line.indexOf(stringToSearch);

        //Mientras existan datos almacenados
        while (i != -1) {
            contador++;
            i = line.indexOf(stringToSearch, i + 1);
        }
        return contador;
    }
    //********************

    //**************MÉTODO PARA MANEJAR LAS CONEXIONES DEL CLIENTE EN HILOS SEPARADOS --> CONEXIÓN CON EL CLIENTE**********
    static class ManejadorCliente implements Runnable {

        private Socket socketCliente;

        //*************************
        //Constructor que recibe el socket del cliente
        public ManejadorCliente(Socket socketCliente) {
            this.socketCliente = socketCliente;
        }
        //***************

        //*******MÉTODO PARA MANEJAR LA CONEXIÓN CON EL CLIENTE*************
        @Override
        public void run() {
            try (ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream()); //Para recibir datos del cliente
                ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream())) { //Para enviar datos al cliente

                //*********BUCLE PARA MANTENER LA COMUNICACIÓN CON EL CLIENTE*************
                while (true) {
                    salida.writeUTF("Introduce la URL:");
                    salida.flush();
                    String url = entrada.readUTF();
                    salida.writeUTF("Introduce la cadena a buscar:");
                    salida.flush();

                    String cadena = entrada.readUTF(); //Leer la cadena enviada por el cliente

                    //----------------OBJETO---------------
                    
                    //**********RECIBE EL OBJETO DEL CLIENTE*********
                    //Crear objeto DatosBusqueda con la URL y la cadena
//                    DatosBusqueda datosBusqueda = new DatosBusqueda(url, cadena);
//                    salida.writeObject(datosBusqueda);
//                    salida.flush(); //Limpiar el flujo de salida
//                    //*******************

                    ObjectInputStream inputObjeto = new ObjectInputStream(socketCliente.getInputStream());
                DatosBusqueda datosBusqueda = (DatosBusqueda) inputObjeto.readObject();

                    
                    
                    
                    //Contar los datos almacenados de la cadena en la URL
                    int datos = contarDatosAlmacenadosEnURL(url, cadena);

                    //*******ENVÍA EL OBJETO AL CLIENTE*****************
                    // Enviar el resultado al cliente
                    salida.writeInt(datos);
                    salida.flush(); // Limpiar el flujo de salida
                    salida.writeUTF("¿Deseas realizar una nueva operación? (si/no)"); // Preguntar al cliente si desea realizar otra operación
                    salida.flush(); // Limpiar el flujo de salida
                    String respuesta = entrada.readUTF(); // Leer la respuesta del cliente
                    
                    // Salir del bucle si la respuesta del cliente no es "si"
                    if (!respuesta.equalsIgnoreCase("si")) {
                        break;
                    }
                    //***************************************
                }
            } catch (IOException e) {
                e.printStackTrace();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                //***********
                // Cerrar el socket del cliente
                try {
                    socketCliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //****************
                
            }
        }

        //*******MÉTODO PARA CONTAR LOS DATOS ALMACENADOS DE UNA CADENA EN UNA URL**********
        private static int contarDatosAlmacenadosEnURL(String urlString, String cadena) {
            int datos = 0; // Inicializar el contador de los datos almacenados en cero

            //************************
            try {
                URL url = new URL(urlString); //Crear un objeto URL con la URL proporcionada
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); //Crear un BufferedReader para leer la URL
                String inputLine; //Variable para almacenar cada línea leída de la URL

                //Leer línea por línea de la URL
                while ((inputLine = in.readLine()) != null) {
                    datos += contarDatosAlamenados(inputLine, cadena); //Contar los datos almacenados de la cadena en la línea actual
                }

                in.close(); // Cerrar BufferedReader

            } catch (IOException e) {
                e.printStackTrace();
            }

            return datos;
        }
        //************************   
    }
    //*****************************
}
