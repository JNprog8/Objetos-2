package ejercicio2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class LogAspect {
    private static final String SIN_PARAMETROS = "sin parametros";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final Path LOG_PATH = Path.of(System.getProperty(
            "ejercicio2.log.path",
            "log.txt"
    ));

    @Before("execution(@ejercicio2.aspect.Log * *(..))")
    public void loguearInvocacion(JoinPoint joinPoint) {
        String metodo = joinPoint.getSignature().getName();
        String parametros = formatearParametros(joinPoint.getArgs());
        String fechaHora = LocalDateTime.now().format(FORMATTER);
        String registro = String.format("\"%s\", \"%s\", \"%s\"%n", metodo, parametros, fechaHora);

        try {
            Path parent = LOG_PATH.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(LOG_PATH, registro, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo escribir el log de invocaciones", e);
        }
    }

    private String formatearParametros(Object[] argumentos) {
        if (argumentos.length == 0) {
            return SIN_PARAMETROS;
        }
        return Arrays.stream(argumentos)
                .map(String::valueOf)
                .collect(Collectors.joining("|"));
    }
}
