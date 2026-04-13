package models;

public class Participante {
    private static final String VALOR_PUNTOS_POSITIVO = "La cantidad de puntos debe ser un valor positivo.",
            VALOR_ID = "El ID destino debe ser positivo.",
            VALOR_DNI = "El DNI debe ser positivo.",
            NOMBRE_PARTICIPANTE_NO_VACIO = "El nombre del participante no puede estar vacío.",
            EMAIL_PARTICIPANTE_NO_VACIO = "El email del participante no puede estar vacío.";

    private final int id;
    private final long dni;
    private final String nombre;
    private final String email;
    private int puntos;

    public Participante(int idParticipante, String nombreParticipante, long dniParticipante, String email) {
        validarId(idParticipante);
        validarNombre(nombreParticipante);
        validarEmail(email);
        validarDni(dniParticipante);

        this.id = idParticipante;
        this.nombre = nombreParticipante;
        this.dni = dniParticipante;
        this.email = email;
        this.puntos = 0;
    }

    public void sumarPuntos(int puntosASumar) {
        validarPuntos(puntosASumar);
        this.puntos += puntosASumar;
    }

    public int puntos() {
        return this.puntos;
    }

    public int obtenerId() {
        return this.id;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public String obtenerEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return String.format("Participante: %s (%s) | Puntos: %d",
                nombre, email, puntos);
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(NOMBRE_PARTICIPANTE_NO_VACIO);
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException(EMAIL_PARTICIPANTE_NO_VACIO);
        }
    }

    private void validarPuntos(int puntos) {
        if (puntos <= 0) {
            throw new IllegalArgumentException(VALOR_PUNTOS_POSITIVO);
        }
    }

    private void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException(VALOR_ID);
        }
    }

    private void validarDni(long dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException(VALOR_DNI);
        }
    }
}