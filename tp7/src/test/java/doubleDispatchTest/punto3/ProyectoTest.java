package doubleDispatchTest.punto3;

import doubleDispatch.punto3.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProyectoTest {

    @Test
    public void testEpicaCostoYValidacion() {
        Epica epica = new Epica(100);
        Spike spike1 = new Spike(20);
        Spike spike2 = new Spike(30);

        epica.agregarItem(spike1);
        epica.agregarItem(spike2);

        // 100 + 20 + 30 = 150
        assertEquals(150, epica.horasTotales());
    }

    @Test
    public void testEpicaNoPermiteTareas() {
        Epica epica = new Epica(100);
        Tarea tarea = new Tarea(50);

        assertThrows(RuntimeException.class, () -> epica.agregarItem(tarea),
                "Solo spikes se permiten en una epica");
    }

    @Test
    public void testEpicaNoPermiteHistorias() {
        Epica epica = new Epica(100);
        HistoriaUsuario hu = new HistoriaUsuario(50);

        assertThrows(RuntimeException.class, () -> epica.agregarItem(hu));
    }

    @Test
    public void testEpicaNoPermiteEpicas() {
        Epica epica1 = new Epica(100);
        Epica epica2 = new Epica(100);

        assertThrows(RuntimeException.class, () -> epica1.agregarItem(epica2));
    }

    @Test
    public void testHistoriaUsuarioCostoYValidacion() {
        HistoriaUsuario hu = new HistoriaUsuario(40);
        Tarea t1 = new Tarea(10);
        Tarea t2 = new Tarea(15);

        hu.agregarItem(t1);
        hu.agregarItem(t2);

        // 40 + 10 + 15 = 65
        assertEquals(65, hu.horasTotales());
    }

    @Test
    public void testHistoriaUsuarioNoPermiteSpikes() {
        HistoriaUsuario hu = new HistoriaUsuario(40);
        Spike spike = new Spike(20);

        assertThrows(RuntimeException.class, () -> hu.agregarItem(spike),
                "Solo tareas de desarrollo se permiten en una historia de usuario");
    }

    @Test
    public void testHistoriaUsuarioNoPermiteEpicas() {
        HistoriaUsuario hu = new HistoriaUsuario(40);
        Epica epica = new Epica(100);

        assertThrows(RuntimeException.class, () -> hu.agregarItem(epica));
    }

    @Test
    public void testHistoriaUsuarioNoPermiteHistorias() {
        HistoriaUsuario hu1 = new HistoriaUsuario(40);
        HistoriaUsuario hu2 = new HistoriaUsuario(40);

        assertThrows(RuntimeException.class, () -> hu1.agregarItem(hu2));
    }

    @Test
    public void testTareaSimple() {
        Tarea tarea = new Tarea(8);
        assertEquals(8, tarea.horasTotales());
    }

    @Test
    public void testSpikeSimple() {
        Spike spike = new Spike(4);
        assertEquals(4, spike.horasTotales());
    }

    @Test
    public void testValidacionesSegunReglasDeNegocio() {
        // Tarea y Spike implementan validarEn(TareaCompleja)
        // se testea que deleguen correctamente al contenedor
        ItemDeProyecto tarea = new Tarea(10);
        ItemDeProyecto spike = new Spike(10);

        HistoriaUsuario hu = new HistoriaUsuario(0);
        Epica epica = new Epica(0);

        assertTrue(tarea.validarEn(hu));
        assertFalse(tarea.validarEn(epica));

        assertTrue(spike.validarEn(epica));
        assertFalse(spike.validarEn(hu));
    }
}
