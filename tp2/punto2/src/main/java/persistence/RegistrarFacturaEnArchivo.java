package persistence;

import logica.facturacion.RegistrarFactura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarFacturaEnArchivo implements RegistrarFactura {

    private final String nombreArchivo;
    private final String directorio;

    public RegistrarFacturaEnArchivo(int numeroMesa) {
        this(numeroMesa, ".");
    }

    public RegistrarFacturaEnArchivo(int numeroMesa, String directorio) {
        this.directorio = directorio;
        this.nombreArchivo = "mesa_" + numeroMesa + ".txt";
    }

    @Override
    public void registrar(double monto) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String registro = String.format(java.util.Locale.US, "%s || %.1f%n", fecha, monto);

        try {
            Path pathDirectorio = Paths.get(directorio);
            if (!Files.exists(pathDirectorio)) {
                Files.createDirectories(pathDirectorio);
            }
            
            Files.write(pathDirectorio.resolve(nombreArchivo),
                    registro.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error al registrar la factura en el archivo: " + nombreArchivo, e);
        }
    }
}
