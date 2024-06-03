
package Generar_Hash_Ejercicio3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author laura
 */
public class Cliente {
    public static void main(String[] args) throws IOException {
        //Cargar los parámetros que vamos a neceistar
        DatagramSocket socket = null;
        InetAddress host = InetAddress.getByName("localhost");
        int puerto = 6001;

        //Crear el socket UDP
        socket = new DatagramSocket();
        
        //Scanner porque lo vamos a insertar
        Scanner scanner = new Scanner(System.in);
        
        //Recibir el mensaje del servidor --> RECIBIR
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        System.out.println("Servidor dice: " + new String(packet.getData(), 0, packet.getLength()));
        
        // Enviar mensaje al servidor en este caso una cadena --> ENVIAR
        String linea = scanner.nextLine();
        byte[] bufferEnvio = linea.getBytes();
        DatagramPacket packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, host, puerto);
        socket.send(packetEnvio);
        
        //Recibir la respuesta del servidor --> RECIBIR
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        System.out.println("Servidor dice: " + new String(packet.getData(), 0, packet.getLength()));
        
        //Cerrar todo
        scanner.close();
        socket.close();
    }
}