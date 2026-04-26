public class MovimientoStock {

    // ── Constantes de tipo 
    public static final String ENTRADA = "ENTRADA";
    public static final String SALIDA  = "SALIDA";

    // ── Contador estático
    private static int contadorId = 1;

    // ── Atributos privados 
    private int      idMovimiento;
    private String   tipo;       // "ENTRADA" o "SALIDA"
    private int      cantidad;
    private String   fecha;

    // Dependencia
    private Producto productoAsociado;

    // Constructor 
    public MovimientoStock(String tipo, int cantidad, String fecha, Producto productoAsociado) {
        this.idMovimiento    = contadorId++;
        this.productoAsociado = productoAsociado;
        setTipo(tipo);
        setCantidad(cantidad);
        this.fecha = fecha;
    }

    // Getters 
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public Producto getProductoAsociado() {
        return productoAsociado;
    }

    // Setters con validación 
    public void setTipo(String tipo) {
        if (ENTRADA.equalsIgnoreCase(tipo) || SALIDA.equalsIgnoreCase(tipo)) {
            this.tipo = tipo.toUpperCase();
        } else {
            System.out.println("[ERROR] Tipo de movimiento inválido: '" + tipo +
                               "'. Debe ser ENTRADA o SALIDA.");
            this.tipo = ENTRADA; // valor por defecto
        }
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("[ERROR] La cantidad debe ser mayor a cero.");
            this.cantidad = 1;
        } else {
            this.cantidad = cantidad;
        }
    }

       public boolean registrarMovimiento() {
        if (productoAsociado == null) {
            System.out.println("[ERROR] No hay producto asociado al movimiento.");
            return false;
        }

        if (tipo.equals(ENTRADA)) {
            productoAsociado.setStock(productoAsociado.getStock() + cantidad);
            System.out.println("[OK] ENTRADA registrada: +" + cantidad +
                               " unidades de '" + productoAsociado.getNombre() + "'.");
            System.out.println("     Stock actualizado: " + productoAsociado.getStock());
            return true;

        } else { // SALIDA
            if (cantidad > productoAsociado.getStock()) {
                System.out.println("[ERROR] Stock insuficiente para '" +
                                   productoAsociado.getNombre() + "'. Disponible: " +
                                   productoAsociado.getStock() + ", solicitado: " + cantidad);
                return false;
            }
            productoAsociado.setStock(productoAsociado.getStock() - cantidad);
            System.out.println("[OK] SALIDA registrada: -" + cantidad +
                               " unidades de '" + productoAsociado.getNombre() + "'.");
            System.out.println("     Stock actualizado: " + productoAsociado.getStock());
            return true;
        }
    }

    @Override
    public String toString() {
        String prod = productoAsociado != null ? productoAsociado.getNombre() : "N/A";
        return String.format("[Mov.%03d] %-8s | Producto: %-20s | Cant: %4d | Fecha: %s",
                idMovimiento, tipo, prod, cantidad, fecha);
    }
}