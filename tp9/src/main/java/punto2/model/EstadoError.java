package punto2.model;

public class EstadoError implements EstadoCalculadora {
    private static final String MSG_ESTADO_ERROR = "La calculadora está en estado de error";
    private static final String ERROR = "ERROR";
    private Calculadora calculadora;

    public EstadoError(Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    @Override
    public String mostrar() {
        return MSG_ESTADO_ERROR;
    }

    @Override
    public void borrar() {
        calculadora.nuevoEstado(new EstadoInicial(calculadora));
    }

    @Override
    public void valor(double unValor) {
        System.out.println(MSG_ESTADO_ERROR);
    }

    @Override
    public void mas() {
        System.out.println(MSG_ESTADO_ERROR);
    }

    @Override
    public void menos() {
        System.out.println(MSG_ESTADO_ERROR);
    }

    @Override
    public void dividido() {
        System.out.println(MSG_ESTADO_ERROR);
    }

    @Override
    public void por() {
        System.out.println(MSG_ESTADO_ERROR);
    }

    @Override
    public String toString() {
        return ERROR;
    }
}
