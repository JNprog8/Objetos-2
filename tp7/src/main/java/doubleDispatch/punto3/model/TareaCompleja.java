package doubleDispatch.punto3.model;

import java.util.ArrayList;
import java.util.List;

public abstract class TareaCompleja implements ItemDeProyecto {
    private List<ItemDeProyecto> items;
    private int horasEstimadas;

    public TareaCompleja(int horasEstimadas) {
        this.items = new ArrayList<>();
        this.horasEstimadas = horasEstimadas;
    }

    public void agregarItem(ItemDeProyecto item) {
        if (item.validarEn(this)) {
            this.items.add(item);
        } else {
            throw new RuntimeException(this.getErrorMessage());
        }
    }

    @Override
    public int horasTotales() {
        return this.horasEstimadas + items.stream().mapToInt(ItemDeProyecto::horasTotales).sum();
    }

    // Métodos de despacho para todos los tipos posibles
    public abstract boolean validarTarea(Tarea t);

    public abstract boolean validarSpike(Spike s);

    public abstract boolean validarEpica(Epica e);

    public abstract boolean validarHistoria(HistoriaUsuario h);

    protected abstract String getErrorMessage();
}
