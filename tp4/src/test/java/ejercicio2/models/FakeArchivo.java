package ejercicio2.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FakeArchivo implements Importar {
    @Override
    public List<Empleado> importarEmpleados() {
        return Arrays.asList(
            new Empleado("Gomez", "Juan", LocalDate.of(1990, 4, 29), "juan@example.com"),
            new Empleado("Perez", "Ana", LocalDate.of(1985, 5, 10), "ana@example.com")
        );
    }
}
