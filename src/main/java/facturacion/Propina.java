package facturacion;

public enum Propina {
    DOS_POR_CIENTO(0.02),
    TRES_POR_CIENTO(0.03),
    CINCO_POR_CIENTO(0.05);

    private final double porcentaje;

    Propina(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double calcularMonto(double subtotalBruto) {
        return subtotalBruto * this.porcentaje;
    }
}