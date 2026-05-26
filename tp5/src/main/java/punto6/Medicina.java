package punto6;

public class Medicina extends Producto {

    private static final int MONTO_MINIMO_DESCUENTO = 50;
    private static final double DESCUENTO = 0.1;
    private static final int MONTO_MINIMO_ENVIO_GRATIS = 100;

    public Medicina(double precio) {
        super(precio);
    }

    @Override
    protected double aplicarDescuento() {
        return (precio > MONTO_MINIMO_DESCUENTO) ? DESCUENTO : 0;
    }

    @Override
    protected double aplicarImpuesto() {
        return 0;
    }

    @Override
    protected boolean tieneEnvioGratis() {
        return (precio > MONTO_MINIMO_ENVIO_GRATIS);
    }
}
