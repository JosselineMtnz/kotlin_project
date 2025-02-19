class Carrito {
    private val productosEnCarrito = mutableMapOf<String, Int>()

    var onProductoAgregado: ((Producto, Int) -> Unit)? = null

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (cantidad > producto.cantidadDisponible) {
            println("No hay suficiente stock disponible.")
            return
        }

        producto.cantidadDisponible -= cantidad
        productosEnCarrito[producto.nombre] = productosEnCarrito.getOrDefault(producto.nombre, 0) + cantidad
        println("Producto agregado al carrito: ${producto.nombre} (x$cantidad)")

        // Disparar evento
        onProductoAgregado?.invoke(producto, cantidad)
    }

    fun eliminarProducto(nombreProducto: String): Boolean {
        val claveReal = productosEnCarrito.keys.find { it.equals(nombreProducto, ignoreCase = true) }

        if (claveReal != null) {
            val cantidadEliminada = productosEnCarrito[claveReal] ?: 0
            productosEnCarrito.remove(claveReal)

            val producto = productosDisponibles.find { it.nombre.equals(claveReal, ignoreCase = true) }
            producto?.cantidadDisponible = (producto?.cantidadDisponible ?: 0) + cantidadEliminada

            println("Producto eliminado del carrito. Se han devuelto $cantidadEliminada unidades al stock.")
            return true
        } else {
            println("El producto '$nombreProducto' no está en el carrito.")
            return false
        }
    }

    fun mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("\n🛒 El carrito está vacío.")
            return
        }

        val tasaImpuesto = 0.15 // Se agrega el 15% de IVA
        var total = 0.0

        println("\n🛍️ Carrito de Compras")
        println("=".repeat(60))
        println("Producto".padEnd(20) + "Cantidad".padEnd(10) + "Precio U.".padEnd(12) + "Subtotal")
        println("-".repeat(60))

        productosEnCarrito.forEach { (nombre, cantidad) ->
            val producto = productosDisponibles.find { it.nombre.equals(nombre, ignoreCase = true) }
            val precioUnitario = producto?.precio ?: 0.0
            val subtotal = precioUnitario * cantidad
            total += subtotal

            println("${nombre.padEnd(20)} ${cantidad.toString().padEnd(10)} ${"$${precioUnitario}".padEnd(12)} $${"%.2f".format(subtotal)}")
        }

        val impuesto = total * tasaImpuesto
        val totalConImpuesto = total + impuesto

        println("-".repeat(60))
        println("Subtotal:".padEnd(45) + "$${"%.2f".format(total)}")
        println("Impuesto (15%):".padEnd(45) + "$${"%.2f".format(impuesto)}")
        println("TOTAL A PAGAR:".padEnd(45) + "$${"%.2f".format(totalConImpuesto)}")
        println("=".repeat(60))
    }


    fun generarFactura() {
        if (productosEnCarrito.isEmpty()) {
            println("No hay productos en el carrito para generar factura.")
            return
        }

        val tasaImpuesto = 0.15 // Se agrega el 15% de IVA
        var total = 0.0
        println("\nFactura de compra:")
        println("Producto".padEnd(15) + "Cantidad".padEnd(10) + "Precio U.".padEnd(10) + "Subtotal")

        productosEnCarrito.forEach { (nombre, cantidad) ->
            val producto = productosDisponibles.find { it.nombre.equals(nombre, ignoreCase = true) }
            val precioUnitario = producto?.precio ?: 0.0
            val subtotal = precioUnitario * cantidad
            total += subtotal
            println("${nombre.padEnd(15)} ${cantidad.toString().padEnd(10)} ${"$${precioUnitario}".padEnd(10)} $${"%.2f".format(subtotal)}")
        }

        val impuesto = total * tasaImpuesto
        val totalConImpuesto = total + impuesto

        println("\nSubtotal: $${"%.2f".format(total)}")
        println("Impuesto (15%): $${"%.2f".format(impuesto)}")
        println("Total a pagar: $${"%.2f".format(totalConImpuesto)}")
        println("Gracias por su compra!")

        productosEnCarrito.clear()
    }

}
