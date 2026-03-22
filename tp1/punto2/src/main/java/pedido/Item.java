package pedido;

import catalogo.Producto;
import facturacion.tarjeta.TarjetaCredito;

public class Item {

    private Producto producto;
    private int cantidad;

    public Item(Producto producto, int cantidad) {
        validarProducto(producto);
        validarCantidadPositiva(cantidad);
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularDescuento(TarjetaCredito tarjeta) {
        return this.producto.calcularDescuento(tarjeta, this.cantidad);
    }

    public double calcularSubtotal() {
        return this.producto.calcularSubtotal(this.cantidad);
    }

    // Validaciones

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
}