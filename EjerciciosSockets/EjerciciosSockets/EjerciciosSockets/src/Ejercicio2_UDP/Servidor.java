package Ejercicio2_UDP; 

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    
    //Declarar todas las variables que neceistamos
    private static final int PUERTO = 6000; //Puerto de conexión
    private static final int NUM_HILOS = 10; //Número máximo de hilos para manejar las conexiones de los clientes

    public static void main(String[] args) {
        
        //******ESTABLECER Y MANEJAR LA CONEXIÓN CON EL CLIENTE************
        ExecutorService numhilos = Executors.newFixedThreadPool(NUM_HILOS);

        //Crear un socket servidor y vincularlo al puerto
        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) { 
            System.out.println("Servidor TCP esperando conexiones...");

            //Bucle para aceptar conexiones de clientes
            while (true) { 
                //Aceptar la conexión entrante del cliente
                Socket socketCliente = servidorSocket.accept(); 
                numhilos.execute(new ManejarConexionesDelCliente(socketCliente));
            }
            //*******************
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    //*******MÉTODO PARA CONTAR LOS DATOS ALMACENADOS DE UNA CADENA EN UNA LÍNEA****************
    private static int contarDatos(String line, String stringToSearch) {
        
        int contador = 0; 
        //Obtener el índice de los primeros datos almacenados de la cadena
        int i = line.indexOf(stringToSearch); 

        //Mientras existan datos almacenados
        while (i != -1) { 
            contador++; 
            i = line.indexOf(stringToSearch, i + 1); //Buscar los datos almacenados
        }
        return contador;
        
    }
    //********************

    //**************MÉTODO PARA MANEJAR LAS CONEXIONES DEL CLIENTE EN HILOS SEPARADOS --> CONEXIÓN CON EL CLIENTE**********
    static class ManejarConexionesDelCliente implements Runnable {
       
        private Socket socketCliente;

        //*************************
        //Constructor que recibe el socket del cliente
        public ManejarConexionesDelCliente(Socket socketCliente) { 
            //Asignar el socket recibido
            this.socketCliente = socketCliente; 
        }
        //*************************

        //*******MÉTODO PARA MANEJAR LA CONEXIÓN CON EL CLIENTE*************
        @Override
        public void run() { 
            try (ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream()); //Para recibir datos del cliente
                 ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream())) { //Para enviar datos al cliente

                //*********BUCLE PARA MANTENER LA COMUNICACIÓN CON EL CLIENTE
                while (true) { 
                    salida.writeUTF("Introduce la URL:"); 
                    salida.flush(); //Limpiar el flujo de salida
                    String url = entrada.readUTF();
                    salida.writeUTF("Introduce la cadena a buscar:");
                    salida.flush(); //Limpiar el flujo de salida

                    String cadena = entrada.readUTF(); //Leer la cadena enviada por el cliente

                    //----------------OBJETO---------------
                    //*****************
                    //Crear el objeto
                    DatosBusqueda datosBusqueda = new DatosBusqueda(url, cadena); 
                    
                    //*******RECIBIR OBJETO DEL CLIENTE*******
                    salida.writeObject(datosBusqueda); 
                    //Limpiar el flujo de salida
                    salida.flush(); 
                    //**************

                    //Contar los datos almacenados de la cadena en la URL
                    int datos = contarDatosEnURL(url, cadena); 

                    //*******ENVIAR RESPUESTA AL CLIENTE**********
                    salida.writeInt(datos); 
                    salida.flush(); //Limpiar el flujo de salida
                    salida.writeUTF("¿Deseas realizar una nueva operación? (si/no)");
                    salida.flush(); //Limpiar el flujo de salida
                    String respuesta = entrada.readUTF(); //Leer la respuesta del cliente

                    
                    //Si la respuesta del usuario no es "si", salir del bucle
                    if (!respuesta.equalsIgnoreCase("si")) { 
                        break; 
                    }
                    //************************
                }
            } catch (IOException e) { 
                e.printStackTrace(); 
                
            } finally { 
                
                //***********
                //Cerrar socket 
                try { 
                    socketCliente.close();  
                } catch (IOException e) { 
                    e.printStackTrace(); 
                }
                //*************
            }
        }

        //*******MÉTODO PARA CONTAR LOS DATOS ALMACENADOS DE UNA CADENA EN UNA URL**********
        private static int contarDatosEnURL(String urlString, String cadena) {
            int datos = 0; 

            //************************
            try { 
                URL url = new URL(urlString); //Crear un objeto URL con la URL proporcionada
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream())); //Crear un BufferedReader para leer la URL
                String linea; //Variable para almacenar cada línea leída de la URL

                //Leer línea por línea de la URL
                while ((linea = br.readLine()) != null) { 
                    datos += contarDatos(linea, cadena); // Contar los datos almacenados de la cadena en la línea actual
                }
                
                //Cerrar BufferedReader
                br.close();
                
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
            //***********************

            return datos;
        }
        //*************************
    }
    //*****************************
}