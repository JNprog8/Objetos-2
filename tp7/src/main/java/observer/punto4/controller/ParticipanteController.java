package observer.punto4.controller;

import observer.punto4.model.Concurso;
import observer.punto4.model.Participante;

import java.util.Objects;
import java.util.regex.Pattern;

public class ParticipanteController {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private Concurso concurso;

    public ParticipanteController(Concurso concurso) {
        this.concurso = Objects.requireNonNull(concurso, "El concurso no puede ser nulo.");
    }

    public void registrarParticipante(String nombre, String telefono, String region, String email) {
        validarCampos(nombre, telefono, region, email);

        Participante participante = new Participante(nombre, telefono, region, email);
        concurso.inscribirA(participante);
    }

    private void validarCampos(String nombre, String telefono, String region, String email) {
        ensureNotEmpty(nombre, "El nombre es un campo requerido.");
        ensureNotEmpty(telefono, "El teléfono es un campo requerido.");
        ensureNotEmpty(region, "La región es un campo requerido.");
        ensureNotEmpty(email, "El correo electrónico es un campo requerido.");

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
        }
    }

    private void ensureNotEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
