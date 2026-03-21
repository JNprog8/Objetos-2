package facturacion.tarjetas;

import pedidos.Pedido;

public class TarjetaGenerica implements TarjetaCredito {
    @Override
    public double calcularDescuento(Pedido pedido) {
        // "Cualquier otro tipo de tarjeta no posee descuento."
        return 0.0;
    }
}