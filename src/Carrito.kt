class Carrito {
    private val productosEnCarrito = mutableMapOf<String, Int>()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (cantidad > producto.cantidadDisponible) {
            println("No hay suficiente stock disponible. Máximo permitido: ${producto.cantidadDisponible}")
            return
        }

        producto.cantidadDisponible -= cantidad
        productosEnCarrito[producto.nombre] = productosEnCarrito.getOrDefault(producto.nombre, 0) + cantidad
        println("Producto agregado al carrito: ${producto.nombre} (x$cantidad)")
    }

    fun eliminarProducto(nombreProducto: String): Boolean {
        return if (productosEnCarrito.containsKey(nombreProducto)) {
            productosEnCarrito.remove(nombreProducto)
            println("Producto eliminado del carrito.")
            true
        } else {
            println("El producto '$nombreProducto' no está en el carrito.")
            false
        }
    }

    fun mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("El carrito está vacío.")
            return
        }

        println("\nCarrito de compras:")
        productosEnCarrito.forEach { (nombre, cantidad) ->
            println("$nombre - Cantidad: $cantidad")
        }
    }

    fun generarFactura() {
        if (productosEnCarrito.isEmpty()) {
            println("No hay productos en el carrito para generar factura.")
            return
        }

        println("\nFactura de compra:")
        var total = 0.0
        productosEnCarrito.forEach { (nombre, cantidad) ->
            val precioUnitario = productosDisponibles.find { it.nombre.equals(nombre, ignoreCase = true) }?.precio ?: 0.0
            val subtotal = precioUnitario * cantidad
            total += subtotal
            println("$nombre - Cantidad: $cantidad - Subtotal: $${"%.2f".format(subtotal)}")
        }

        println("Total a pagar: $${"%.2f".format(total)}")
        productosEnCarrito.clear()
        println("Gracias por su compra!")
    }
}
