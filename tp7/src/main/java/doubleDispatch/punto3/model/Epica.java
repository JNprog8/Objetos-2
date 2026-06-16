package doubleDispatch.punto3.model;

public class Epica extends TareaCompleja {
    private static final String VALIDA_EPICA = "Solo spikes se permiten en una epica";

    public Epica(int horasEstimadas) {
        super(horasEstimadas);
    }

    @Override
    public boolean validarEn(TareaCompleja contenedor) {
        return contenedor.validarEpica(this);
    }

    @Override
    public boolean validarSpike(Spike s) {
        return true;
    }

    @Override
    public boolean validarTarea(Tarea t) {
        return false;
    }

    @Override
    public boolean validarEpica(Epica e) {
        return false;
    }

    @Override
    public boolean validarHistoria(HistoriaUsuario h) {
        return false;
    }

    @Override
    protected String getErrorMessage() {
        return VALIDA_EPICA;
    }
}
