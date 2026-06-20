package punto3.solucion2.model;

public class Golondrina {

    private int energia;

    public Golondrina() {
        this.energia = 45;
    }

    public int energia() {
        return this.energia;
    }

    public void realizarDeseo() {
        estadoDeAnimo().realizarDeseoPara(this);
    }

    public EstadoDeAnimo estadoDeAnimo() {
        return EstadoDeAnimo.toHandle(this);
    }

    void realizarDeseoCuandoNeutra() {
        // Me quedo descansando en mi árbol
    }

    void realizarDeseoCuandoEuforica() {
        this.volar(5);
    }

    void realizarDeseoCuandoDebil() {
        this.comer(50);
    }

    public int energiaRecuperadaAlComer(int cantidadDeGramosIngeridos) {
        return 5 * cantidadDeGramosIngeridos;
    }

    public void comer(int cantidadDeGramosIngeridos) {
        this.energia += energiaRecuperadaAlComer(cantidadDeGramosIngeridos);
    }

    public int energiaConsumidaAlVolar(int unaDistanciaEnKilometros) {
        return 10 + unaDistanciaEnKilometros;
    }

    public void volar(int unaDistanciaEnKilometros) {
        this.energia -= energiaConsumidaAlVolar(unaDistanciaEnKilometros);
    }

    public boolean seSienteDebil() {
        return this.energia < 50 /* joules */;
    }

    public boolean seSienteEuforica() {
        return this.energia > 500 /* joules */;
    }

    public boolean seSienteNeutra() {
        return !seSienteDebil() && !seSienteEuforica();
    }

    private Object shouldImplement() {
        throw new RuntimeException("Should implement");
    }
}
