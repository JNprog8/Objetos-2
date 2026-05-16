package punto4;

import org.junit.jupiter.api.Test;
import punto4.model.Calculador;
import punto4.model.Jubilado;
import punto4.model.LogTransaction;
import punto4.model.NoJubilado;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculador {

    @Test
    public void testJubiladoEnMesDePromocion() {
        LogTransaction log = (msg) -> {
        };
        int mesActual = LocalDate.now().getMonthValue();
        Calculador jubilado = new Jubilado(mesActual, log);

        // No debería sumar nada extra si es mes de promoción (100 + 0)
        assertEquals(100.0, jubilado.calcularPrecio(100.0), 0.001);
    }

    @Test
    public void testJubiladoFueraDeMesDePromocion() {
        LogTransaction log = (msg) -> {
        };
        int mesNoPromocion = (LocalDate.now().getMonthValue() % 12) + 1;
        Calculador jubilado = new Jubilado(mesNoPromocion, log);

        // Debería sumar 10% si NO es mes de promoción (100 + 10)
        assertEquals(110.0, jubilado.calcularPrecio(100.0), 0.001);
    }

    @Test
    public void testNoJubiladoEnMesDePromocion() {
        LogTransaction log = (msg) -> {
        };
        int mesActual = LocalDate.now().getMonthValue();
        Calculador noJubilado = new NoJubilado(mesActual, log);

        // Debería sumar 15% si ES mes de promoción (100 + 15)
        assertEquals(115.0, noJubilado.calcularPrecio(100.0), 0.001);
    }

    @Test
    public void testNoJubiladoFueraDeMesDePromocion() {
        LogTransaction log = (msg) -> {
        };
        int mesNoPromocion = (LocalDate.now().getMonthValue() % 12) + 1;
        Calculador noJubilado = new NoJubilado(mesNoPromocion, log);

        // Debería sumar 21% si NO es mes de promoción (100 + 21)
        assertEquals(121.0, noJubilado.calcularPrecio(100.0), 0.001);
    }

    @Test
    public void testVerificarLogging() {
        AtomicBoolean logged = new AtomicBoolean(false);
        LogTransaction log = (msg) -> {
            if (msg.contains("Jubilado")) logged.set(true);
        };
        Calculador jubilado = new Jubilado(LocalDate.now().getMonthValue(), log);
        jubilado.calcularPrecio(100.0);

        assertTrue(logged.get(), "Debería haber registrado la transacción con el nombre de la clase");
    }

    @Test
    public void testMesInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Jubilado(0, (msg) -> {
        }));
        assertThrows(IllegalArgumentException.class, () -> new Jubilado(13, (msg) -> {
        }));
    }

    @Test
    public void testLogNuloLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> new Jubilado(1, null));
    }

    @Test
    public void testPrecioNegativoLanzaExcepcion() {
        Calculador jubilado = new Jubilado(1, (msg) -> {
        });
        assertThrows(IllegalArgumentException.class, () -> jubilado.calcularPrecio(-1.0));
    }

    @Test
    public void testPrecioCero() {
        Calculador noJubilado = new NoJubilado(LocalDate.now().getMonthValue(), (msg) -> {
        });
        // 0 + 15% de 0 = 0
        assertEquals(0.0, noJubilado.calcularPrecio(0.0), 0.001);
    }
}
