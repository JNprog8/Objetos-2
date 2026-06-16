package strategy.punto2.model.concreteStrategy;

import strategy.punto2.model.strategy.FormatoFecha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatoLargo implements FormatoFecha {
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "AR"));

    @Override
    public String formato(LocalDate fecha) {
        String fechaFormateada = fecha.format(FORMATO);
        return fechaFormateada.substring(0, fechaFormateada.indexOf(" de ") + 4) +
                fechaFormateada.substring(fechaFormateada.indexOf(" de ") + 4, fechaFormateada.indexOf(" de ") + 5).toUpperCase() +
                fechaFormateada.substring(fechaFormateada.indexOf(" de ") + 5);
    }
}
