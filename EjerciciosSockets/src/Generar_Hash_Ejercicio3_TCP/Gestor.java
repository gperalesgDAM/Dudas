package Generar_Hash_Ejercicio3_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laura
 */
public class Gestor extends Thread{
    
    //Atributos
    private Socket socket;
    private BufferedReader entrada = null; //Recibir datos por el canal
    private PrintWriter salida = null; //Enviar datos por el canal
    
    //Constructor
    public Gestor(Socket socket){
        this.socket = socket;
    }
    
    //Lo que va hacer este hilo
    @Override
    public void run(){
        try {
            //PUERTO
            //Puerto del cliente
            String puertoCliente = String.valueOf(socket.getPort()); //getPort = obtener el puerto del socket
            System.out.println("Conexión aceptada con origen el puerto: " + puertoCliente);
            //Entrada por el objeto para recibir los datos
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Salida por el objeto para recibir los datos
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
            //CADENA
            //Empezar comunicación con el cliente
            //Solicitar la cadena de texto
            salida.println("Introduce la cadena para obtener su hash: ");
            String cadena = entrada.readLine();
            System.out.println(cadena);
            
            //Llamar a las funciones que calculan el hash y pasarle los parámetros
            String cadenaHash = calcularHashString2(puertoCliente, cadena);
            salida.println("El hash obtenido utilizado como salt tu puerto es: " + cadenaHash);
            
            
            //Cerrar todos menos el servidor
            salida.close();
            entrada.close();
            socket.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //MÉTODO PARA CALCULAR EL HASH
    public static String valorHexadecimal2(byte[] bytes){
        StringBuilder result = new StringBuilder(); //Más eficiente
        for(byte b : bytes){
            result.append(String.format("%02x", b)); //Asegurar que cada byte se presente con dos caracteres hexadecimales
        }
        return result.toString();
    }
    
    private static String calcularHashString2(String puerto, String cadena){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] puertoBytes = (puerto + cadena).getBytes();
            md.update(puertoBytes);
            byte[] dig = md.digest();
            return valorHexadecimal2(dig);
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Error al calcular el hash de la contraseña" , e);
        }
    }
}

