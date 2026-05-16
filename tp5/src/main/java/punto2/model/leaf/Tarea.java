package punto2.model.leaf;

import punto2.model.component.ItemDeProyecto;
import punto2.model.composite.ItemCompuesto;

import java.time.Duration;

public class Tarea implements ItemDeProyecto {
    private static final String ERROR_DURACION_INVALIDA = "La duración no puede ser nula o negativa.";
    private static final String ERROR_DESCRIPCION_INVALIDA = "La descripción no puede ser nula o vacía.";
    private static final String ERROR_NOMBRE_INVALIDO = "El nombre no puede ser nulo o vacío.";

    private static final String PREFIJO = "[Tarea] ";
    private static final String SEPARADOR_DURACION = " — ";
    private static final String SUFIJO_HORAS = " hs";
    private static final String SEPARADOR_LINEA = System.lineSeparator();
    private static final String INDICE_TAREA = "- ";

    private String nombre;
    private String descripcion;
    private Duration duracion;

    public Tarea(String nombre, String descripcion, Duration duracion) {
        validarNombre(nombre);
        validarDescripcion(descripcion);
        validarDuracion(duracion);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    private static void validarDuracion(Duration duracion) {
        if (duracion == null || duracion.isNegative()) {
            throw new IllegalArgumentException(ERROR_DURACION_INVALIDA);
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(ERROR_DESCRIPCION_INVALIDA);
        }
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(ERROR_NOMBRE_INVALIDO);
        }
    }

    @Override
    public Duration calcularDuracion() {
        return duracion;
    }

    @Override
    public String mostrar() {
        return new StringBuilder(INDICE_TAREA)
                .append(PREFIJO)
                .append(nombre)
                .append(SEPARADOR_DURACION)
                .append(duracion.toHours())
                .append(SUFIJO_HORAS)
                .append(SEPARADOR_LINEA)
                .toString();
    }

    @Override
    public void agregarA(ItemCompuesto contenedor) {
        contenedor.agregarTarea(this);
    }

//    protected Optional<String> nombre() {
//        return Optional.ofNullable(nombre);
//    }

//    protected Optional<String> obtenerDescripcion() {
//        return Optional.ofNullable(descripcion);
//    }
}
