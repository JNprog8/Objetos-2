package inscripcion;

import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;

import java.time.LocalDate;
import java.util.List;

public class Inscripcion implements RegistrarInscripcion {
    private List<RegistrarInscripcion> registro;

    public Inscripcion(List<RegistrarInscripcion> registro) {
        validarRegistro(registro);
        this.registro = registro;
    }

    private static void validarRegistro(List<RegistrarInscripcion> registradores) {
        if (registradores == null || registradores.isEmpty()) {
            throw new IllegalArgumentException("La lista de registradores no puede ser nula o vacía.");
        }
    }

    @Override
    public void guardar(Participante participante, Concurso concurso) {
        registro.forEach(r -> r.guardar(participante, concurso));
    }
}