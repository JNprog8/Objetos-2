package mail.core;

import mail.dto.Mensaje;

public class GeneradorMensaje {

    public Mensaje crearMensajeInscripcion(String emailParticipante, int idParticipante, int idConcurso) {
        String asunto = "¡Inscripción confirmada!";
        String cuerpo = String.format("Hola %d,\n\nTu inscripción al concurso '%d' fue un éxito.\n¡Mucha suerte!",
                idParticipante, idConcurso);

        return new Mensaje(emailParticipante, asunto, cuerpo);
    }

    public Mensaje crearMensajeFacturacionRestaurante(String emailRestaurante, int numeroMesa, double montoTotal) {
        String asunto = "Notificación de Facturación - Mesa #" + numeroMesa;
        String cuerpo = String.format("Se ha cerrado la cuenta de la mesa %d.\nMonto total facturado: $%.2f",
                numeroMesa, montoTotal);

        return new Mensaje(emailRestaurante, asunto, cuerpo);
    }
}
