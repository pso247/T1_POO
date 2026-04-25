package com.mycompany.caso1;

public class Main {
	public static void main(String[] args) {

        // Registrar 3 estudiantes usando el constructor
		Estudiante est1 = new Estudiante("Rony", 29, "Ingeniería de Sistemas");
        Estudiante est2 = new Estudiante("Paul", 25, "Ingeniería de Sistemas");
        Estudiante est3 =new Estudiante("Bruce", 26, "Ingeniería de Sistemas");

        // Mostrar datos
        est1.mostrarDatos();
        est2.mostrarDatos();
        est3.mostrarDatos();
    }

}
