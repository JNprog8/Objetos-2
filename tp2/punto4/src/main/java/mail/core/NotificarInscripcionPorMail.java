package mail.core;

import mail.dto.Mensaje;
import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;

public class NotificarInscripcionPorMail implements RegistrarInscripcion {
    private final Notificador proveedor;
    private final GeneradorMensaje generador;

    public NotificarInscripcionPorMail(Notificador proveedor, GeneradorMensaje generador) {
        this.proveedor = proveedor;
        this.generador = generador;
    }

    @Override
    public void guardar(Participante participante, Concurso concurso) {
        Mensaje mensaje = generador.crearMensajeInscripcion(participante.obtenerEmail(), participante.obtenerId(), concurso.obtenerId());
        proveedor.enviar(mensaje);
    }
}