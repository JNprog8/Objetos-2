package logica.facturacion.tarjetas;

public class Mastercard extends TarjetaBase {
    private static final double PORCENTAJE_DESCUENTO = 0.02;

    @Override
    public double descuentoSobrePlatoPrincipal(double monto) {
        return monto * PORCENTAJE_DESCUENTO;
    }
}
