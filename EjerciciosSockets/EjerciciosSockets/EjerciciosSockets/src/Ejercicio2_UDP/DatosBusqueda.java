package Ejercicio2_UDP; 

import java.io.Serializable; 

public class DatosBusqueda implements Serializable { 
    
    //Declaración de variables que necesitamos
    private String url; 
    private String cadena; 

    //Constructor
    public DatosBusqueda(String url, String cadena) { 
        this.url = url; 
        this.cadena = cadena; 
    }

    //Get
    public String getUrl() {
        return url; 
    }

    public String getCadena() {
        return cadena; 
    }
}