import java.util.ArrayList;

public class Inventario {

    
    private int    idInventario;
    private String fecha;
    private int    totalProductos;

    // Agregación: la lista de productos existe independientemente del inventario
    private ArrayList<Producto> productos;

    // Composición: los movimientos pertenecen al inventario
    private ArrayList<MovimientoStock> movimientos;

   
    public Inventario(int idInventario, String fecha) {
        this.idInventario  = idInventario;
        this.fecha         = fecha;
        this.totalProductos = 0;
        this.productos     = new ArrayList<>();
        this.movimientos   = new ArrayList<>();
    }

  
    public int getIdInventario()    { return idInventario; }
    public String getFecha()        { return fecha; }
    public int getTotalProductos()  { return totalProductos; }

    
    //  RF-01: Registrar Producto
    
    /**
     * Agrega un producto al inventario. Valida que no exista un ID duplicado
     * y que los campos obligatorios sean correctos (HU-01, Criterio exitoso/fallido).
     */
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("[ERROR] El producto no puede ser nulo.");
            return;
        }
        // Validación: nombre vacío o precio negativo
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            System.out.println("[ERROR] El nombre del producto es obligatorio. No se registró.");
            return;
        }
        if (producto.getPrecio() < 0) {
            System.out.println("[ERROR] El precio es negativo. No se registró.");
            return;
        }
        // Validación: ID duplicado
        if (buscarProductoPorId(producto.getId()) != null) {
            System.out.println("[ERROR] Ya existe un producto con ID " + producto.getId() + ".");
            return;
        }
        productos.add(producto);
        totalProductos = productos.size();
        System.out.println("[OK] Producto '" + producto.getNombre() + "' registrado correctamente.");
    }

    
    //  RF-04: Eliminar Producto
   
    /**
     * Elimina un producto por su ID. No permite eliminar si tiene movimientos activos.
     */
    public void eliminarProducto(int id) {
        Producto p = buscarProductoPorId(id);
        if (p == null) {
            System.out.println("[ERROR] No se encontró un producto con ID " + id + ".");
            return;
        }
        // Verificar si tiene movimientos activos
        for (MovimientoStock mov : movimientos) {
            if (mov.getProductoAsociado() != null &&
                mov.getProductoAsociado().getId() == id) {
                System.out.println("[ERROR] El producto '" + p.getNombre() +
                                   "' tiene movimientos activos y no puede eliminarse.");
                return;
            }
        }
        productos.remove(p);
        totalProductos = productos.size();
        System.out.println("[OK] Producto '" + p.getNombre() + "' eliminado del inventario.");
    }

    
    //  RF-03: Buscar Producto
    

    /** Busca por nombre o categoría (búsqueda parcial, insensible a mayúsculas). */
    public Producto buscarProducto(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            System.out.println("[ERROR] El criterio de búsqueda no puede estar vacío.");
            return null;
        }
        String criterioMin = criterio.toLowerCase();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(criterioMin) ||
                (p.getCategoria() != null &&
                 p.getCategoria().getNombre().toLowerCase().contains(criterioMin))) {
                return p;
            }
        }
        System.out.println("[INFO] No se encontró ningún producto con el criterio: '" + criterio + "'.");
        return null;
    }

    /** Busca por ID exacto. */
    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /** Lista todos los productos que coinciden con el criterio. */
    public ArrayList<Producto> buscarTodos(String criterio) {
        ArrayList<Producto> resultado = new ArrayList<>();
        String criterioMin = criterio.toLowerCase();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(criterioMin) ||
                (p.getCategoria() != null &&
                 p.getCategoria().getNombre().toLowerCase().contains(criterioMin))) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    
    //  RF-02: Actualizar Stock (registrar movimiento)
    
    /**
     * Registra un movimiento de stock (entrada o salida) y lo guarda en el inventario.
     * Aplica la composición: el movimiento pertenece al inventario.
     */
    public void registrarMovimiento(MovimientoStock movimiento) {
        if (movimiento == null) {
            System.out.println("[ERROR] El movimiento no puede ser nulo.");
            return;
        }
        boolean exito = movimiento.registrarMovimiento();
        if (exito) {
            movimientos.add(movimiento);
        }
    }

    
    //  RF-05: Generar Reporte
    
    /**
     * Genera y devuelve un reporte completo del inventario:
     * productos, stock y valor total (HU-03).
     */
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        String linea = "=".repeat(70);

        sb.append(linea).append("\n");
        sb.append("       REPORTE DE INVENTARIO\n");
        sb.append("       Fecha del sistema: ").append(fecha).append("\n");
        sb.append(linea).append("\n");

        if (productos.isEmpty()) {
            // Criterio fallido HU-03: sin productos registrados
            sb.append("  [INFO] No hay productos registrados en el inventario.\n");
            sb.append(linea).append("\n");
            return sb.toString();
        }

        // Encabezado de tabla
        sb.append(String.format("  %-4s %-22s %-14s %10s %8s %14s\n",
                "ID", "PRODUCTO", "CATEGORÍA", "PRECIO", "STOCK", "VALOR TOTAL"));
        sb.append("-".repeat(70)).append("\n");

        double valorTotalInventario = 0;
        for (Producto p : productos) {
            double valorProducto = p.getPrecio() * p.getStock();
            valorTotalInventario += valorProducto;
            String catNombre = p.getCategoria() != null ? p.getCategoria().getNombre() : "Sin cat.";
            sb.append(String.format("  %-4d %-22s %-14s %10.2f %8d %14.2f\n",
                    p.getId(), p.getNombre(), catNombre,
                    p.getPrecio(), p.getStock(), valorProducto));
        }

        sb.append("-".repeat(70)).append("\n");
        sb.append(String.format("  Total de productos: %d\n", totalProductos));
        sb.append(String.format("  Valor total del inventario: S/. %.2f\n", valorTotalInventario));
        sb.append(linea).append("\n");

        // Resumen de movimientos
        if (!movimientos.isEmpty()) {
            sb.append("\n  HISTORIAL DE MOVIMIENTOS DE STOCK\n");
            sb.append("-".repeat(70)).append("\n");
            for (MovimientoStock mov : movimientos) {
                sb.append("  ").append(mov.toString()).append("\n");
            }
            sb.append(linea).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Inventario{id=" + idInventario + ", fecha='" + fecha +
               "', totalProductos=" + totalProductos + "}";
    }
}
