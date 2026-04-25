
package com.mycompany.caso4;

/**
 * @author paul.silva
 */
// Clase Main para probar Herencia y Polimorfismo
public class Main {
    public static void main(String[] args) {
        // Crear objetos de tipo Estudiante y Docente
        Persona estudiante = new Estudiante("Carlos", 20, "Ingenieria");
        Persona docente = new Docente("María", 40, "Matematicas");
        
        // Llamar al método presentar, que se comporta de manera diferente
        // dependiendo del tipo de objeto (polimorfismo)
        estudiante.presentar();  // Llamada al método de Estudiante
        docente.presentar();    // Llamada al método de Docente
    }
}