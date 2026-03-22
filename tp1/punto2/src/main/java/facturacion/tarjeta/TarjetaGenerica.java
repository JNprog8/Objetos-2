package facturacion.tarjeta;

public class TarjetaGenerica implements TarjetaCredito {
    @Override
    public double descuentoBebida(double subtotal) {
        return 0.0;
    }

    @Override
    public double descuentoPlatoPrincipal(double subtotal) {
        return 0.0;
    }
}