package Generar_Hash_Ejercicio3_TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author laura
 */
public class Servidor {
        public static void main(String[] args) throws IOException  {
            
            int puerto = 6001;//Puerto en el que vamos a tener abierto para escuchar las peticiones
            Socket cliente = null; //Le daremos valor cuando un cliente se conecte
            ServerSocket servidor = null; //Servidor
            
            try {
                servidor = new ServerSocket(puerto); //Configurar el servidor
                System.out.println("SERVER escuchando " + servidor.toString()); //Saber si se ha conectado

                //Si no recibe peticiones que se pare; sino continua
                while (true) {
                    //Esperar a que un cliente haga la conexión
                    cliente = servidor.accept();
                    //En cuanto le llegue una petición del cliente, generamos un hilo de la clase Gestor para que sea este hilo el que atienda al cliente
                    Gestor hilo = new Gestor(cliente); //Crear un hilo y le pasamos el socket con el que va a trabajar
                    hilo.start(); //Arrancar el hilo

               }
            }catch(IOException ex){
                //Cuando haya alguna excepción, lo cierro
                servidor.close();
                
            }
        }
}
