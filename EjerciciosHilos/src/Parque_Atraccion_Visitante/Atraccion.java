package Parque_Atraccion_Visitante;

/**
 *
 * @author laura
 */
public class Atraccion extends Thread {

    //*******ATRIBUTOS*********
    private int idVisitante;
    private String nombre;
    private int tiempoDuracion; //(Fijo) (200ms)
    private int cantidadVisitantes; 
    private int maxVisitantes = Integer.MIN_VALUE;
    private int minVisitantes = Integer.MAX_VALUE;
    private String atraccionMasConcurrida = "";
    private String atraccionMenosConcurrida = "";
    //****************
    
    //*********CONSTRUCTOR (NO SE METE LOS ATRIBUTOS QUE TENDREMOS QUE HACER LO QUE NOS DICE)*********
    public Atraccion(int idVisitante, String nombre) {
        this.idVisitante = idVisitante;
        this.nombre = nombre;
    }
    //*******************************
    
    //***************************
    
    @Override
    public void run() {
        synchronized (this) {
            
            //******ITERAR TODOS LOS VISITANTES***********
            for (Visitante visitante : Parque.visitante) {
                try {
                    visitante.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cantidadVisitantes++;
                //*******************
                
                //**********NOTIFICAR AL VISITANTE PARA QUE CONTINÚE************
                synchronized (visitante) {
                    visitante.notify(); 
                }
                //********************
            }
            //**********************
        }
        
        System.out.println("Cantidad de visitantes que tomaron " + nombre + ": " + cantidadVisitantes);
    }
    
    
  
}
