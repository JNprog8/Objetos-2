package punto5.model.concreteComponent;

import punto5.model.Participante;
import punto5.model.component.Concurso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcursoConcreto implements Concurso {

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Participante> inscriptos;

    public ConcursoConcreto(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        validarNombre(nombre);
        validarFechas(fechaInicio, fechaFin);
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inscriptos = new ArrayList<>();
    }

    @Override
    public String nombre() {
        return this.nombre;
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del Concurso NO es valido");
        }
    }

    private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas del Concurso NO son validas");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin del Concurso NO puede ser anterior a la fecha de inicio");
        }
    }

    @Override
    public void inscribir(Participante p) {
        validarParticipante(p);
        p.agregarConcurso(this);
        inscriptos.add(p);
    }

    private void validarParticipante(Participante p) {
        if (p == null) {
            throw new IllegalArgumentException("El participante del Concurso no es valido");
        }
        if (this.inscriptos.contains(p)) {
            throw new IllegalArgumentException("El participante ya esta inscripto en el Concurso");
        }
        if (LocalDate.now().isAfter(this.fechaFin)) {
            throw new IllegalStateException("No se pueden inscribir participantes en un Concurso que ya finalizó");
        }
        if (LocalDate.now().isBefore(this.fechaInicio)) {
            throw new IllegalStateException("No se pueden inscribir participantes en un Concurso que aún no comenzó");
        }
    }

}
