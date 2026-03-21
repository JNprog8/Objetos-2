package facturacion.tarjetas;

import pedidos.Pedido;

public class TarjetaComarcaPlus implements TarjetaCredito {
    @Override
    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularSubtotalGeneral() * 0.02;
    }
}