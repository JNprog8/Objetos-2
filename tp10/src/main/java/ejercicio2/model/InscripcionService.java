package ejercicio2.model;

import java.util.List;

public class InscripcionService {
    private ConcursoRepository repositorioConcursos;
    private InscriptosRepository repositorioInscriptos;

    public InscripcionService(ConcursoRepository repositorioConcursos, InscriptosRepository repositorioInscriptos) {
        this.repositorioConcursos = repositorioConcursos;
        this.repositorioInscriptos = repositorioInscriptos;
    }

    private static void validarConcurso(Concurso concurso) {
        if (concurso == null) {
            throw new IllegalArgumentException("Debe elegir un concurso");
        }
    }

    public List<Concurso> concursosDisponibles() {
        return repositorioConcursos.todosLosConcursos().stream()
                .filter(Concurso::estaAbierto)
                .toList();
    }

    public void registrarNuevaInscripcion(String dni, String nombre, String apellido, String telefono, String email, Concurso concurso) {
        validarConcurso(concurso);
        Inscripto inscripto = concurso.inscribir(dni, nombre, apellido, telefono, email);
        repositorioInscriptos.guardarInscripcion(inscripto);
    }
}
