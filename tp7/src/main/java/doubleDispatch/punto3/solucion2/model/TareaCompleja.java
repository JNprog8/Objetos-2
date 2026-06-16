package doubleDispatch.punto3.solucion2.model;

import java.util.List;

public class TareaCompleja implements ItemDeProyecto {
    public static final String VALIDA_HISTORIA = "Solo tareas de desarrollo se permiten en una historia de usuario";
    public static final String VALIDA_EPICA = "Solo spikes se permiten en una epica";
    public static final String VALIDA_TAREA_COMPLEJA = "No puede crear TS o Spike como Tarea Compleja";
    public static final String VALIDA_TC = "No se puede agregar el item a la tarea compleja";
    private List<ItemDeProyecto> items;
    private int horasEstimadas;
    private TipoTarea tipoTarea;

    public TareaCompleja(int horasEstimadas, TipoTarea tipoTarea) {
        if (!tipoTarea.puedeSerCompleja()) {
            throw new RuntimeException(VALIDA_TAREA_COMPLEJA);
        }
        this.horasEstimadas = horasEstimadas;
        this.tipoTarea = tipoTarea;
    }

    public void agregarItem(ItemDeProyecto item) {
        //Una historia de usuario solo puede tener tareas de desarrollo
        //Una Epica solo puede tener Apikes
        if (!this.tipoTarea.puedeContenerA(item.tipoTarea())) {
            throw new RuntimeException(VALIDA_TC);
        }
//        if (this.tipoTarea.equals(TipoTarea.HISTORIA_USUARIO)
//                && !item.tipoTarea().equals(TipoTarea.TAREA_DESARROLLO)) {
//            throw new RuntimeException(VALIDA_HISTORIA);
//        }
//        if (this.tipoTarea.equals(TipoTarea.EPICA)
//                && !item.tipoTarea().equals(TipoTarea.SPIKE)) {
//            throw new RuntimeException(VALIDA_EPICA);
//        }

        this.items.add(item);
    }


    @Override
    public int horasTotales() {
        int horasTotales = this.horasEstimadas;
        for (ItemDeProyecto item : items) {
            horasTotales += item.horasTotales();
        }
        return horasTotales;
    }

    @Override
    public TipoTarea tipoTarea() {
        return this.tipoTarea;
    }
}
