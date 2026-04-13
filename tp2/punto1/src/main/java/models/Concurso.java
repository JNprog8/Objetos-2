package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {

    private static final String FECHA_NO_NULA = "La fecha no puede ser nula.",
            ID_NO_NULA = "El Id del concurso debe ser positivo",
            NOMBRE_NO_VACIO = "El nombre no puede estar vacío.",
            PARTICIPANTE_NO_NULO = "El participante no puede ser nulo.",
            CONCURSO_CERRADO = "El concurso no acepta inscripciones en esta fecha.",
            PARTICIPANTE_YA_REGISTRADO = "El participante ya está registrado.",
            FECHA_INICIO_ANTES_DE_FECHA_FIN = "La fecha de inicio debe ser antes de la fecha de fin.";

    private static final int DURACION_CONCURSO_DIAS = 30;
    private static final int PUNTOS_PRIMER_DIA = 10;

    private int id;
    private String nombre;
    private LocalDate fechaInicioInscripcion, fechaFinInscripcion;
    private List<Participante> inscriptos;
    private RegistrarInscripcion registro;

    public Concurso(int idConcurso,
                    String nombre,
                    LocalDate fechaInicioInscripcion,
                    RegistrarInscripcion registro) {

        validarId(idConcurso);
        validarNombre(nombre);
        validarFechaInscripcion(fechaInicioInscripcion);

        this.id = idConcurso;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaInicioInscripcion.plusDays(DURACION_CONCURSO_DIAS);
        this.inscriptos = new ArrayList<>();
        this.registro = registro;
    }

    public Concurso(int idConcurso,
                    String nombre,
                    LocalDate fechaInicioInscripcion,
                    LocalDate fechaFinInscripcion,
                    RegistrarInscripcion registro) {

        validarNombre(nombre);
        validarFechaInscripcion(fechaInicioInscripcion);
        validarFechaInscripcion(fechaFinInscripcion);
        validarFechas(fechaInicioInscripcion, fechaFinInscripcion);

        this.id = idConcurso;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.inscriptos = new ArrayList<>();
        this.registro = registro;
    }

    private static void validarFechas(LocalDate fechaInico, LocalDate fechaFin) {
        if (fechaInico.isAfter(fechaFin)) {
            throw new IllegalArgumentException(FECHA_INICIO_ANTES_DE_FECHA_FIN);
        }
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(NOMBRE_NO_VACIO);
        }
    }

    private static void validarParticipanteNoNulo(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException(PARTICIPANTE_NO_NULO);
        }
    }

    private static void validarFechaInscripcion(LocalDate fechaInscripcion) {
        if (fechaInscripcion == null) {
            throw new IllegalArgumentException(FECHA_NO_NULA);
        }
    }

    public void inscribirA(Participante participante, LocalDate fechaInscripcion) {
        validarParticipanteNoNulo(participante);
        validarPeriodoInscripcionActivo(fechaInscripcion);
        validarParticipanteNoInscripto(participante);

        if (esPrimerDia(fechaInscripcion)) {
            participante.sumarPuntos(PUNTOS_PRIMER_DIA);
        }

        this.inscriptos.add(participante);
        this.registro.guardar(participante, this);
    }

    public int obtenerId() {
        return this.id;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    private void validarId(int idConcurso) {
        if (idConcurso <= 0) {
            throw new IllegalArgumentException(ID_NO_NULA);
        }
    }

    private void validarPeriodoInscripcionActivo(LocalDate fecha) {
        if (fecha.isBefore(fechaInicioInscripcion) || fecha.isAfter(fechaFinInscripcion)) {
            throw new IllegalStateException(CONCURSO_CERRADO);
        }
    }

    private void validarParticipanteNoInscripto(Participante participante) {
        if (inscriptos.contains(participante)) {
            throw new IllegalStateException(PARTICIPANTE_YA_REGISTRADO);
        }
    }

    private boolean esPrimerDia(LocalDate fecha) {
        return fecha.isEqual(fechaInicioInscripcion);
    }
}