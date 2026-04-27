package sistemabancario;

public class CuentaBancaria {
    // Atributos
    private double saldo;
    private static int contadorCuentas = 0;
    private final int numeroCuenta;

    // Constructor
    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
        contadorCuentas++;
        this.numeroCuenta = contadorCuentas;
        System.out.println("¡Cuenta creada exitosamente! ");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Su saldo inicial: $" + saldoInicial);
    }

    // Método para depositar
    public void depositar(double monto) {
        try {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero");
            }
            saldo += monto;
            System.out.println("Se realizó el depósito exitosamente: $" + monto);
            System.out.println("Nuevo saldo: $" + saldo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error en depósito: " + e.getMessage());
        }
    }

    // Método para retirar
    public void retirar(double monto) {
        try {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
            }
            if (monto > saldo) {
                throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: $" + saldo);
            }
            saldo -= monto;
            System.out.println("Retiro exitoso: $" + monto);
            System.out.println("Nuevo saldo: $" + saldo);
        } catch (IllegalArgumentException | SaldoInsuficienteException e) {
            System.out.println("Error en retiro: " + e.getMessage());
        }
    }

    // Getters
    public double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    // Métodos estáticos
    public static int getTotalCuentas() {
        return contadorCuentas;
    }

    public static void mostrarTotalCuentas() {
        System.out.println("\n=== TOTAL DE CUENTAS CREADAS: " + contadorCuentas + " ===");
    }

    // Mostrar información de la cuenta
    public void mostrarInformacion() {
        System.out.println("\n=== INFORMACIÓN DE LA CUENTA ===");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo actual: $" + saldo);
        System.out.println("=================");
    }
}