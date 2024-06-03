package Generar_Hash_Ejercicio3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */

//En la comunicación basada en UDP, no utilizamos BufferedReader ni PrintWriter porque estos están diseñados 
//para trabajar con flujos de datos (streams) que son típicos de la comunicación basada en TCP, que es orientada 
//a la conexión y asegura la entrega de los datos en el orden correcto.

//UDP, por otro lado, es un protocolo sin conexión y basado en mensajes. 
//Utiliza DatagramPacket para enviar y recibir datos como bloques de bytes

public class Gestor extends Thread {
    
    //Atributos
    private DatagramSocket socket;
    private DatagramPacket packet;
    
    //Constructor
    public Gestor(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }
    
    //Lo que va hacer este hilo
    @Override
    public void run() {
        try {
            InetAddress address = packet.getAddress();
            //PUERTO
            int port = packet.getPort(); //getPort = obtener el puerto del socket
            String puertoCliente = String.valueOf(port);
            System.out.println("Conexión aceptada con origen el puerto: " + puertoCliente);
            
            //CADENA
            //Empezar comunicación con el cliente
            //Solicitar la cadena de texto --> ENVIAR
            String mensaje = "Introduce la cadena para obtener su hash: ";
            byte[] bufferEnvio = mensaje.getBytes();
            DatagramPacket packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, address, port);
            socket.send(packetEnvio);
            
            //Recibir la cadena del cliente --> RECIBIR
            byte[] buffer = new byte[1024];
            DatagramPacket packetRecibo = new DatagramPacket(buffer, buffer.length);
            socket.receive(packetRecibo);
            String cadena = new String(packetRecibo.getData(), 0, packetRecibo.getLength());
            
            
            //Llamar a las funciones que calculan el hash y pasarle los parámetros
            String cadenaHash = calcularHashString2(puertoCliente, cadena);
            mensaje = "El hash obtenido utilizando como salt tu puerto es: " + cadenaHash;
            bufferEnvio = mensaje.getBytes();
            packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, address, port);
            socket.send(packetEnvio);
            
                        
            //Cerrar todos 
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Generar_Hash_Ejercicio3_TCP.Gestor.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    //MÉTODO PARA CALCULAR EL HASH
    public static String valorHexadecimal2(byte[] bytes) {
        StringBuilder result = new StringBuilder();  //Más eficiente
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); //Asegurar que cada byte se presente con dos caracteres hexadecimales
        }
        return result.toString();
    }
    
    private static String calcularHashString2(String puerto, String cadena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] puertoBytes = (puerto + cadena).getBytes();
            md.update(puertoBytes);
            byte[] dig = md.digest();
            return valorHexadecimal2(dig);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular el hash de la contraseña", e);
        }
    }
}