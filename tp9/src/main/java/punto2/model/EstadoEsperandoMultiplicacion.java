package punto2.model;

public class EstadoEsperandoMultiplicacion extends EstadoEsperandoOperando {
    private static final String ESPERANDO_MULTIPLICACION = "ESPERANDO MULTIPLICACION";

    public EstadoEsperandoMultiplicacion(Calculadora calculadora) {
        super(calculadora);
    }

    @Override
    public void valor(double unValor) {
        double resultado = super.calculadora.valorAcumulado() * unValor;
        super.calculadora.nuevoValor(resultado);
        super.calculadora.nuevoEstado(new EstadoInicial(super.calculadora));
    }

    public String toString() {
        return ESPERANDO_MULTIPLICACION;
    }
}
