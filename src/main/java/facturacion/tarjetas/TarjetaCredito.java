package facturacion.tarjetas;

import pedidos.Pedido;

public interface TarjetaCredito {
    double calcularDescuento(Pedido pedido);
}