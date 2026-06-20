package punto3.solucion3.model;

public class EstadoDebil implements EstadoAnimo {
    private static final int UMBRAL_DEBIL = 50;
    private static final int GRAMOS_DESEO = 50;

    @Override
    public void realizarDeseo(Golondrina golondrina) {
        golondrina.comer(GRAMOS_DESEO);
    }

    @Override
    public boolean aplicarSegunEnegia(int energia) {
        return energia < UMBRAL_DEBIL;
    }
}
