package Ejercicio1_TCP;

import java.io.*;
import java.net.*;

/**
 *
 * @autor laura
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            //**********************ESTABLECER CONEXIÓN CON EL CLIENTE*********************************
            // Crear un ServerSocket en el puerto especificado
            ServerSocket servidorSocket = new ServerSocket(6000);
            System.out.println("Servidor TCP esperando conexiones...");
            //******************************

            //*****************BUCLE PARA REALIZAR CONTINUAMENTE********************************************
            while (true) {
                
                //************ACEPTAR LA CONEXIÓN DEL CLIENTE*******************
                Socket socketCliente = servidorSocket.accept();
                System.out.println("Cliente conectado");
                //*************************************

                //**************OBTENER LOS STREAMS DE ENTRADA Y SALIDA*************
                ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream());
                //********************************************

                //*************************RECIBIR EL MENSAJE DEL CLIENTE(SALUDO)************************************
                String mensajeRecibido = entrada.readUTF();
                System.out.println("Cliente dice: " + mensajeRecibido.trim());
                //*************************************

                //*************************DATOS DEL CLIENTE --> ENVIAR AL CLIENTE************************************
                // Preparar la respuesta para enviar al cliente (nombre y puerto)
                String nombre = "Cliente" + socketCliente.getPort();
                String respuesta = "!Hola " + nombre + "!";
                salida.writeUTF(respuesta);
                salida.flush();
                System.out.println("Servidor ha enviado: " + respuesta);
                //*************************************

                //----------------------------------------OBJETO-------------------------------------------------------------------------
                //***************CREAR OBJETO PARA ENVIAR AL CLIENTE*******************
                DatosCliente ObjetoClienteEnviar = new DatosCliente(nombre, socketCliente.getPort());
                salida.writeObject(ObjetoClienteEnviar);
                salida.flush();
                System.out.println("Servidor ha enviado un objeto con el número: " + ObjetoClienteEnviar.getNumero());
                //*************************************************************

                // Cerrar el socket del cliente y los streams
                entrada.close();
                salida.close();
                socketCliente.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}