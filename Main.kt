//Interaccion con el usuario 
fun main() {
    val productosDisponibles = mutableListOf(
        Producto("Laptop", 800.0, 10),
        Producto("Smartphone", 500.0, 20),
        Producto("Tablet", 300.0, 15)
    )
    
    val carrito = Carrito()

    while (true) {
        println("\nBienvenido a la tienda en línea. Elige una opción:")
        println("1. Ver productos")
        println("2. Agregar al carrito")
        println("3. Eliminar del carrito")
        println("4. Ver carrito")
        println("5. Confirmar compra")
        println("6. Salir")
        print("Opción: ")
        
        when (readLine()) {
            "1" -> mostrarProductos(productosDisponibles)
            "2" -> agregarAlCarrito(productosDisponibles, carrito)
            "3" -> eliminarDelCarrito(carrito)
            "4" -> carrito.mostrarCarrito()
            "5" -> {
                carrito.generarFactura()
                break
            }
            "6" -> break
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}

//lista de productos disponibles
fun mostrarProductos(productos: List<Producto>) {
    println("\nProductos disponibles:")
    for (producto in productos) {
        println("${producto.nombre} - Precio: ${producto.precio} - Cantidad disponible: ${producto.cantidadDisponible}")
    }
}

//Función para agregar un producto al carrito
fun agregarAlCarrito(productos: List<Producto>, carrito: Carrito) {
    println("\nIngrese el nombre del producto a agregar al carrito:")
    val nombreProducto = readLine()!!
    val producto = productos.find { it.nombre.equals(nombreProducto, ignoreCase = true) }

    if (producto != null) {
        print("Ingrese la cantidad que desea agregar: ")
        val cantidad = readLine()!!.toInt()

        carrito.agregarProducto(producto, cantidad)
    } else {
        println("Producto no encontrado.")
    }
}

//Función para eliminar un producto del carrito
fun eliminarDelCarrito(carrito: Carrito) {
    println("\nIngrese el nombre del producto a eliminar del carrito:")
    val nombreProducto = readLine()!!
    carrito.eliminarProducto(nombreProducto)
}
