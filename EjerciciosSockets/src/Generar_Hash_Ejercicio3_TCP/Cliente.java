package Generar_Hash_Ejercicio3_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author laura
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        //Cargar los parámetros que vamos a necesitar
        BufferedReader entrada = null;
        PrintWriter salida = null;
        Socket cliente = null;
        String host = "localhost";
        int puerto = 6001;
        
        //Configurar el host y el puerto del socket
        cliente = new Socket(host, puerto);
        //Configurar los canales de entrada y salida como hemos hecho en el servidor
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())),true);
        

        //Recibir el mensaje del servidor --> RECIBIR
        System.out.println("Servidor dice: " + entrada.readLine());
        
        
        //Scanner porque lo vamos a insertar
        Scanner scanner = new Scanner(System.in);
        
        //Enviar el mensaje al servidor en este caso una cadena --> ENVIAR
        String linea = scanner.nextLine();
        salida.println(linea);
        //Recibir la respuesta del servidor --> RECIBIR
        System.out.println("Servidor dice: " + entrada.readLine());
        
        //Cerrar todo
        salida.close();
        entrada.close();
        scanner.close();
        cliente.close();   
    }

}


//OPERACIONES
//Hilo --> envía, recibe, envía
//Cliente --> recibe, envía, recibe