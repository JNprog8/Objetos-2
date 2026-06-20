package punto3.solucion2.model;

public class EstadoDeAnimoNeutro extends EstadoDeAnimo {

    @Override
    public boolean canHandle(Golondrina golondrina) {
        return golondrina.seSienteNeutra();
    }

    @Override
    public void realizarDeseoPara(Golondrina golondrina) {
        golondrina.realizarDeseoCuandoNeutra();
    }
}
