package facturacion.tarjetas;

import pedidos.Pedido;

public class TarjetaVisa implements TarjetaCredito {
    @Override
    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularSubtotalBebidas() * 0.03;
    }
}