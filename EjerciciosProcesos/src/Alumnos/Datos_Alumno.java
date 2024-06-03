/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumnos;

import java.util.Scanner;

/**
 *
 * @author guipe
 */
public class Datos_Alumno {

    String nombre = null;
    int edad = 0;
    


    public String PedirNombre() {
        Scanner scanner = new Scanner(System.in);
        String datoRecibido = null;
        while (true) {
            String nombre;
            //*****************INGRESAR EL NOMBRE A ANALIZAR********************
            System.out.print("Ingrese el nombre del alumno (o '*' para terminar): ");
            datoRecibido = scanner.nextLine();
            //***************************
            if (datoRecibido.equals("*")) {

                break; // Salir del bucle si se ingresa '*'
            }
            //*********CONDICIONES QUE CONTIENE EL NOMBRE************
            if (datoRecibido.trim().isEmpty()) {
                System.out.println("El nombre no puede estar en blanco. Por favor, ingrese un nombre válido.");
            } else {
                nombre = datoRecibido;
                PedirEdad();
                return nombre;
            }

        }

        scanner.close();
        return "*";

    }

    public void PedirEdad() {
        Scanner scanner = new Scanner(System.in);
        while (edad < 1 || edad > 99) {
            //*****************INGRESAR LE EDAD A ANALIZAR********************
            System.out.print("Ingrese la edad del alumno (entre 1 y 99): ");
            edad = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
    
       

}
