package facturacion.tarjeta;

public class TarjetaVisa implements TarjetaCredito {
    @Override
    public double descuentoBebida(double subtotal) {
        return subtotal * 0.03;
    }

    @Override
    public double descuentoPlatoPrincipal(double subtotal) {
        return 0.0;
    }
}