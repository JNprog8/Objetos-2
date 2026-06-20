package punto3.model;

public class EstadoNormal implements EstadoGolondrina {
    private static final int ENERGIA_DEBIL = 50;
    private static final int ENERGIA_EUFORICA = 500;

    private final Golondrina golondrina;

    public EstadoNormal(Golondrina golondrina) {
        this.golondrina = golondrina;
    }

    @Override
    public void realizarDeseo() {
        // no hace nada
    }

    @Override
    public void comer(int gramos) {
        golondrina.perderEnergia(gramos);
        if (golondrina.energia() > ENERGIA_EUFORICA) {
            golondrina.nuevoEstado(new EstadoEuforica(golondrina));
        }
    }

    @Override
    public void volar(int kms) {
        golondrina.perderEnergia(kms);
        if (golondrina.energia() < ENERGIA_DEBIL) {
            golondrina.nuevoEstado(new EstadoDebil(golondrina));
        }
    }
}
