package logica.pedido;

import logica.catalogo.Producto;
import logica.facturacion.tarjetas.TarjetaCredito;

public class ItemMenu {
    private Producto producto;
    private int cantidad;

    public ItemMenu(Producto producto, int cantidad) {
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

    public double calcularDescuento(TarjetaCredito tarjeta) {
        return this.producto.aplicarDescuento(tarjeta, calcularSubtotal());
    }
}
