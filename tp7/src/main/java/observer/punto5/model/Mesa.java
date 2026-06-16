package observer.punto5.model;

import observer.punto5.model.productos.ItemMenu;
import observer.punto5.model.propinas.Propina;
import observer.punto5.model.tarjetas.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class Mesa extends Subject {
    private int numero;
    private int capacidad;
    private List<Pedido> historialPedidos;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.historialPedidos = new ArrayList<>();
        this.pedidoActual = new Pedido();
    }

    public void agregarItemAPedido(ItemMenu item, int cantidad) {
        this.pedidoActual.agregarItem(item, cantidad);
    }

    public void pagar(Tarjeta tarjeta, Propina propina) {
        if (pedidoActual.obtenerItems().isEmpty()) {
            throw new IllegalStateException("No hay items en el pedido para procesar el pago");
        }

        pedidoActual.confirmar();

        double totalOriginal = pedidoActual.calcularTotalOriginal();
        double subtotalConDescuento = pedidoActual.calcularSubtotalConDescuento(tarjeta);
        double montoPropina = propina.calcularPropina(totalOriginal);
        double totalAFacturar = subtotalConDescuento + montoPropina;

        this.historialPedidos.add(pedidoActual);

        this.notify(totalAFacturar);

        this.pedidoActual = new Pedido();
    }

    public int numero() {
        return numero;
    }

    public int capacidad() {
        return capacidad;
    }

    public Pedido pedidoActual() {
        return pedidoActual;
    }
}
