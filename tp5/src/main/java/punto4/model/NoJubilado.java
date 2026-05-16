package punto4.model;

public class NoJubilado extends Calculador {

    private static final double IVA_PROMOCION = 0.15;
    private static final double IVA_REGULAR = 0.21;

    public NoJubilado(int mesEnPromocion, LogTransaction log) {
        super(mesEnPromocion, log);
    }

    @Override
    protected double sobrecargo(double precioProducto) {
        if (esMesEnPromocion()) {
            return precioProducto * IVA_PROMOCION;
        } else {
            return precioProducto * IVA_REGULAR;
        }
    }
}
