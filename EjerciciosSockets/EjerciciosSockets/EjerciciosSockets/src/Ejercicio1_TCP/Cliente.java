package Ejercicio1_TCP;

import java.io.*;
import java.net.*;

/**
 *
 * @author laura
 */
public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //************************ESTABLECER CONEXIÓN CON EL SERVIDOR***********************************
        // Crear un socket para conectarse al servidor
        Socket socketCliente = new Socket(InetAddress.getLocalHost(), 6000);
        System.out.println("Conectado al servidor");
        //****************************************************

        //****************OBTENER LOS STREAMS DE ENTRADA Y SALIDA***********
        ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream());
        //************************************************

        //***************************MANDAR UN MENSAJE AL SERVIDOR (SALUDO)************
        String saludo = "Hola Servidor !!!";
        salida.writeUTF(saludo); // Enviar el mensaje
        salida.flush();
        System.out.println("Cliente ha enviado: " + saludo);
        //***********************************************************

        //***********DATOS SOBRE EL SERVIDOR --> RECIBIR DEL SERVIDOR*************************
        // Leer la respuesta del servidor
        String respuesta = entrada.readUTF();
        System.out.println("Servidor dice: " + respuesta);
        //***********************************************************

        //-------------------------------OBJETO---------------------------------------------------------------------------------
        //*****************************RECIBIR EL OBJETO DEL SERVIDOR******************************
        DatosCliente cliente = (DatosCliente) entrada.readObject();
        System.out.println("Soy el cliente: " + cliente.getNombre() + ", y tengo el número " + cliente.getNumero()); //Datos que tenemos en la clase DatosCliente
        //****************************************************
        //--------------------------------------------------------------------------------------------------------------

        // Cerrar el socket y los streams
        entrada.close();
        salida.close();
        socketCliente.close();
    }
}