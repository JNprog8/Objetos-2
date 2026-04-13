package logica.facturacion.tarjetas;

public interface TarjetaCredito {
    double descuentoSobreBebida(double monto);

    double descuentoSobrePlatoPrincipal(double monto);
}
