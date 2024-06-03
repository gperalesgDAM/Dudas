package Ejercicio1_UDP;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author laura
 */
public class DatosCliente implements Serializable {

    //Crear las variables que el objeto va a obtener
    private final String nombre;
    private final int numero;

    //Constructor
    DatosCliente(String nombre, int puertoCliente) {
        this.nombre = nombre;
        numero = new Random().nextInt(100); //Aleatorio entre 0 y 100
    }

    //Get
    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

}
