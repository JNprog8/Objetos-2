package facturacion.tarjetas;

import pedidos.Pedido;

public class TarjetaMastercard implements TarjetaCredito {
    @Override
    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularSubtotalPlatosPrincipales() * 0.02;
    }
}