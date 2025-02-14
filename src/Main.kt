fun main() {
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

        when (readLine()?.trim()) {
            "1" -> mostrarProductos(productosDisponibles)
            "2" -> agregarAlCarrito(productosDisponibles, carrito)
            "3" -> eliminarDelCarrito(carrito)
            "4" -> carrito.mostrarCarrito()
            "5" -> {
                carrito.generarFactura()
                return
            }
            "6" -> return
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}
