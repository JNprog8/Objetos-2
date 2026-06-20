package punto3.solucion2.model;

public class EstadoDeAnimoDebil extends EstadoDeAnimo {

    @Override
    public boolean canHandle(Golondrina golondrina) {
        return golondrina.seSienteDebil();
    }

    @Override
    public void realizarDeseoPara(Golondrina golondrina) {
        golondrina.realizarDeseoCuandoDebil();
    }
}
