package logica.facturacion.tarjetas;

public class ComarcaPlus extends TarjetaBase {
    private static final double DESCUENTO_GENERAL = 0.02;

    @Override
    protected double aplicarDescuentoGeneral(double monto) {
        return monto * DESCUENTO_GENERAL;
    }
}
