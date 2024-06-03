package Orden_Ascendente;

import java.io.IOException;

/**
 *
 * @author laura
 */
public class Ascendente {
    //************CONVERTIR A ORDEN ASCENDENTE*************
    public static void main(String[] args) throws IOException {
        //*******PASARLE LOS ARGUMENTOS = numProceso, num1, num2************
        String numProceso = args[0];
        int num1 = Integer.parseInt(args[1]);
        int num2 = Integer.parseInt(args[2]);
        //*********************
        
        //***************ORDENAR DE FORMA ASCENDENTE
        if (num1 > num2) {
            int asignacion = num1;
            num1 = num2;
            num2 = asignacion;
        }
        //*********************

        //Sacarlo por pantalla
        System.out.println("Proceso " + numProceso + ": Los números en orden ascendente son: " + num1 + " " + num2);
        //**********************************************
    }
    //******************************
}