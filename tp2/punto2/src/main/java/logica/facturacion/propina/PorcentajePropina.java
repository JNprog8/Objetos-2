package logica.facturacion.propina;

public class PorcentajePropina implements Propina {
    public static final Propina DOS_PORCIENTO = new PorcentajePropina(2);
    public static final Propina TRES_PORCIENTO = new PorcentajePropina(3);
    public static final Propina CINCO_PORCIENTO = new PorcentajePropina(5);

    private final double porcentaje;

    private PorcentajePropina(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularSobre(double montoBruto) {
        return montoBruto * (porcentaje / 100.0);
    }
}
