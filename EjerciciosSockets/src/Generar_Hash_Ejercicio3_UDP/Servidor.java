package Generar_Hash_Ejercicio3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author laura
 */


public class Servidor {
    public static void main(String[] args) throws IOException {
        
        int puerto = 6001; //Puerto en el que vamos a tener abierto para escuchar las peticiones
        DatagramSocket servidor = null; //Servidor

        try {
            servidor = new DatagramSocket(puerto); //Configurar el servidor
            System.out.println("SERVER escuchando en el puerto " + puerto); //Saber si se ha conectado

            //Si no recibe peticiones que se pare; sino continua
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                //Esperar a que un cliente haga la conexión
                servidor.receive(packet);
                
                //En cuanto le llegue una petición del cliente, generamos un hilo de la clase Gestor para que sea este hilo el que atienda al cliente
                Gestor gestor = new Gestor(servidor, packet); //Crear un hilo y le pasamos el socket y el paquete con el que va a trabajar
                gestor.start(); //Arrancar el hilo
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (servidor != null && !servidor.isClosed()) {
                //Cuando haya alguna excepción, lo cierro
                servidor.close();
            }
        }
    }
}
