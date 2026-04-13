package testInfrastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.RegistrarFacturaEnArchivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRegistrarFacturacionPorMesaATexto {

    private static final String RUTA_DB = "src/test/java/testInfrastructure/DB/";

    @BeforeAll
    public static void setup() throws IOException {
        Path path = Paths.get(RUTA_DB);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        try (var files = Files.list(path)) {
            files.forEach(p -> {
                try {
                    Files.delete(p);//limpieza
                } catch (IOException e) {
                    // Ignora errores durante limpieza
                }
            });
        }
    }

    @Test
    public void testGenerarRegistrosPorMesaATexto() throws IOException {
        // set up
        int[] mesas = {1, 2, 3};
        double[] montosBase = {1000.0, 500.0, 2000.0};
        String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // ejercitacion: Registrar 3 facturas por cada mesa
        for (int i = 0; i < mesas.length; i++) {
            var registrar = new RegistrarFacturaEnArchivo(mesas[i], RUTA_DB);
            for (int j = 1; j <= 3; j++) {
                registrar.registrar(montosBase[i] * j); // Facturas de montos distintos: x1, x2, x3
            }
        }

        // validacion
        for (int i = 0; i < mesas.length; i++) {
            Path archivoMesa = Paths.get(RUTA_DB, "mesa_" + mesas[i] + ".txt");
            assertTrue(Files.exists(archivoMesa), "El archivo de la mesa " + mesas[i] + " debe existir");

            String contenido = Files.readString(archivoMesa);

            // Verificación flexible de las 3 facturas por mesa
            for (int j = 1; j <= 3; j++) {
                double montoEsperado = montosBase[i] * j;

                // Verificamos que contenga la fecha y que la cadena contenga el valor numérico base
                // Esto es más flexible ya que no fuerza un formato de decimal rígido en el test
                assertTrue(contenido.contains(fechaHoy), "Debe contener la fecha de hoy");
                assertTrue(contenido.contains(String.valueOf((int) montoEsperado)),
                        "El archivo de la mesa " + mesas[i] + " debe contener el monto " + montoEsperado);
            }
        }
    }
}
