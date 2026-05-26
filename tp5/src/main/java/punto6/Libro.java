package punto6;

public class Libro extends Producto {

    private static final double DESCUENTO = 0.1;
    private static final double IMPUESTO = 0.1;

    public Libro(double precio) {
        super(precio);
    }

    @Override
    protected double aplicarDescuento() {
        return DESCUENTO;
    }

    @Override
    protected double aplicarImpuesto() {
        return IMPUESTO;
    }

    @Override
    protected boolean tieneEnvioGratis() {
        return precio > 100;
    }
}
