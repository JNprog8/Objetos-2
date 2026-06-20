package punto3.model;

public class EstadoDebil implements EstadoGolondrina {
    private static final int ENERGIA_DEBIL = 50;
    private static final int GRAMOS_DESEO = 50;
    private final Golondrina golondrina;

    public EstadoDebil(Golondrina golondrina) {
        this.golondrina = golondrina;
    }

    @Override
    public void realizarDeseo() {
        golondrina.comer(GRAMOS_DESEO);
    }

    @Override
    public void comer(int gramos) {

        golondrina.ganarEnergia(gramos);

        if (golondrina.energia() >= ENERGIA_DEBIL) {
            golondrina.nuevoEstado(new EstadoNormal(golondrina));
        }
    }

    @Override
    public void volar(int kms) {
        golondrina.perderEnergia(kms);
        // o tambien puede no hacer nada
        // opcional - seguir debil
        // golondrina.nuevoEstado(this);
    }
}
