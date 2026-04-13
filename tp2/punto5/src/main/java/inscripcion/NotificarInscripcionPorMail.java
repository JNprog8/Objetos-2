package inscripcion;

import mail.core.GeneradorMensaje;
import mail.core.Notificador;
import mail.dto.Mensaje;
import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;

public class NotificarInscripcionPorMail implements RegistrarInscripcion {
    private final Notificador notificador;
    private final GeneradorMensaje generador;

    public NotificarInscripcionPorMail(Notificador notificador, GeneradorMensaje generador) {
        this.notificador = notificador;
        this.generador = generador;
    }

    @Override
    public void guardar(Participante participante, Concurso concurso) {
        Mensaje mensaje = generador.crearMensajeInscripcion(
                participante.obtenerEmail(),
                participante.obtenerId(),
                concurso.obtenerId());
        notificador.enviar(mensaje);
    }
}
