package punto5.model;

public class Importadas extends Remera {
    private static final float RECARGO_IMPORTACION = 0.03f;
    private static final float IMPUESTO_ADUANERO = 0.05f;
    private static final float MARGEN_COMERCIO = 0.25f;

    public Importadas(float precioUnitario) {
        super(precioUnitario);
    }

    @Override
    protected float recargo() {
        return precioUnitario().map(p -> p * RECARGO_IMPORTACION).orElse(0f);
    }

    @Override
    protected float impuestoAduanero() {
        return precioUnitario().map(p -> p * IMPUESTO_ADUANERO).orElse(0f);
    }

    @Override
    protected float bonificacion() {
        return 0;
    }

    @Override
    protected float porcentajeComercio() {
        return MARGEN_COMERCIO;
    }
}
