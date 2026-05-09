package ejercicio2.models;

import java.time.LocalDate;

public class RelojSistema implements Reloj {
    public LocalDate hoy() {
        return LocalDate.now();
    }
}
