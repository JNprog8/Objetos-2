package punto2.model;

public class EstadoEsperandoResta extends EstadoEsperandoOperando {
    private static final String ESPERANDO_RESTA = "ESPERANDO RESTA";

    public EstadoEsperandoResta(Calculadora calculadora) {
        super(calculadora);
    }

    @Override
    public void valor(double unValor) {
        double resultado = super.calculadora.valorAcumulado() - unValor;
        super.calculadora.nuevoValor(resultado);
        super.calculadora.nuevoEstado(new EstadoInicial(super.calculadora));
    }

    @Override
    public String toString() {
        return ESPERANDO_RESTA;
    }
}
