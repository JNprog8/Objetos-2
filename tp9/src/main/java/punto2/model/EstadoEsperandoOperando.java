package punto2.model;

public abstract class EstadoEsperandoOperando implements EstadoCalculadora {
    private static final String ESPERANDO_OPERANDO = "ESPERANDO OPERANDO";
    protected Calculadora calculadora;

    public EstadoEsperandoOperando(Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    @Override
    public String mostrar() {
        calculadora.nuevoEstado(new EstadoError(calculadora));
        return calculadora.mostrar();
    }

    @Override
    public void borrar() {
        calculadora.nuevoEstado(new EstadoInicial(calculadora));
    }

//    @Override
//    public void valor(double unValor) {
//        calculadora.nuevoEstado(new EstadoInicial(calculadora));
//    }

    @Override
    public void mas() {
        calculadora.nuevoEstado(new EstadoError(calculadora));
    }

    @Override
    public void menos() {
        calculadora.nuevoEstado(new EstadoError(calculadora));
    }

    @Override
    public void dividido() {
        calculadora.nuevoEstado(new EstadoError(calculadora));
    }

    @Override
    public void por() {
        calculadora.nuevoEstado(new EstadoError(calculadora));
    }

    @Override
    public String toString() {
        return ESPERANDO_OPERANDO;
    }
}
