package punto3.model;

public class Golondrina {
    private static final int ESTADO_INICIAL = 45;
    private int energia;
    private EstadoGolondrina estado;

    public Golondrina() {
        this.energia = ESTADO_INICIAL;
        this.estado = new EstadoDebil(this);
    }

    public void comer(int gramos) {
        estado.comer(gramos);
    }

    public void volar(int kms) {
        estado.volar(kms);
    }

    public void realizarDeseo() {
        estado.realizarDeseo();
    }

    void ganarEnergia(int cantidad) {
        this.energia += (5 * cantidad);
    }

    void perderEnergia(int cantidad) {
        this.energia -= (10 + cantidad);
    }

    int energia() {
        return energia;
    }

    void nuevoEstado(EstadoGolondrina estado) {
        this.estado = estado;
    }
}