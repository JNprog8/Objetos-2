package strategyTest.punto2;

import org.junit.jupiter.api.Test;
import strategy.punto2.model.concreteStrategy.FormatoCorto;
import strategy.punto2.model.concreteStrategy.FormatoLargo;
import strategy.punto2.model.context.Persona;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatoFechaTest {

    @Test
    public void testFormatoCorto() {
        var persona = new Persona(LocalDate.of(1986, 6, 3), new FormatoCorto());
        assertEquals("3-06-1986", persona.fechaNacimiento());//formato corto dd-mm-aaaa
    }

    @Test
    public void testFormatoLargo() {
        var persona = new Persona(LocalDate.of(1986, 6, 3), new FormatoLargo());
        assertEquals("3 de Junio de 1986", persona.fechaNacimiento());// formato largo 'dd' de 'mmmm' de 'aaaa'
    }
}
