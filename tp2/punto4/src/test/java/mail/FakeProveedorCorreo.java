package mail;

import mail.core.Notificador;
import mail.dto.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class FakeProveedorCorreo implements Notificador {
    private final List<Mensaje> mensajesEnviados = new ArrayList<>();

    @Override
    public void enviar(Mensaje mensaje) {
        mensajesEnviados.add(mensaje);
    }

    public int cantidadDeCorreosEnviados() {
        return mensajesEnviados.size();
    }

    public boolean fueEnviadoA(String destinatario) {
        return mensajesEnviados.stream().anyMatch(m -> m.destinatario().equals(destinatario));
    }

    public Mensaje ultimoMensaje() {
        if (mensajesEnviados.isEmpty()) {
            throw new IllegalStateException("No se han enviado mensajes.");
        }
        return mensajesEnviados.get(mensajesEnviados.size() - 1);
    }
}
