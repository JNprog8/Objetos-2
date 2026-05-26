package punto3.model.concreteComponent;

import punto3.model.component.Exportable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report implements Exportable {
    private static final String ERROR_FILE_NULO = "File es NULL; no puedo exportar...";
    private static final String ERROR_AL_EXPORTAR = "Error al exportar el reporte: ";

    private String contenido;

    public Report(String contenido) {
        this.contenido = contenido;
    }

    private void validarFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException(ERROR_FILE_NULO);
        }
    }

    @Override
    public void export(File file) {
        validarFile(file);
        try (var writer = new FileWriter(file)) {
            writer.write(contenido);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_AL_EXPORTAR + e.getMessage(), e);
        }
    }
}
