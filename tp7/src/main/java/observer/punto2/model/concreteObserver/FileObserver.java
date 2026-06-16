package observer.punto2.model.concreteObserver;

import observer.punto2.model.observer.Observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileObserver implements Observer {
    private static final String DATE_SHORT_FORMAT = "dd/MM/yyyy";
    private String fileName;
    private String path;

    public FileObserver(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    @Override
    public void update(String temperature) {
        var file = new File(path, fileName);
        var fecha = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_SHORT_FORMAT));
        String datos = "Temperatura: " + temperature + " °C" + " - Fecha: " + fecha;

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(datos + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de log", e);
        }
    }
}
