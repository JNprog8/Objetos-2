package observer.punto3.model.concreteObserver;

import observer.punto3.model.observer.Observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileObserver implements Observer {
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private String fileName;
    private String path;

    public FileObserver(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    @Override
    public void update(String temperature) {
        var file = new File(path, fileName);
        var fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String registro = String.format("Fecha: %s - Temperatura: %s °C", fechaHora, temperature);

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(registro + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de log", e);
        }
    }
}
