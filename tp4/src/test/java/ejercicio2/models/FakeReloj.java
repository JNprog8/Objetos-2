package ejercicio2.models;

import java.time.LocalDate;

public class FakeReloj implements Reloj {
    private LocalDate fecha;

    public FakeReloj(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public LocalDate hoy() {
        return fecha;
    }
}
