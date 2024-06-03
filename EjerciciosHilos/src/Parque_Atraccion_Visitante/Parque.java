package Parque_Atraccion_Visitante;

import java.util.Random;

/**
 *
 * @author laura
 */
public class Parque {
    
    public static void main(String[] args) throws InterruptedException {

    //*********ATRIBUTOS******
    int numVisitantes = new Random().nextInt(151) + 50; // 151 + 50 = 2001
    int numAtracciones = 5;
    int tiempoEsperaParque = 15 * 1000; //15min * 1000ms
    int cantidadVisitantes;
    int maxVisitantes = Integer.MIN_VALUE;
    int minVisitantes = Integer.MAX_VALUE;
    //************************

    //************LO QUE CONTIENE*************
    Atraccion atracc = new Atraccion(12,"prueba");
    Atraccion[] atraccion = new Atraccion[numAtracciones];
    Visitante[] visitante = new Visitante[numVisitantes];
    //private List<Atraccion> atracciones = new ArrayList<>();
    //private List<Visitante> visitantes = new ArrayList<>();
    //**********************

}
    //************************

   
    public void Parque(int numVisitantes) { //Le metemos el numVisitantes porque los visitantes se montan en las atracciones --> Visitante 1 - Atracción 1
        this.numVisitantes = numVisitantes;

        //****INICIALIZACIÓN DE LAS ATRACCIONES*********
        for (int i = 1; i <= numAtracciones; i++) {
            atraccion.add(new Atraccion("Atracción " + i));
        }
        //***************************
        
        //*****INICIALIZACIÓN DE LOS VISITANTES*********
        for (int i = 1; i <= numVisitantes; i++) {
            visitante.add(new Visitante("Visitante " + i));
        }
        //***************************
    }

    //*************

    //*****INICIAR LA SIMULACIÓN******
    public void iniciarSimulacion() {

        //******** 1 --> LLEGADA DE LOS VISITANTES*****************
        System.out.println("El parque abre, los visitantes empiezan a llegar...");
        try {
            Thread.sleep(tiempoEsperaParque); //Cada visitante espera --> sleep --> tiempoEsperaParque
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El parque está lleno, las atracciones comienzan a funcionar...");
        //******************************

        //*********** 2 --> INICIAR CADA ATRACCIÓN******************
        for (int i = 0; i < numAtracciones; i++) {
            atraccion.get(i).start();
        }
        //***********************************

        //******NOTIFICAR A CADA VISITANTE PAR QUE COMIENCE A VISITAR LAS ATRACCIONES***********
        for (int i = 0; i < numVisitantes; i++) {
            synchronized (visitante.get(i)) {
                visitante.get(i).notify();
            }
        }
        //***********************************

        //***************ESPERAR A QUE TODAS LAS ATRACCIONES TERMINEN***********
        for (Atraccion atraccion : atraccion) {
            int visitantesTomaronAtraccion = atraccion.getCantidadVisitantes();
            try {
                atraccion.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Todos los visitantes han terminado sus recorridos");
        //********************************

        //*********CONDICIONES QUE NOS PIDEN********
        for (Atraccion atraccion : atraccion) { //Le metemos una atracción porque las condiciones van dirigidas hacia esa clase
            System.out.println("Cantidad de visitantes que tomaron " + atraccion.getNombre() + ": " + cantidadVisitantes);

            //Atracción que mas visitantes que han montado
            if (cantidadVisitantes > maxVisitantes) {
                maxVisitantes = cantidadVisitantes;
                System.out.println("Atracción más concurrida: " + atraccion.getNombre());
            }
            //********************

            //Atracción que menos visitantes que han montado
            if (cantidadVisitantes < minVisitantes) {
                minVisitantes = cantidadVisitantes;
                System.out.println("Atracción menos concurrida: " + atraccion.getNombre());
            }
            //*********************
        }

    }
    //************
}
