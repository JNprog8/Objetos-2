package facturacion.tarjeta;

public interface TarjetaCredito {
    double descuentoBebida(double subtotal);
    double descuentoPlatoPrincipal(double subtotal);
}