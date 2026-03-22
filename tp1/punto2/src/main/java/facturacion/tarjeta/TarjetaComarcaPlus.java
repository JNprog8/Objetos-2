package facturacion.tarjeta;

public class TarjetaComarcaPlus implements TarjetaCredito {
    @Override
    public double descuentoBebida(double subtotal) {
        return subtotal * 0.02;
    }

    @Override
    public double descuentoPlatoPrincipal(double subtotal) {
        return subtotal * 0.02;
    }
}