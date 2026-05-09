package ejercicio2.models;

import ejercicio2.services.ServicioCumpleanios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestSaludoCumpleanios {

    private FakeEnvioMail fakeNotificador;
    private FakeArchivo fakeImportador;
    private MensajeSimple mensaje;
    private RelojSistema reloj;

    @BeforeEach
    void setUp() {
        fakeNotificador = new FakeEnvioMail();
        fakeImportador = new FakeArchivo();
        mensaje = new MensajeSimple();
        reloj = new RelojSistema();
    }

    @Test
    void testEmpleadoGetters() {
        Empleado emp = new Empleado("Doe", "John", LocalDate.of(1990, 1, 1), "john@example.com");
        assertEquals("Doe", emp.apellido());
        assertEquals("John", emp.nombre());
        assertEquals(LocalDate.of(1990, 1, 1), emp.fechaNacimiento());
        assertEquals("john@example.com", emp.mail());
    }

    @Test
    void testCumpleAniosHoyTrue() {
        Empleado emp = new Empleado("Doe", "John", LocalDate.of(1990, 4, 29), "john@example.com");
        assertTrue(emp.cumpleAniosHoy(LocalDate.of(2023, 4, 29)));
    }

    @Test
    void testCumpleAniosHoyFalse() {
        Empleado emp = new Empleado("Doe", "John", LocalDate.of(1990, 4, 29), "john@example.com");
        assertFalse(emp.cumpleAniosHoy(LocalDate.of(2023, 4, 30)));
    }

    @Test
    void testCelebrarCumple() {
        Empleado emp = new Empleado("Doe", "John", LocalDate.of(1990, 4, 29), "john@example.com");
        emp.celebrarCumple(fakeNotificador, mensaje);
        List<String> envios = fakeNotificador.getEnvios();
        assertEquals(1, envios.size());
        assertEquals("john@example.com;Feliz Cumpleaños;Feliz cumpleaños, John Doe!\n", envios.get(0));
    }

    @Test
    void testMensajeSimpleGenerarPara() {
        Empleado emp = new Empleado("Doe", "John", LocalDate.of(1990, 1, 1), "john@example.com");
        String expected = "Feliz cumpleaños, John Doe!\n";
        assertEquals(expected, mensaje.generarPara(emp));
    }

    @Test
    void testRelojSistemaHoy() {
        LocalDate hoy = reloj.hoy();
        assertNotNull(hoy);
    }
}
