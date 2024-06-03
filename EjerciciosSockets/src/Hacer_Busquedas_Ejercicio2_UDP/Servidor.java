package Hacer_Busquedas_Ejercicio2_UDP;

import java.io.*;
import java.net.*;


/**
 *
 * @author laura
 */

public class Servidor {
    public static void main(String[] args) {
        
        //Atributos
        final int puerto = 9876; //Puerto que nos han dicho

        try (DatagramSocket socketServidor = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP iniciado en el puerto " + puerto);

            while (true) {
                
                //Recibir datos del cliente
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socketServidor.receive(paqueteRecibido);

                String datosRecibidos = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                //Extraer URL y cadena de búsqueda de los datos recibidos
                String[] partes = datosRecibidos.split(",");
                String url = partes[0];
                String cadenaBusqueda = partes[1];

                //Realizar búsqueda (simulada)
                int resultado = buscarCoincidencias(url, cadenaBusqueda);

                //Enviar resultado al cliente
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                String resultadoStr = String.valueOf(resultado);
                byte[] bufferRespuesta = resultadoStr.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
                socketServidor.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int buscarCoincidencias(String url, String cadenaBusqueda) {       
        return 2; //Devolvemos un resultado ficticio
    }
}
