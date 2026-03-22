import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Participante {
    private static final String VALOR_PUNTOS_POSITIVO = "La cantidad de puntos debe ser un valor positivo.",
            CONCURSO_DESTINO_NO_NULO = "El concurso de destino no puede ser nulo.",
            NOMBRE_PARTICIPANTE_NO_VACIO = "El nombre del participante no puede estar vacío.",
            PARTICIPANTE_PERTENECE_CONCURSO = "El participante ya pertenece al concurso";

    private final String nombre;
    private final List<Concurso> concursos;
    private int puntos;

    public Participante(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.puntos = 0;
        this.concursos = new ArrayList<>();
    }


    public void participarEn(Concurso concurso, LocalDate fechaActual) {
        validarConcursoNoNulo(concurso);
        validarQueNoEsteInscripto(concurso);

        concurso.inscribirA(this, fechaActual);
        this.concursos.add(concurso);
    }

    public void sumarPuntos(int puntosASumar) {
        validarPuntos(puntosASumar);
        this.puntos += puntosASumar;
    }

    public int puntos() {
        return this.puntos;
    }

    public boolean estaInscripto(Concurso concurso) {
        return this.concursos.contains(concurso);
    }

    @Override
    public String toString() {
        return String.format("Participante: %s | Puntos: %d | Concursos: %d",
                nombre, puntos, concursos.size());
    }

    private void validarQueNoEsteInscripto(Concurso concurso) {
        if (estaInscripto(concurso)) {
            throw new IllegalStateException(PARTICIPANTE_PERTENECE_CONCURSO);
        }
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(NOMBRE_PARTICIPANTE_NO_VACIO);
        }
    }

    private static void validarConcursoNoNulo(Concurso concurso) {
        if (concurso == null) {
            throw new IllegalArgumentException(CONCURSO_DESTINO_NO_NULO);
        }
    }

    private static void validarPuntos(int puntos) {
        if (puntos <= 0) {
            throw new IllegalArgumentException(VALOR_PUNTOS_POSITIVO);
        }
    }
}