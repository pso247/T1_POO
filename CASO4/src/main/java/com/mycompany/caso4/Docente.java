
package com.mycompany.caso4;


// Clase Docente que hereda de Persona
public class Docente extends Persona {

    private String materia;
    
    public Docente(String nombre, int edad, String materia) {
        super(nombre, edad); // Llamada al constructor de la clase Persona
        this.materia = materia;
    }

    @Override
    public void presentar() {
        System.out.println("Hola, soy un docente de la materia de " + materia + ".");
    }

    // Métodos getter y setter
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
