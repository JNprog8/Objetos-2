package inscripcion;

import models.Concurso;
import models.Participante;

import java.time.LocalDate;

public class ServicioInscripcion {

    public ServicioInscripcion() {
    }

    public void inscribir(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        concurso.inscribirA(participante, fechaInscripcion);
    }
}
