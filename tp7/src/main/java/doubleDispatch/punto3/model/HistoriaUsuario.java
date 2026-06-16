package doubleDispatch.punto3.model;

public class HistoriaUsuario extends TareaCompleja {
    private static final String VALIDA_HISTORIA = "Solo tareas de desarrollo se permiten en una historia de usuario";

    public HistoriaUsuario(int horasEstimadas) {
        super(horasEstimadas);
    }

    @Override
    public boolean validarEn(TareaCompleja contenedor) {
        return contenedor.validarHistoria(this);
    }

    @Override
    public boolean validarTarea(Tarea t) {
        return true;
    }

    @Override
    public boolean validarSpike(Spike s) {
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
        return VALIDA_HISTORIA;
    }
}
