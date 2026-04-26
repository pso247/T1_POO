import java.util.ArrayList;

public class Producto {

    // ── Atributos privados (encapsulamiento) 
    private int       id;
    private String    nombre;
    private String    descripcion;
    private double    precio;
    private int       stock;

    // Composición: Categoria es parte esencial del Producto
    private Categoria categoria;

    // Asociación: un Producto puede tener varios Proveedores (Semana 2 – Colecciones)
    private ArrayList<Proveedor> proveedores;

    // ── Constructor 
    public Producto(int id, String nombre, String descripcion,
                    double precio, int stock, Categoria categoria) {
        this.id          = id;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);          // usa setter para validar
        setStock(stock);            // usa setter para validar
        this.categoria   = categoria;
        this.proveedores = new ArrayList<>();
    }

    // ── Getters 
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    // ── Setters con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("[ERROR] El nombre del producto no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            System.out.println("[ERROR] El precio no puede ser negativo. Se asigna 0.0.");
            this.precio = 0.0;
        } else {
            this.precio = precio;
        }
    }

    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("[ERROR] El stock no puede ser negativo. Se asigna 0.");
            this.stock = 0;
        } else {
            this.stock = stock;
        }
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // ── Métodos de negocio 

 
    public void agregarProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            proveedores.add(proveedor);
            System.out.println("[OK] Proveedor '" + proveedor.getNombre() +
                               "' agregado al producto '" + nombre + "'.");
        }
    }

    /**
     * Muestra todos los datos del producto con formato legible (RF-05).
     */
    public String mostrarDatos() {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(50)).append("\n");
        sb.append("  PRODUCTO\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("  ID          : ").append(id).append("\n");
        sb.append("  Nombre      : ").append(nombre).append("\n");
        sb.append("  Descripción : ").append(descripcion).append("\n");
        sb.append("  Precio      : S/. ").append(String.format("%.2f", precio)).append("\n");
        sb.append("  Stock       : ").append(stock).append(" unidades\n");
        sb.append("  Categoría   : ").append(categoria != null ? categoria.getNombre() : "Sin categoría").append("\n");
        if (!proveedores.isEmpty()) {
            sb.append("  Proveedores : ");
            for (int i = 0; i < proveedores.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(proveedores.get(i).getNombre());
            }
            sb.append("\n");
        }
        sb.append("=".repeat(50));
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("[%d] %-20s | Cat: %-12s | Precio: S/.%7.2f | Stock: %4d",
                id, nombre,
                categoria != null ? categoria.getNombre() : "N/A",
                precio, stock);
    }
}
