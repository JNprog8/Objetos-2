package ejercicio2.models;

import java.util.ArrayList;
import java.util.List;

public class FakeEnvioMail implements Notificador {
    private List<String> envios = new ArrayList<>();

    @Override
    public void enviar(String destino, String asunto, String mensaje) {
        envios.add(destino + ";" + asunto + ";" + mensaje);
    }

    public List<String> getEnvios() {
        return envios;
    }

    public void clear() {
        envios.clear();
    }
}
