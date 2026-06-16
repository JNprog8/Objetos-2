package observer.punto4.model.concreteSubject;

import observer.punto4.model.Concurso;
import observer.punto4.model.Participante;
import observer.punto4.model.subject.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConcursoConcreto extends Subject implements Concurso {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Participante> inscriptos;

    public ConcursoConcreto(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        super();
        this.nombre = validateNotBlank(nombre, "El nombre del concurso no puede estar vacío.");
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "La fecha de inicio es requerida.");
        this.fechaFin = Objects.requireNonNull(fechaFin, "La fecha de fin es requerida.");
        validateDates(fechaInicio, fechaFin);
        this.inscriptos = new ArrayList<>();
    }

    private String validateNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new RuntimeException(message);
        }
        return value.trim();
    }

    private void validateDates(LocalDate inicio, LocalDate fin) {
        if (fin.isBefore(inicio)) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la de inicio.");
        }
    }

    @Override
    public void inscribirA(Participante participante) {
        Objects.requireNonNull(participante, "El participante no puede ser nulo.");
        if (!estaAbierto()) {
            throw new RuntimeException("El concurso '" + nombre + "' no está abierto para inscripciones.");
        }
        this.inscriptos.add(participante);
        this.notify(participante);
    }

    public boolean estaAbierto() {
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) &&
                (hoy.isEqual(fechaFin) || hoy.isBefore(fechaFin));
    }

    public String getNombre() {
        return nombre;
    }
}
