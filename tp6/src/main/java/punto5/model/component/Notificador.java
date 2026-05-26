package punto5.model.component;

public interface Notificador {
    void enviar(String destinatario, String asunto, String cuerpo);
}
