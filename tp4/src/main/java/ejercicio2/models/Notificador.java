package ejercicio2.models;

public interface Notificador {
    void enviar(String destino, String asunto, String mensaje);
}
