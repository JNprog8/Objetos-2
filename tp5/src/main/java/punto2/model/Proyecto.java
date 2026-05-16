package punto2.model;

import punto2.model.component.ItemDeProyecto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proyecto {
    private static final String ERROR_NOMBRE_INVALIDO = "El nombre del proyecto no puede ser nulo o vacío.";
    private static final String ERROR_ITEM_NULO = "El ítem no puede ser nulo.";

    private static final String DETALLE_DEL_PROYECTO = "===== Detalle del Proyecto =====";
    private static final String SEPARADOR = "-------------------------------------------------------------";
    private static final String DURACIÓN_TOTAL_ESTIMADA_DEL_PROYECTO = "Duración total estimada del proyecto: ";
    private static final String HORAS = " hs";

    private String nombre;
    private List<ItemDeProyecto> items = new ArrayList<>();

    public Proyecto(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(ERROR_NOMBRE_INVALIDO);
        }
    }

    public void agregarItem(ItemDeProyecto item) {
        items.add(Objects.requireNonNull(item, ERROR_ITEM_NULO));
    }

    public Duration calcularDuracion() {
        return items.stream()
                .map(ItemDeProyecto::calcularDuracion)
                .reduce(Duration.ZERO, Duration::plus);
    }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        sb.append(DETALLE_DEL_PROYECTO).append(System.lineSeparator());
        for (ItemDeProyecto item : items) {
            sb.append(item.mostrar());
        }
        sb.append(SEPARADOR).append(System.lineSeparator());
        sb.append(DURACIÓN_TOTAL_ESTIMADA_DEL_PROYECTO).append(calcularDuracion().toHours()).append(HORAS).append(System.lineSeparator());
        return sb.toString();
    }
}
