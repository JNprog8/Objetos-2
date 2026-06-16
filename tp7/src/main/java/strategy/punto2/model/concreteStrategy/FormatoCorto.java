package strategy.punto2.model.concreteStrategy;

import strategy.punto2.model.strategy.FormatoFecha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatoCorto implements FormatoFecha {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("d-MM-yyyy");

    @Override
    public String formato(LocalDate fecha) {
        return fecha.format(FORMATO);
    }
}
