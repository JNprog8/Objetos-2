package punto3.model.component;

import java.io.File;

public interface Exportable {
    void export(File file);
}
/**
 * PROXIMOS PASOS:
 * 1. Inyectar estrategia de escritura:
 * Report debería recibir un objeto que sepa cómo escribir en un archivo.
 * Esto permite cambiar el comportamiento de sobrescritura de forma más limpia
 * o mockear la escritura en tests.
 * Por ejemplo:
 * public interface Escritor {
 * void escribir(String contenido, Writer destino) throws IOException;
 * }
 */
