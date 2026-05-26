package punto5.model;

import punto5.model.component.Concurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante {
    private long dni;
    private String nombre;
    private String apellido;
    private String mail;
    private List<Concurso> concursosInscriptos;

    public Participante(long dni, String nombre, String apellido, String mail) {
        this.dni = Objects.requireNonNull(dni, "El DNI no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.apellido = Objects.requireNonNull(apellido, "El apellido no puede ser nulo");
        this.mail = Objects.requireNonNull(mail, "El email no puede ser nulo");
        this.concursosInscriptos = new ArrayList<>();
    }

    public String email() {
        return this.mail;
    }

    public void agregarConcurso(Concurso c) {
        validarConcurso(c);
        this.concursosInscriptos.add(c);
    }

    private void validarConcurso(Concurso c) {
        if (c == null) {
            throw new IllegalArgumentException("El concurso del Concurso no es valido");
        }
        if (this.concursosInscriptos.contains(c)) {
            throw new IllegalArgumentException("El participante ya esta inscripto en el Concurso");
        }
    }
}
