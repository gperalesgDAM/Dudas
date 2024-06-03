package Ejercicio1_UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author laura
 */
public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //************************ESTABLECER CONEXIÓN CON EL SERVIDOR***********************************
        // Crear un socket de datagrama para conectarse al servidor
        DatagramSocket socketCliente = new DatagramSocket();
        // Definimos la dirección y el puerto a donde voy a mandar el datagrama
        InetAddress destino = InetAddress.getLocalHost();
        //***********************************************************

        //***************************MANDAR UN MENSAJE AL SERVIDOR (SALUDO)********************************
        String saludo = "Hola Servidor !!!";
        byte[] mensaje = saludo.getBytes(); // Convertirlo a bytes
        //***********************************************************

        //***********************ENVIAR PAQUETE AL SERVIDOR************************************
        //Construir el datagrama a enviar al servidor
        DatagramPacket paqueteEnvioAlServidor = new DatagramPacket(mensaje, mensaje.length, destino, 6000); // Envío el mensaje, dirección IP y el puerto
        //Enviar el paquete
        socketCliente.send(paqueteEnvioAlServidor);
        System.out.println("Cliente ha enviado: " + saludo);
        //***********************************************************

        //*************PREPARAR EL DATAGRAMA PARA RECIBIR LA RESPUESTA DEL SERVIDOR**********************************
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteRecibirRespuestaDelServidor = new DatagramPacket(buffer, buffer.length);
        //************************

        //***********DATOS SOBRE EL SERVIDOR --> RECIBIR DEL SERVIDOR*************************
        // Permanecer a la espera de recibir datos del servidor
        socketCliente.receive(paqueteRecibirRespuestaDelServidor);
        // Obtener los datos recibidos y la información sobre el servidor
        String respuesta = new String(paqueteRecibirRespuestaDelServidor.getData(), 0, paqueteRecibirRespuestaDelServidor.getLength());
        System.out.println("Servidor dice: " + respuesta);
        //***********************************************************

        //-------------------------------------OBJETO----------------------------------------------------------------------------
        //*****************************RECIBIR EL ARRAY DE BYTES DEL OBJETO DEL SERVIDOR******************************
        byte[] datosRecibidos = new byte[1024];
        DatagramPacket paqueteRecibidoDelServidor = new DatagramPacket(datosRecibidos, datosRecibidos.length);
        socketCliente.receive(paqueteRecibidoDelServidor);
        // Convertimos el stream de bytes en un objeto de la clase general Object
        ByteArrayInputStream entradaObjetos = new ByteArrayInputStream(datosRecibidos);
        // Convertimos el array de bytes en un stream de bytes
        ObjectInputStream entradaObjetosStream = new ObjectInputStream(entradaObjetos);
        //************************************************************

        //***********************MOSTRAR POR PANTALLA COMO SE HA RECIBIDO************************************
        //*******************LEER EL OBJETO DEL SERVIDOR Y CONVERTIRLO A LA CLASE DATOSCLIENTE*********************************
        DatosCliente cliente = (DatosCliente) entradaObjetosStream.readObject();
        System.out.println("Soy el cliente: " + cliente.getNombre() + ", y tengo el número " + cliente.getNumero()); //Datos que tenemos en la clase DatosCliente
        //****************************************************
        //-----------------------------------------------------------------------------------------------------------------

        // Cerrar el socket
        socketCliente.close();
    }
}
