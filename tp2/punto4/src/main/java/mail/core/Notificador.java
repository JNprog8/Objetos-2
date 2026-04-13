package mail.core;

import mail.dto.Mensaje;

public interface Notificador {
    void enviar(Mensaje mensaje);
}