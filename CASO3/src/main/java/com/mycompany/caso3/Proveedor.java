/**
 * Clase Proveedor
 * Representa a un proveedor de productos del inventario.
 * Relación: Asociación con Producto (ambos pueden existir de forma independiente).
 *
 * Semana 1-2: Clases, atributos, métodos, encapsulamiento, modificadores de acceso.
 */
public class Proveedor {

    // ── Atributos privados (encapsulamiento) ──────────────────────────────────
    private int    idProveedor;
    private String nombre;
    private String telefono;
    private String email;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Proveedor(int idProveedor, String nombre, String telefono, String email) {
        this.idProveedor = idProveedor;
        this.nombre      = nombre;
        this.telefono    = telefono;
        this.email       = email;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────
    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Validación básica de email
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("[ERROR] El email ingresado no es válido: " + email);
        }
    }

    // ── Método de representación ──────────────────────────────────────────────
    @Override
    public String toString() {
        return "Proveedor{id=" + idProveedor +
               ", nombre='" + nombre +
               "', telefono='" + telefono +
               "', email='" + email + "'}";
    }
}
