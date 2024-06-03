package Ejercicio1_UDP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author laura
 */
public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            //**********************ESTABLECER CONEXIÓN CON EL CLIENTE*********************************
            // Crear un socket de datagrama en el puerto que nos dice
            DatagramSocket socketServidor = new DatagramSocket(6000);
            socketServidor.setSoTimeout(6000);
            //*************************************************************

            //*****************BUCLE PARA REALIZAR CONTINUAMENTE********************************************
            while (true) {
                System.out.println("Servidor UDP esperando mensajes..."); //Mensaje que sirve de ayuda

                //*************************CREAR UNA DIRECCIÓN DE MEMORIA DONDE VOY A ALMACENAR TODO************************************          
                byte[] bufferEntrada = new byte[1024];
                //*********************************************

                //************CONSTRUIR EL DATAGRAMA A ENVIAR AL CLIENTE********************
                DatagramPacket paqueteRecibidoDelCliente = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                //*************************************************************

                //*************************************************************
                // Estamos preparados para almacenar datos en ese buffer
                try {

                    //*************************RECIBIR EL MENSAJE DEL CLIENTE (SALUDO)************************************
                    // Permanecer a la espera los datos del cliente
                    socketServidor.receive(paqueteRecibidoDelCliente);
                    // Obtener los datos recibidos y la información sobre el cliente 
                    String mensajeRecibido = new String(paqueteRecibidoDelCliente.getData(), 0, paqueteRecibidoDelCliente.getLength()); // Lo convierto a String
                    // Visualizar información
                    System.out.println("Cliente dice: " + mensajeRecibido.trim());
                    //*************************************************************

                    //****************************RECIBIR PAQUETE DEL CLIENTE*********************************
                    // Sacar del paquete que me ha enviado el cliente la información que necesito para darle la respuesta (el puerto y la dirección)
                    int puertoCliente = paqueteRecibidoDelCliente.getPort();
                    InetAddress direccionCliente = paqueteRecibidoDelCliente.getAddress();
                    //*************************************************************

                    //*************************DATOS DEL CLIENTE --> ENVIAR AL CLIENTE************************************
                    // Preparar la respuesta para enviar al cliente (nombre y puerto)
                    String nombre = "Cliente" + puertoCliente;
                    String respuesta = "!Hola " + nombre + "!";
                    byte[] bufferSalida = respuesta.getBytes();
                    // Crear un DatagramPacket con la respuesta y la información del cliente
                    DatagramPacket paqueteEnviarRespuestaAlCliente = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                    // Enviar la respuesta al cliente con el método send
                    socketServidor.send(paqueteEnviarRespuestaAlCliente);
                    System.out.println("Servidor ha enviado: " + respuesta);
                    //*************************************************************

                    //----------------------------------------OBJETO-------------------------------------------------------------------------
                    //******************************FLUYO DE BYTES PARA ALMACENAR EL OBJETO EN BYTES*******************************
                    // Se crea un flujo de bytes de salida en memoria para almacenar en bytes objeto Persona
                    ByteArrayOutputStream salidaBytes = new ByteArrayOutputStream();
                    // Se crea un objeto utilizado para escribir objetos en el flujo de bytes creado
                    ObjectOutputStream salidaObjetos = new ObjectOutputStream(salidaBytes);
                    //********************************************

                    //***************CREAR OBJETO PARA ENVIAR AL CLIENTE*****************************
                    DatosCliente ObjetoClienteEnviar = new DatosCliente(nombre, puertoCliente);  // Crear objeto cliente para enviar
                    salidaObjetos.writeObject(ObjetoClienteEnviar);
                    salidaObjetos.flush();
                    //****************************
                    
                    //***************ENVIAR EL OBJETO DEL FLUJO DE BYTES EN ARRAY AL CLIENTE******************
                    // Se convierte el flujo de bytes en un array
                    byte[] datosEnviados = salidaBytes.toByteArray();
                    // En este punto, el objeto se ha convertido en una secuencia de bytes
                    DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, direccionCliente, puertoCliente);
                    socketServidor.send(paqueteEnviado);
                    //***********************************************

                    //***********************MOSTRAR POR PANTALLA COMO SE HA ENVIADO************************************
                    System.out.println("Servidor ha enviado un objeto con el número: " + ObjetoClienteEnviar.getNumero());
                    //*************************************************************
                    //-----------------------------------------------------------------------------------------------------------------

                } catch (IOException e) {
                    System.err.println("Tiempo de espera agotado para recibir paquetes");
                    break;
                }
                //*************************************************************

            }

            // Cerrar el socket
            socketServidor.close();

            //*************************************************************
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
