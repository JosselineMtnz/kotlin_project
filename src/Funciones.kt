val productosDisponibles = mutableListOf(
    Producto("Laptop", 800.0, 10),
    Producto("Smartphone", 500.0, 20),
    Producto("Tablet", 300.0, 15),
    Producto("Cable", 25.0, 8),
    Producto("Mouse", 50.0, 20),
    Producto("Teclado", 50.0, 20)
)

fun mostrarProductos(productos: List<Producto>) {
    println("\nProductos disponibles:")
    println("Nombre".padEnd(15) + "Precio".padEnd(10) + "Stock")
    println("-".repeat(35))
    productos.forEach {
        println("${it.nombre.padEnd(15)} ${"$${it.precio}".padEnd(10)} ${it.cantidadDisponible}")
    }
}

fun agregarAlCarrito(productos: List<Producto>, carrito: Carrito) {
    println("\nIngrese el nombre del producto a agregar al carrito:")
    val nombreProducto = readLine()?.trim()
    val producto = productos.find { it.nombre.equals(nombreProducto, ignoreCase = true) }

    if (producto != null) {
        print("Ingrese la cantidad que desea agregar: ")
        val cantidad = readLine()?.toIntOrNull() ?: -1
        if (cantidad <= 0) {
            println("Cantidad inválida. Intenta de nuevo.")
            return
        }

        carrito.agregarProducto(producto, cantidad)
    } else {
        println("Producto no encontrado.")
    }
}

fun eliminarDelCarrito(carrito: Carrito) {
    println("\nIngrese el nombre del producto a eliminar del carrito:")
    val nombreProducto = readLine()?.trim() ?: ""
    if (nombreProducto.isEmpty()) {
        println("Nombre de producto inválido.")
        return
    }

    carrito.eliminarProducto(nombreProducto)
}
