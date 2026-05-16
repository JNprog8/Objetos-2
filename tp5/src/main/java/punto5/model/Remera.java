package punto5.model;

import java.util.Optional;

public abstract class Remera {
    private static final String ERROR_PRECIO_NEGATIVO = "El precio unitario debe ser mayor a cero.";

    private float precioUnitario;

    public Remera(float precioUnitario) {
        validarPrecio(precioUnitario);
        this.precioUnitario = precioUnitario;
    }

    private static void validarPrecio(float precioUnitario) {
        if (precioUnitario <= 0) {
            throw new IllegalArgumentException(ERROR_PRECIO_NEGATIVO);
        }
    }

    public float calcularPrecio() {
        var precioConRecargos = precioUnitario + recargo() + impuestoAduanero() - bonificacion();
        return precioConRecargos + (precioConRecargos * porcentajeComercio());
    }

    protected abstract float recargo();

    protected abstract float impuestoAduanero();

    protected abstract float bonificacion();

    protected abstract float porcentajeComercio();

    protected Optional<Float> precioUnitario() {
        return Optional.of(precioUnitario);
    }
}


