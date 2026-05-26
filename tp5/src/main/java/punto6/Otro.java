package punto6;

public class Otro extends Producto {

    private static final double IMPUESTO = 0.15;
    private static final double DESCUENTO = 0.05;
    private static final int MONTO_MINIMO_DESCUENTO = 50;
    private static final int MONTO_MINIMO_ENVIO_GRATIS = 200;

    public Otro(double precio) {
        super(precio);
    }

    @Override
    protected double aplicarDescuento() {
        return (precio > MONTO_MINIMO_DESCUENTO) ? DESCUENTO : 0;
    }

    @Override
    protected double aplicarImpuesto() {
        return IMPUESTO;
    }

    @Override
    protected boolean tieneEnvioGratis() {
        return (precio > MONTO_MINIMO_ENVIO_GRATIS);
    }
}
