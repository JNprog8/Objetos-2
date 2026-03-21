package pedidos;

import catalogo.Categoria;
import catalogo.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final List<Item> items;
    private Estado estado;

    public Pedido() {
        this.items = new ArrayList<>();
        this.estado = Estado.SIN_CONFIRMAR;
    }

    public void agregarItem(Producto producto, int cantidad) {
        validarEstadoSinConfirmar();
        this.items.add(new Item(producto, cantidad));
    }

    private void validarEstadoSinConfirmar() {
        if (this.estado == Estado.CONFIRMADO) {
            throw new IllegalStateException("No se pueden agregar ítems a un pedido confirmado.");
        }
    }

    public void confirmar() {
        validarListaItemsNoVacia();
        this.estado = Estado.CONFIRMADO;
    }

    private void validarListaItemsNoVacia() {
        if (this.items.isEmpty()) {
            throw new IllegalStateException("No se puede confirmar un pedido vacío.");
        }
    }

    public boolean estaConfirmado() {
        return this.estado == Estado.CONFIRMADO;
    }

    public double calcularSubtotalBebidas() {
        return calcularSubtotalPorCategoria(Categoria.BEBIDA);
    }

    public double calcularSubtotalPlatosPrincipales() {
        return calcularSubtotalPorCategoria(Categoria.PLATO_PRINCIPAL);
    }

//    public double calcularSubtotalGeneral() {
//        double total = 0;
//        for (Item item : items) {
//            total += item.calcularSubtotal();
//        }
//        return total;
//    }

    public double calcularSubtotalGeneral() {
        return this.items.stream()
                .mapToDouble(Item::calcularSubtotal)
                .sum();
    }

//    private double calcularSubtotalPorCategoria(Categoria categoria) {
//        double subtotal = 0;
//        for (Item item : items) {
//            if (item.perteneceACategoria(categoria)) {
//                subtotal += item.calcularSubtotal();
//            }
//        }
//        return subtotal;
//    }

    private double calcularSubtotalPorCategoria(Categoria categoria) {
        return this.items.stream()
                .filter(item -> item.perteneceACategoria(categoria))
                .mapToDouble(Item::calcularSubtotal)
                .sum();
    }
}