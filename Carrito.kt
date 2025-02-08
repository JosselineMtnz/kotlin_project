//Clase para carrito de compras
class Carrito {
    private val productosEnCarrito = mutableListOf<ProductoCarrito>()

    //se agrega un producto
    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.cantidadDisponible >= cantidad) {
            producto.cantidadDisponible -= cantidad
            val productoCarrito = ProductoCarrito(producto, cantidad)
            productosEnCarrito.add(productoCarrito)
            println("Producto agregado: ${productoCarrito.nombre} - Cantidad: $cantidad")
        } else {
            println("No hay suficiente cantidad de ${producto.nombre} en inventario.")
        }
    }

    //elimina
    fun eliminarProducto(nombre: String) {
        val producto = productosEnCarrito.find { it.nombre == nombre }
        if (producto != null) {
            productosEnCarrito.remove(producto)
            producto.producto.cantidadDisponible += producto.cantidad
            println("Producto eliminado: ${producto.nombre}")
        } else {
            println("Producto no encontrado en el carrito.")
        }
    }

    //muestra
    fun mostrarCarrito() {
        var total = 0.0
        println("\nCarrito de Compras:")
        for (producto in productosEnCarrito) {
            val precioTotal = producto.cantidad * producto.precio
            total += precioTotal
            println("${producto.nombre} - ${producto.cantidad} x ${producto.precio} = $precioTotal")
        }
        println("Total del carrito: $total")
    }

    //Calcula el total 
    fun totalCarrito(): Double {
        return productosEnCarrito.sumOf { it.precio * it.cantidad }
    }

    //Genera la factura
    fun generarFactura() {
        val total = totalCarrito()
        println("\nFactura:")
        for (producto in productosEnCarrito) {
            println("${producto.nombre} - ${producto.cantidad} x ${producto.precio} = ${producto.cantidad * producto.precio}")
        }
        println("Total General: $total")
    }
}

//Clase para representar un Producto en el Carrito
data class ProductoCarrito(val producto: Producto, val cantidad: Int) {
    val nombre get() = producto.nombre
    val precio get() = producto.precio
}
