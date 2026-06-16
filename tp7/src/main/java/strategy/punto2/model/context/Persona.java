package strategy.punto2.model.context;

import strategy.punto2.model.strategy.FormatoFecha;

import java.time.LocalDate;

public class Persona {
    private final LocalDate nacimiento;
    private final FormatoFecha formatoStrategy;

    public Persona(LocalDate nacimiento, FormatoFecha formatoStrategy) {
        this.nacimiento = nacimiento;
        this.formatoStrategy = formatoStrategy;
    }

    public String fechaNacimiento() {
        return formatoStrategy.formato(nacimiento);
    }
}
