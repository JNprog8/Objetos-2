package punto4.model;

public class Jubilado extends Calculador {

    private static final double SOBRECARGO_REGULAR = 0.1;
    private static final double SIN_SOBRECARGO = 0.0;

    public Jubilado(int mesEnPromocion, LogTransaction log) {
        super(mesEnPromocion, log);
    }

    @Override
    protected double sobrecargo(double precioProducto) {
        if (!esMesEnPromocion()) {
            return precioProducto * SOBRECARGO_REGULAR;
        }
        return SIN_SOBRECARGO;
    }
}
