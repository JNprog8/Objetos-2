package ejercicio1.controller;

import ejercicio1.models.Concurso;
import ejercicio1.models.Participante;

public class ParticipanteController {
    private final Concurso concurso;

    public ParticipanteController(Concurso concurso) {
        this.concurso = concurso;
    }

    public void registrarParticipante(String nombre, String telefono, String region) {
        Participante participante = new Participante(nombre, telefono, region);
        concurso.registrarParticipante(participante);
    }
}
