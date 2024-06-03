package Hacer_Busquedas_Ejercicio2_UDP;

import java.io.*;
import java.net.*;

/**
 *
 * @author laura
 */


public class Cliente {
    public static void main(String[] args) {
        
        //Atributos
        final String servidorHost = "localhost";
        final int servidorPuerto = 9876; //Puerto que nos han dicho

        try (DatagramSocket socketCliente = new DatagramSocket()) {
            BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                
                //Leer URL y cadena de búsqueda desde la entrada estándar
                System.out.println("Introduzca la URL deseada:");
                String url = entradaUsuario.readLine();

                System.out.println("Introduzca la cadena a buscar:");
                String cadenaBusqueda = entradaUsuario.readLine();

                //Enviar datos al servidor --> ENVIAR
                String datosEnviar = url + "," + cadenaBusqueda;
                byte[] bufferEnviar = datosEnviar.getBytes();
                InetAddress direccionServidor = InetAddress.getByName(servidorHost);
                DatagramPacket paqueteEnviar = new DatagramPacket(bufferEnviar, bufferEnviar.length, direccionServidor, servidorPuerto);
                socketCliente.send(paqueteEnviar);

                //Recibir resultado del servidor --> RECIBIR
                byte[] bufferRecibir = new byte[1024];
                DatagramPacket paqueteRecibir = new DatagramPacket(bufferRecibir, bufferRecibir.length);
                socketCliente.receive(paqueteRecibir);

                String resultadoStr = new String(paqueteRecibir.getData(), 0, paqueteRecibir.getLength());
                System.out.println("Número de ocurrencias encontradas: " + resultadoStr);

                //Preguntar si desea realizar otra búsqueda
                System.out.println("¿Desea realizar otra búsqueda? (si/no)");
                String respuesta = entradaUsuario.readLine();
                if (respuesta.equalsIgnoreCase("no")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}