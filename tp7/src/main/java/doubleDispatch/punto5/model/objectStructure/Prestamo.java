package doubleDispatch.punto5.model.objectStructure;

import doubleDispatch.punto5.model.element.Articulo;
import java.time.LocalDate;

public class Prestamo {
    private final String persona;
    private final LocalDate fechaInicio;
    private final LocalDate fechaDevolucion;

    public Prestamo(String persona, Articulo articulo, LocalDate fechaInicio) {
        this.persona = persona;
        this.fechaInicio = fechaInicio;
        int dias = articulo.calcularDias();
        this.fechaDevolucion = fechaInicio.plusDays(dias);
    }

    public String persona() {
        return persona;
    }

    public LocalDate fechaInicio() {
        return fechaInicio;
    }

    public LocalDate fechaDevolucion() {
        return fechaDevolucion;
    }
}
