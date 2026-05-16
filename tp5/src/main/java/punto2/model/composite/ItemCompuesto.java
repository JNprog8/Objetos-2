package punto2.model.composite;

import punto2.model.component.ItemDeProyecto;
import punto2.model.leaf.Tarea;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ItemCompuesto implements ItemDeProyecto {
    private static final String ERROR_NOMBRE_INVALIDO = "El nombre no puede ser nulo o vacío.";
    private static final String ERROR_ITEM_NULO = "El ítem a agregar no puede ser nulo.";

    private static final String CORCHETE_ABRE = "[";
    private static final String CORCHETE_CIERRA = "] ";
    private static final String SEPARADOR_DURACION = " — ";
    private static final String SUFIJO_DURACION = " hs totales";
    private static final String SEPARADOR_LINEA = System.lineSeparator();
    private static final int ESPACIOS_INDENTACION = 2;
    private static final String INDICE_ITEM_COMPUESTO = "+";
    private static final String INDENTATION = "";

    private String nombre;
    private List<ItemDeProyecto> items;

    public ItemCompuesto(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.items = new ArrayList<>();
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(ERROR_NOMBRE_INVALIDO);
        }
    }

    public void agregarItem(ItemDeProyecto item) {
        Objects.requireNonNull(item, ERROR_ITEM_NULO);
        item.agregarA(this);
    }

    public void agregarHistoria(HistoriaDeUsuario historia) {
        this.items.add(historia);
    }

    public void agregarSpike(Spike spike) {
        this.items.add(spike);
    }

    public void agregarTarea(Tarea tarea) {
        this.items.add(tarea);
    }

    @Override
    public Duration calcularDuracion() {
        return items.stream()
                .map(ItemDeProyecto::calcularDuracion)
                .reduce(Duration.ZERO, Duration::plus);
    }

    @Override
    public String mostrar() {
        return items.stream()
                .map(item -> item.mostrar().indent(ESPACIOS_INDENTACION))
                .collect(Collectors.joining(INDENTATION, INDICE_ITEM_COMPUESTO + CORCHETE_ABRE + obtenerTipo() + CORCHETE_CIERRA + nombre + SEPARADOR_DURACION + calcularDuracion().toHours() + SUFIJO_DURACION + SEPARADOR_LINEA, INDENTATION));
    }

    protected abstract String obtenerTipo();

//    public List<ItemDeProyecto> obtenerItems() {
//        return Collections.unmodifiableList(items);
//    }
}
