package com.mycompany.caso3; 
public class Main {

    public static void main(String[] args) {

        System.out.println("SISTEMA DE GESTION DE INVENTARIO Caso 3 UML");

       
        // 1. CREAR CATEGORÍAS (Composición con Producto)
        
        System.out.println("Creando categorias...");
        Categoria catElectronica  = new Categoria(1, "ElectrOnica",  "Dispositivos y accesorios electronicos");
        Categoria catAlimentos    = new Categoria(2, "Alimentos",    "Productos de consumo alimenticio");
        Categoria catLimpieza     = new Categoria(3, "Limpieza",     "ArtIculos de higiene y limpieza");
        System.out.println("  " + catElectronica);
        System.out.println("  " + catAlimentos);
        System.out.println("  " + catLimpieza);

       
        // 2. CREAR PROVEEDORES (Asociación con Producto)
        
        System.out.println("\n Creando proveedores...");
        Proveedor prov1 = new Proveedor(1, "TechDistrib SAC",    "987654321", "ventas@techdistrib.pe");
        Proveedor prov2 = new Proveedor(2, "AlimPeru EIRL",      "912345678", "pedidos@alimperu.pe");
        Proveedor prov3 = new Proveedor(3, "LimpiaMax S.A.",     "933221100", "contacto@limpiamax.pe");
        System.out.println("  " + prov1);
        System.out.println("  " + prov2);
        System.out.println("  " + prov3);

        
        // 3. CREAR PRODUCTOS (RF-01 – Registrar Producto | HU-01)
       
        System.out.println("\n Creando productos (RF-01)...");
        Producto p1 = new Producto(101, "Laptop HP 15\"",  "Laptop Intel Core i5, 8GB RAM, 512GB SSD",  3500.00, 15, catElectronica);
        Producto p2 = new Producto(102, "Mouse Inalambrico", "Mouse USB 2.4GHz, 1600 DPI",              45.00,  80, catElectronica);
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
     
        System.out.println("\n Creando inventario y registrando productos...");
        Inventario inventario = new Inventario(1, "2026-04-25");
        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);
        inventario.agregarProducto(p4);
        inventario.agregarProducto(p5);

       
        // 5. VALIDACIONES – HU-01 Criterio Fallido (campos inválidos)
        
        System.out.println("\n Probando validaciones (HU-01 Criterio fallido)...");
        // Producto con nombre vacío
        Producto invalido1 = new Producto(200, "", "sin nombre", 10.0, 5, catLimpieza);
        inventario.agregarProducto(invalido1);
        // Producto con precio negativo
        Producto invalido2 = new Producto(201, "Producto malo", "precio negativo", -5.0, 5, catAlimentos);
        inventario.agregarProducto(invalido2);
        // ID duplicado
        Producto duplicado = new Producto(101, "Copia Laptop", "duplicado", 100.0, 1, catElectronica);
        inventario.agregarProducto(duplicado);

        
        // 6. MOSTRAR DATOS DE UN PRODUCTO
        
        System.out.println("\n► Detalle de un producto:");
        System.out.println(p1.mostrarDatos());

        
        // 7. RF-03 – BUSCAR PRODUCTO
        
        System.out.println("\n Busqueda de producto (RF-03)...");
        Producto encontrado = inventario.buscarProducto("Mouse");
        if (encontrado != null) {
            System.out.println("  Resultado: " + encontrado);
        }

        Producto porCategoria = inventario.buscarProducto("Alimentos");
        if (porCategoria != null) {
            System.out.println("  Por categoría: " + porCategoria);
        }

        // Búsqueda sin resultados
        inventario.buscarProducto("Nevera");

        
        // 8. RF-02 – ACTUALIZAR STOCK (movimientos de entrada y salida)
        //    HU-02: Criterio exitoso y fallido
        
        System.out.println("\n Registrando movimientos de stock (RF-02 | HU-02)...");

        // ENTRADA exitosa
        MovimientoStock mov1 = new MovimientoStock(MovimientoStock.ENTRADA, 30, "2026-04-25", p2);
        inventario.registrarMovimiento(mov1);

        // SALIDA exitosa
        MovimientoStock mov2 = new MovimientoStock(MovimientoStock.SALIDA, 10, "2026-04-25", p3);
        inventario.registrarMovimiento(mov2);

        // SALIDA fallida (stock insuficiente – HU-02 Criterio fallido)
        System.out.println("\n  [Criterio Fallido HU-02]: Intentar sacar más stock del disponible:");
        MovimientoStock mov3 = new MovimientoStock(MovimientoStock.SALIDA, 999, "2026-04-25", p5);
        inventario.registrarMovimiento(mov3);

        // Otra entrada válida
        MovimientoStock mov4 = new MovimientoStock(MovimientoStock.ENTRADA, 50, "2026-04-25", p4);
        inventario.registrarMovimiento(mov4);

        
        // 9. RF-04 – ELIMINAR PRODUCTO
        
        System.out.println("\n Eliminando producto (RF-04)...");
        // Intento fallido: producto con movimientos activos
        System.out.println("  [Intentando eliminar producto con movimientos activos]:");
        inventario.eliminarProducto(102); // p2 tiene mov1

        // Eliminación exitosa: producto sin movimientos
        Producto pTemp = new Producto(999, "Producto Temporal", "Solo para prueba", 5.0, 3, catLimpieza);
        inventario.agregarProducto(pTemp);
        inventario.eliminarProducto(999);

        
        // 10. RF-05 – GENERAR REPORTE FINAL (HU-03)
        
        System.out.println("\n Generando reporte del inventario (RF-05 | HU-03)...\n");
        System.out.println(inventario.generarReporte());

        
        // 11. PROBAR SETTER CON VALIDACIÓN (Semana 2: manejo de errores)
        
        System.out.println(" Probando setters con validacion (Semana 2 – manejo de errores)...");
        prov1.setEmail("emailinvalido"); // sin @
        prov1.setEmail("nuevo@correo.pe");
        System.out.println("  Email actualizado: " + prov1.getEmail());

        catElectronica.setNombre(""); // nombre vacío
        catElectronica.setNombre("Tecnología");
        System.out.println("  Categoria renombrada: " + catElectronica.getNombre());

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║   Ejecución completada – Sistema de Inventario OK    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
