package punto2.model.composite;

import java.time.Duration;

public class Spike extends ItemCompuesto {
    private static final String SPIKE = "Spike";
    private static final String ERROR_DURACION_SPIKE_INVALIDA = "La duración del Spike no puede ser negativa.";

    private static final String ERROR_DESCRIPCION_SPIKE_INVALIDA = "La descripción del Spike no puede ser vacía.";
    private static final String ERROR_SPIKE_NO_INCLUYE_HISTORIA = "Un Spike no puede contener historias de usuario.";
    private static final String ERROR_SPIKE_NO_INCLUYE_SPIKE = "Un Spike no puede contener otros Spikes.";

    private String descripcion;
    private Duration duracionPropia;

    public Spike(String nombre, String descripcion, Duration duracion) {
        super(nombre);
        validarDescripcion(descripcion);
        validarDuracion(duracion);
        this.descripcion = descripcion;
        this.duracionPropia = duracion;
    }

    private static void validarDuracion(Duration duracion) {
        if (duracion == null || duracion.isNegative()) {
            throw new IllegalArgumentException(ERROR_DURACION_SPIKE_INVALIDA);
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(ERROR_DESCRIPCION_SPIKE_INVALIDA);
        }
    }

    @Override
    public void agregarA(ItemCompuesto contenedor) {
        contenedor.agregarSpike(this);
    }

    @Override
    public void agregarHistoria(HistoriaDeUsuario historia) {
        throw new IllegalArgumentException(ERROR_SPIKE_NO_INCLUYE_HISTORIA);
    }

    @Override
    public void agregarSpike(Spike spike) {
        throw new IllegalArgumentException(ERROR_SPIKE_NO_INCLUYE_SPIKE);
    }

    @Override
    public Duration calcularDuracion() {
        return duracionPropia.plus(super.calcularDuracion());
    }

    @Override
    protected String obtenerTipo() {
        return SPIKE;
    }
}
