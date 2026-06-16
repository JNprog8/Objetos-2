package observer.punto5.persistence;

import observer.punto5.model.Observer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroArchivoVenta implements Observer {
    private String path;

    public RegistroArchivoVenta(String path) {
        this.path = path;
    }

    @Override
    public void update(double monto) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH dd/MM/yyyy");
        String registro = ahora.format(formatter) + " || " + monto + "\n";
        try {
            Files.write(Paths.get(path), registro.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo de ventas", e);
        }
    }
}
