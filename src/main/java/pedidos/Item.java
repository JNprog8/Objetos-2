package pedidos;

import catalogo.Categoria;
import catalogo.Producto;

// Representa la "Línea de Pedido" (El producto + la cantidad que la mesa quiere)
public class Item {
    private final Producto producto;
    private final int cantidad;

    public Item(Producto producto, int cantidad) {
        validarProducto(producto);
        validarCantidadPositiva(cantidad);
        this.producto = producto;
        this.cantidad = cantidad;
    }

    private static void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
    }

    private static void validarCantidadPositiva(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
    }

    public double calcularSubtotal() {
        return this.producto.precio() * this.cantidad;
    }

    public boolean perteneceACategoria(Categoria categoria) {
        return this.producto.esCategoria(categoria);
    }
}