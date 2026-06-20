package punto3.model;

public class EstadoEuforica implements EstadoGolondrina {

    private static final int ENERGIA_EUFORICA = 500;
    private static final int KMS_DESEO = 5;

    private final Golondrina golondrina;

    public EstadoEuforica(Golondrina golondrina) {
        this.golondrina = golondrina;
    }

    @Override
    public void realizarDeseo() {
        // Deseo de euforica -> volar 5km ida y vuelta (otros 5km) - total 10;
        golondrina.volar(KMS_DESEO);
        golondrina.volar(KMS_DESEO);
    }

    @Override
    public void comer(int gramos) {
        golondrina.ganarEnergia(gramos);
        // permanece eufórica
        // o tambien puede no hacer nada
    }

    @Override
    public void volar(int kms) {
        golondrina.perderEnergia(kms);
        if (golondrina.energia() <= ENERGIA_EUFORICA) {
            golondrina.nuevoEstado(new EstadoNormal(golondrina));
        }
    }
}
