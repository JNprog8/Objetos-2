package logica.facturacion.tarjetas;

public class Visa extends TarjetaBase {
    private static final double PORCENTAJE_DESCUENTO = 0.03;

    @Override
    public double descuentoSobreBebida(double monto) {
        return monto * PORCENTAJE_DESCUENTO;
    }
}
