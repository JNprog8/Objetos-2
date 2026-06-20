package punto2.model;

public class EstadoInicial implements EstadoCalculadora {
    private static final String INICIAL = "INICIAL";
    private Calculadora calculadora;

    public EstadoInicial(Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    @Override
    public String mostrar() {
        return String.valueOf(this.calculadora.valorAcumulado());
    }

    @Override
    public void borrar() {
        // no hago nada
    }

    @Override
    public void valor(double unValor) {
        calculadora.nuevoValor(unValor);
    }

    @Override
    public void mas() {
        calculadora.nuevoEstado(new EstadoEsperandoSuma(calculadora));
    }

    @Override
    public void menos() {
        calculadora.nuevoEstado(new EstadoEsperandoResta(calculadora));
    }

    @Override
    public void dividido() {
        calculadora.nuevoEstado(new EstadoEsperandoDivision(calculadora));
    }

    @Override
    public void por() {
        calculadora.nuevoEstado(new EstadoEsperandoMultiplicacion(calculadora));
    }

    @Override
    public String toString() {
        return INICIAL;
    }
}
