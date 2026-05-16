package punto2.model.composite;

public class HistoriaDeUsuario extends ItemCompuesto {

    private static final String HISTORIA = "Historia";
    private static final String ERROR_HISTORIA_NO_INCLUYE_HISTORIA = "Una Historia de Usuario no puede contener otras historias.";

    public HistoriaDeUsuario(String nombre) {
        super(nombre);
    }

    @Override
    public void agregarA(ItemCompuesto contenedor) {
        contenedor.agregarHistoria(this);
    }

    @Override
    public void agregarHistoria(HistoriaDeUsuario historia) {
        throw new IllegalArgumentException(ERROR_HISTORIA_NO_INCLUYE_HISTORIA);
    }

    @Override
    public void agregarSpike(Spike spike) {
        // una Historia puede contener Spikes
        super.agregarSpike(spike);
    }

    @Override
    protected String obtenerTipo() {
        return HISTORIA;
    }
}
