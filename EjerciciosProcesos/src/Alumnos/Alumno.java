package Alumnos;

/**
 *
 * @author laura
 */
public class Alumno {

    //*******PASARLE LOS ARGUMENTOS = nombre y edad************
    String nombre=null;           
    int edad =0;               
    //********************

    //Constructor
    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    //Métodos set y get
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    

    
    
    //*********MÉTODO MOSTRAR EL MENSAJE***************
    public void mostrarMensaje(String nombre, int edad) {
        System.out.println("Estudiante: " + nombre + ", Edad: " + edad);
    }
    //********************

}