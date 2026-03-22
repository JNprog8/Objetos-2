package facturacion.tarjeta;

public class TarjetaMastercard implements TarjetaCredito {
    @Override
    public double descuentoBebida(double subtotal) {
        return 0.0;
    }

    @Override
    public double descuentoPlatoPrincipal(double subtotal) {
        return subtotal * 0.02;
    }
}
