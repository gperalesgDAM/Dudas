package Hacer_Busquedas_Ejercicio2_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author laura
 */

// Clase que maneja las operaciones del cliente (hilo)
public class Cliente implements Runnable {
    private Socket cliente;

    // Constructor para asignar el socket del cliente
    public Cliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            // Flujos de entrada y salida de objetos para comunicarse con el cliente
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());

            while (true) {
                //Solicita la URL al cliente --> ENVIAR
                salidaObjeto.writeObject("Introduzca la URL deseada:");
                String url = (String) entradaObjeto.readObject();

                //Solicita la cadena de búsqueda al cliente --> ENVIAR
                salidaObjeto.writeObject("Introduzca la cadena a buscar:");
                String cadenaBusqueda = (String) entradaObjeto.readObject();

                //Crea un objeto DatosBusqueda con la URL y la cadena de búsqueda
                DatosBusqueda datos = new DatosBusqueda(url, cadenaBusqueda);

                //Realiza la búsqueda y envía el resultado al cliente
                int resultado = buscarCoincidencias(datos);
                salidaObjeto.writeInt(resultado);
                salidaObjeto.flush();

                //Pregunta al cliente si desea realizar otra operación
                salidaObjeto.writeObject("¿Desea realizar una nueva operación? (si/no)");
                String respuesta = (String) entradaObjeto.readObject();
                
                // Sale del bucle si el cliente no desea realizar otra operación
                if (respuesta.equalsIgnoreCase("no")) {
                    break; 
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                
                //Cerrar todo
                cliente.close(); 
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //MÉTODO PARA CONTAR OCURRENCIAS EN LA URL
    private int buscarCoincidencias(DatosBusqueda datos) throws IOException {
        return 0; 
    }
}


//OPERACIONES
//Hilo --> enviar/escribe cadena1, enviar/lee cadena1, recibe, enviar cadena1, enviar cadena2 ¿continuar?
//Cliente --> recibe, recibe, envia objeto, recibe, recibir cadena2