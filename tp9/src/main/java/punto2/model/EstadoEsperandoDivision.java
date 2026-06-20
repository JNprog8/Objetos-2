package punto2.model;

public class EstadoEsperandoDivision extends EstadoEsperandoOperando {

    public EstadoEsperandoDivision(Calculadora calculadora) {
        super(calculadora);
    }

    @Override
    public void valor(double unValor) {
        if (calculadora.dividirValor(unValor)) {
            calculadora.nuevoEstado(new EstadoInicial(calculadora));
        }
    }

    @Override
    public String toString() {
        return "ESPERANDO DIVISION";
    }
}
