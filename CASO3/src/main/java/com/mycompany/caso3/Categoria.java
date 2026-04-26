/**
 * Clase Categoria
 * Representa la clasificación de un producto en el inventario.
 * Relación: Composición con Producto (la categoría define al producto).
 *
 * Semana 1-2: Clases, atributos, métodos, encapsulamiento, modificadores de acceso.
 */
public class Categoria {

    // ── Atributos privados (encapsulamiento) ──────────────────────────────────
    private int    idCategoria;
    private String nombre;
    private String descripcion;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Categoria(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre      = nombre;
        this.descripcion = descripcion;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────
    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("[ERROR] El nombre de la categoría no puede estar vacío.");
            return;
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ── Método de representación ──────────────────────────────────────────────
    @Override
    public String toString() {
        return "Categoria{id=" + idCategoria + ", nombre='" + nombre + "', descripcion='" + descripcion + "'}";
    }
}
