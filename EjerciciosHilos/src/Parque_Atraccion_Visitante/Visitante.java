package Parque_Atraccion_Visitante;

/**
 *
 * @author laura
 */
public class Visitante extends Thread {

    //*********ATRIBUTOS******
    private int idVisitante;
    private String nombre;
    //**************

    //******CONSTRUCTOR********
    public Visitante(int idVisitante, String nombre) {
        this.idVisitante = idVisitante;
        this.nombre = nombre;
    }
    //*****************

    //*******GET Y SET*******
    public int getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //************

    @Override
    public void run() {
        
        //********ESPERAR A SER NOTIFICADO PARA COMENZAR LA VISITA A LAS ATRACCIONES*********
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nombre + " ha comenzado a esperar en una atracción");
        //*****************************

        //**********SIMULAR EL RECORRIDO QUE HA TENIDO EL VISITANTE EN LA ATRACCIÓN**************
        try {
            Thread.sleep(200); // Duración fija de recorrido en milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(nombre + " ha terminado su recorrido en la atracción");
        //**********************************
    }

}
