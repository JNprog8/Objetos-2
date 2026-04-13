package logica.facturacion.tarjetas;

public abstract class TarjetaBase implements TarjetaCredito {

    @Override
    public double descuentoSobreBebida(double monto) {
        return aplicarDescuentoGeneral(monto);
    }

    @Override
    public double descuentoSobrePlatoPrincipal(double monto) {
        return aplicarDescuentoGeneral(monto);
    }

    protected double aplicarDescuentoGeneral(double monto) {
        return 0.0;
    }
}
