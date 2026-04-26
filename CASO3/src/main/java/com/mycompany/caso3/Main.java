public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTIÓN DE INVENTARIO – Caso 3 UML     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

       
        // 1. CREAR CATEGORÍAS (Composición con Producto)
        
        System.out.println("► Creando categorías...");
        Categoria catElectronica  = new Categoria(1, "Electrónica",  "Dispositivos y accesorios electrónicos");
        Categoria catAlimentos    = new Categoria(2, "Alimentos",    "Productos de consumo alimenticio");
        Categoria catLimpieza     = new Categoria(3, "Limpieza",     "Artículos de higiene y limpieza");
        System.out.println("  " + catElectronica);
        System.out.println("  " + catAlimentos);
        System.out.println("  " + catLimpieza);

       
        // 2. CREAR PROVEEDORES (Asociación con Producto)
        
        System.out.println("\n► Creando proveedores...");
        Proveedor prov1 = new Proveedor(1, "TechDistrib SAC",    "987654321", "ventas@techdistrib.pe");
        Proveedor prov2 = new Proveedor(2, "AlimPeru EIRL",      "912345678", "pedidos@alimperu.pe");
        Proveedor prov3 = new Proveedor(3, "LimpiaMax S.A.",     "933221100", "contacto@limpiamax.pe");
        System.out.println("  " + prov1);
        System.out.println("  " + prov2);
        System.out.println("  " + prov3);

        
        // 3. CREAR PRODUCTOS (RF-01 – Registrar Producto | HU-01)
       
        System.out.println("\n► Creando productos (RF-01)...");
        Producto p1 = new Producto(101, "Laptop HP 15\"",  "Laptop Intel Core i5, 8GB RAM, 512GB SSD",  3500.00, 15, catElectronica);
        Producto p2 = new Producto(102, "Mouse Inalámbrico", "Mouse USB 2.4GHz, 1600 DPI",              45.00,  80, catElectronica);
        Producto p3 = new Producto(103, "Arroz Extra",      "Bolsa de arroz de 5 kg, grano largo",       18.50, 200, catAlimentos);
        Producto p4 = new Producto(104, "Aceite Vegetal",   "Botella de aceite vegetal 1 litro",         9.90,  150, catAlimentos);
        Producto p5 = new Producto(105, "Detergente Ariel", "Detergente en polvo 2 kg, fragancia fresh", 22.00,  60, catLimpieza);

        // Agregar proveedores a productos (Asociación)
        p1.agregarProveedor(prov1);
        p2.agregarProveedor(prov1);
        p3.agregarProveedor(prov2);
        p4.agregarProveedor(prov2);
        p5.agregarProveedor(prov3);
        p1.agregarProveedor(prov3); // un producto puede tener varios proveedores

       
        // 4. CREAR INVENTARIO y AGREGAR PRODUCTOS (Agregación)
     
        System.out.println("\n► Creando inventario y registrando productos...");
        Inventario inventario = new Inventario(1, "2026-04-25");
        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);
        inventario.agregarProducto(p4);
        inventario.agregarProducto(p5);

       
        // 5. VALIDACIONES – HU-01 Criterio Fallido (campos inválidos)
        
        System.out.println("\n► Probando validaciones (HU-01 – Criterio fallido)...");
        // Producto con nombre vacío
        Producto invalido1 = new Producto(200, "", "sin nombre", 10.0, 5, catLimpieza);
        inventario.agregarProducto(invalido1);
        // Producto con precio negativo
        Producto invalido2 = new Producto(201, "Producto malo", "precio negativo", -5.0, 5, catAlimentos);
        inventario.agregarProducto(invalido2);
        // ID duplicado
        Producto duplicado = new Producto(101, "Copia Laptop", "duplicado", 100.0, 1, catElectronica);
        inventario.agregarProducto(duplicado);
