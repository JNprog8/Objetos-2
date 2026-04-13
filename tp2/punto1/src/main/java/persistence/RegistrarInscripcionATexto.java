package persistence;

import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrarInscripcionATexto implements RegistrarInscripcion {

    public static final String ERROR_AL_GUARDAR_INSCRIPCION_EN_DISCO = "Error al guardar la inscripción en disco";
    public static final String FORMATO = "HH:mm dd/MM/yyyy";// formato corto
    //public static final String FORMATO = "HH:mm dd 'de' MMMM 'de' yyyy";// formato largo
    private String path;
    private DateTimeFormatter formatoFecha;

    public RegistrarInscripcionATexto(String rutaArchivo) {
        this.path = rutaArchivo;
        this.formatoFecha = DateTimeFormatter.ofPattern(FORMATO);
    }

    private static void validarPathNoNulo(Path path) throws IOException {
        if (path.getParent() == null) {
            throw new IOException("El path no tiene un directorio válido: " + path);
        }
    }

    @Override
    public void guardar(Participante participante, Concurso concurso) {
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATO));
        String linea = String.format("%s || %d || %d%n",
                fechaHora,
                participante.obtenerId(),
                concurso.obtenerId());
        try {
            Path path = Paths.get(this.path);
            validarPathNoNulo(path);
            Files.createDirectories(path.getParent());
            Files.writeString(path, linea,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new RuntimeException(ERROR_AL_GUARDAR_INSCRIPCION_EN_DISCO, e);
        }
    }
}