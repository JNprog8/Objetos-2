package pedido;

import catalogo.Producto;
import facturacion.tarjeta.TarjetaCredito;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static final String ERROR_PEDIDO_CONFIRMADO = "No se pueden agregar ítems a un pedido confirmado.";
    private static final String ERROR_PEDIDO_VACIO      = "No se puede confirmar un pedido vacío.";

    private List<Item> items;
    private boolean confirmado;

    public Pedido() {
        this.items = new ArrayList<>();
        this.confirmado = false;
    }

    // Comportamiento

    public void agregarItem(Producto producto, int cantidad) {
        validarPedidoNoConfirmado();
        this.items.add(new Item(producto, cantidad));
    }

    public void confirmar() {
        validarListaItemsNoVacia();
        this.confirmado = true;
    }

    public boolean estaConfirmado() {
        return this.confirmado;
    }

    public double calcularDescuento(TarjetaCredito tarjeta) {
        return this.items.stream()
                .mapToDouble(item -> item.calcularDescuento(tarjeta))
                .sum();
    }

    public double calcularSubtotal() {
        return this.items.stream()
                .mapToDouble(Item::calcularSubtotal)
                .sum();
    }

    // Validaciones

    private void validarListaItemsNoVacia() {
        if (this.items.isEmpty()) {
            throw new IllegalStateException(ERROR_PEDIDO_VACIO);
        }
    }
    private void validarPedidoNoConfirmado() {
        if (estaConfirmado()) {
            throw new IllegalStateException(ERROR_PEDIDO_CONFIRMADO);
        }
    }
}