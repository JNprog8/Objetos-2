package doubleDispatch.punto3.solucion2.model;

public class Tarea implements ItemDeProyecto {
    public static final String VALIDA_TAREA_SIMPLE = "No puede crear una Epica o HS como tarea simple";
    private int horasEstimadas;
    private TipoTarea tipoTarea;

    public Tarea(int horasEstimadas, TipoTarea tipoTarea) {
//        if (tipoTarea.equals(TipoTarea.EPICA) || tipoTarea.equals(TipoTarea.HISTORIA_USUARIO)) {
//            throw new RuntimeException(VALIDA_TAREA_SIMPLE);
//        }
        if (!tipoTarea.puedeSerSimple()) {
            throw new RuntimeException(VALIDA_TAREA_SIMPLE);
        }
        this.horasEstimadas = horasEstimadas;
        this.tipoTarea = tipoTarea;
    }

    @Override
    public int horasTotales() {
        return this.horasEstimadas;
    }

    @Override
    public TipoTarea tipoTarea() {
        return tipoTarea;
    }
}
