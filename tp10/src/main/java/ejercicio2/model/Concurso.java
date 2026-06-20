package ejercicio2.model;

import java.time.LocalDate;

public class Concurso {
    private static final int DAYS_TO_ADD = 30;
    private int id;
    private String nombre;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;

    public Concurso(int id, String nombre, LocalDate fechaInicioInscripcion) {
        this(id, nombre, fechaInicioInscripcion, fechaInicioInscripcion.plusDays(DAYS_TO_ADD));
    }

    public Concurso(int id, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        this.id = validarId(id);
        this.nombre = validarNoVacio(nombre, "El nombre del concurso es obligatorio");
        this.fechaInicioInscripcion = validarFecha(fechaInicioInscripcion, "La fecha de inicio es obligatoria");
        this.fechaFinInscripcion = validarFecha(fechaFinInscripcion, "La fecha de fin es obligatoria");
        validarConsistenciaFechas(fechaInicioInscripcion, fechaFinInscripcion);
    }

    public Inscripto inscribir(String dni, String nombre, String apellido, String telefono, String email) {
        if (!estaAbierto()) {
            throw new IllegalArgumentException("El concurso '" + nombre + "' no está abierto.");
        }
        return new Inscripto(dni, nombre, apellido, telefono, email, this);
    }

    public boolean estaAbierto() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicioInscripcion) && !hoy.isAfter(fechaFinInscripcion);
    }

    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }

    private String validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    private LocalDate validarFecha(LocalDate valor, String mensaje) {
        if (valor == null) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    private void validarConsistenciaFechas(LocalDate inicio, LocalDate fin) {
        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior al inicio.");
        }
    }

    private int validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser positivo.");
        }
        return id;
    }
}
