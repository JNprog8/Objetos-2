package punto3.solucion3.model;

public class EstadoEuforico implements EstadoAnimo {
    private static final int UMBRAL_EUFORICO = 500;
    private static final int KILOMETROS_PASEO = 5;

    @Override
    public void realizarDeseo(Golondrina golondrina) {
        // vuela 5km ida y vuelta
        golondrina.volar(KILOMETROS_PASEO);
        golondrina.volar(KILOMETROS_PASEO);
    }

    @Override
    public boolean aplicarSegunEnegia(int energia) {
        return energia > UMBRAL_EUFORICO;
    }
}
