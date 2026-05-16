package punto5.model;

public class Nacional extends Remera {
    private static final float RECARGO_TRANSPORTE = 0.015f;
    private static final float BONIFICACION = 0.2f;
    private static final float MARGEN_COMERCIO = 0.15f;

    public Nacional(float precioUnitario) {
        super(precioUnitario);
    }

    @Override
    protected float recargo() {
        return precioUnitario().map(p -> p * RECARGO_TRANSPORTE).orElse(0f);
    }

    @Override
    protected float impuestoAduanero() {
        return 0;
    }

    @Override
    protected float bonificacion() {
        return precioUnitario().map(p -> p * BONIFICACION).orElse(0f);
    }

    @Override
    protected float porcentajeComercio() {
        return MARGEN_COMERCIO;
    }
}
