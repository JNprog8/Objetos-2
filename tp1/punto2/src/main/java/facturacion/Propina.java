package facturacion;

public enum Propina {
    DOS_PORCIENTO(0.02),
    TRES_PORCIENTO(0.03),
    CINCO_PORCIENTO(0.05);

    private final double valor;

    Propina(double valor) {
        this.valor = valor;
    }

    public double calcularSobre(double monto) {
        return monto * this.valor;
    }
}