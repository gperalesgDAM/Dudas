package Hacer_Busquedas_Ejercicio2_TCP;

import java.io.*;
import java.net.*;


/**
 *
 * @author laura
 */



public class DatosBusqueda implements Serializable {
    private String url;
    private String cadenaBusqueda;

    // Constructor
    public DatosBusqueda(String url, String cadenaBusqueda) {
        this.url = url;
        this.cadenaBusqueda = cadenaBusqueda;
    }

    // Get
    public String getUrl() {
        return url;
    }
    
    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }
}
