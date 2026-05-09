package ejercicio1.models;

public class Concurso {
    private RegistrarParticipante registro;

    public Concurso(RegistrarParticipante registro) {
        this.registro = registro;
    }

    public void registrarParticipante(Participante participante) {
        this.registro.guardar(participante);
    }
}
