package punto2.model;

public class Calculadora {
    private static final double VALOR_INICIAL = 0.0;
    private double valorAcumulado;
    private EstadoCalculadora estadoComputadora;

    public Calculadora() {
        this.estadoComputadora = new EstadoInicial(this);
        this.valorAcumulado = VALOR_INICIAL;
    }

    public String estado() {
        return this.estadoComputadora.toString();
    }

    public void mas() {
        this.estadoComputadora.mas();
    }

    public void borrar() {
        this.estadoComputadora.borrar();
        this.valorAcumulado = VALOR_INICIAL;
    }

    public void valor(double valor) {
        this.estadoComputadora.valor(valor);
    }

    public String mostrar() {
        return this.estadoComputadora.mostrar();
    }

    public void menos() {
        this.estadoComputadora.menos();
    }

    public void dividir() {
        this.estadoComputadora.dividido();
    }

    public void por() {
        this.estadoComputadora.por();
    }

    protected void nuevoEstado(EstadoCalculadora nuevoEstado) {
        this.estadoComputadora = nuevoEstado;
    }

    protected double valorAcumulado() {
        return this.valorAcumulado;
    }

    protected void nuevoValor(double valor) {
        this.valorAcumulado = valor;
    }

    protected boolean dividirValor(double valor) {

        if (valor == 0) {
            this.nuevoEstado(new EstadoError(this));
            return false;
        }

        this.valorAcumulado /= valor;
        return true;
    }
}
