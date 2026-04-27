/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemabancario;

import java.util.Scanner;
import sistemabancario.CuentaBancaria;


public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuenta = null;
        
        System.out.println("=== SISTEMA BANCARIO ===");
        System.out.print("Ingrese el saldo inicial para la nueva cuenta: $");
        
        double saldoInicial = scanner.nextDouble();

        try {
            if (saldoInicial < 0) {
                throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
            }
            cuenta = new CuentaBancaria(saldoInicial);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
            System.exit(1);
        }

        // Menú de operaciones
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== Menú de OPERACIONES ===");
            System.out.println("1. Depositar dinero");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Ver información de la cuenta");
            System.out.println("5. Crear nueva cuenta");
            System.out.println("6. Ver total de cuentas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese monto a depositar: $");
                    double deposito = scanner.nextDouble();
                    cuenta.depositar(deposito);
                    break;
                    
                case 2:
                    System.out.print("Ingrese monto a retirar: $");
                    double retiro = scanner.nextDouble();
                    cuenta.retirar(retiro);
                    break;
                    
                case 3:
                    System.out.println("Saldo Actual: $" + cuenta.getSaldo());
                    break;
                    
                case 4:
                    cuenta.mostrarInformacion();
                    break;
                    
                case 5:
                    System.out.print("Ingresar el saldo inicial para la nueva cuenta: $");
                    saldoInicial = scanner.nextDouble();
                    try {
                        if (saldoInicial < 0) {
                            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
                        }
                        cuenta = new CuentaBancaria(saldoInicial);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al crear cuenta: " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    CuentaBancaria.mostrarTotalCuentas();
                    break;
                    
                case 7:
                    continuar = false;
                    System.out.println("¡Gracias por usar el sistema bancario!");
                    break;
                    
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }
}