/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.caso4;

/**
 *
 * @author paul.silva
 */
public class Estudiante extends Persona{
    
     private String carrera;
    
    public Estudiante(String nombre, int edad, String carrera) {
        super(nombre, edad); // Llamada al constructor de la clase Persona
        this.carrera = carrera;
    }

    @Override
    public void presentar() {
        System.out.println("Hola, soy un estudiante de la carrera de " + carrera + ".");
    }

    // Métodos getter y setter
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
