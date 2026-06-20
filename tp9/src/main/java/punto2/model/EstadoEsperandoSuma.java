package punto2.model;

public class EstadoEsperandoSuma extends EstadoEsperandoOperando {
    private static final String ESPERANDO_SUMA = "ESPERANDO SUMA";

    public EstadoEsperandoSuma(Calculadora calculadora) {
        super(calculadora);
    }

    @Override
    public void valor(double unValor) {
        double resultado = super.calculadora.valorAcumulado() + unValor;
        super.calculadora.nuevoValor(resultado);
        super.calculadora.nuevoEstado(new EstadoInicial(super.calculadora));
    }

    @Override
    public String toString() {
        return ESPERANDO_SUMA;
    }
}
