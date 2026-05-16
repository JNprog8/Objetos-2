package punto2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punto2.model.Proyecto;
import punto2.model.composite.HistoriaDeUsuario;
import punto2.model.composite.Spike;
import punto2.model.leaf.Tarea;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestProyectoScrum {

    private Proyecto proyecto;
    private HistoriaDeUsuario historia;
    private Spike spike;
    private Tarea tarea;

    @BeforeEach
    public void setUp() {
        proyecto = new Proyecto("Proyecto Test");
        historia = new HistoriaDeUsuario("HU Test");
        spike = new Spike("Spike Test", "Desc", Duration.ofHours(5));
        tarea = new Tarea("Tarea Test", "Desc", Duration.ofHours(2));
    }

    @Test
    public void testCalculoDuracionTareaSimple() {
        assertEquals(Duration.ofHours(2), tarea.calcularDuracion());
    }

    @Test
    public void testCalculoDuracionHistoriaConTareas() {
        historia.agregarItem(new Tarea("T1", "D1", Duration.ofHours(3)));
        historia.agregarItem(new Tarea("T2", "D2", Duration.ofHours(4)));
        assertEquals(Duration.ofHours(7), historia.calcularDuracion());
    }

    @Test
    public void testCalculoDuracionSpikeConTareas() {
        // Duración base del spike (5) + tareas (3) = 8
        spike.agregarItem(new Tarea("T1", "D1", Duration.ofHours(3)));
        assertEquals(Duration.ofHours(8), spike.calcularDuracion());
    }

    @Test
    public void testHistoriaPuedeContenerSpike() {
        spike.agregarItem(new Tarea("T1", "D1", Duration.ofHours(1))); // 5 + 1 = 6
        historia.agregarItem(tarea); // 2
        historia.agregarItem(spike); // 6
        assertEquals(Duration.ofHours(8), historia.calcularDuracion());
    }

    @Test
    public void testHistoriaNoContieneHistoria() {
        HistoriaDeUsuario huHija = new HistoriaDeUsuario("HU Hija");
        assertThrows(IllegalArgumentException.class, () -> historia.agregarItem(huHija));
    }

    @Test
    public void testSpikeNoContieneHistoria() {
        assertThrows(IllegalArgumentException.class, () -> spike.agregarItem(historia));
    }

    @Test
    public void testSpikeNoContieneSpike() {
        Spike spikeHijo = new Spike("Hijo", "Desc", Duration.ofHours(1));
        assertThrows(IllegalArgumentException.class, () -> spike.agregarItem(spikeHijo));
    }

    @Test
    public void testProyectoCalculaDuracionTotal() {
        historia.agregarItem(tarea); // 2
        proyecto.agregarItem(historia);
        proyecto.agregarItem(new Tarea("T Suelta", "D", Duration.ofHours(10)));
        assertEquals(Duration.ofHours(12), proyecto.calcularDuracion());
    }

    @Test
    public void testValidacionesConstructorTarea() {
        assertThrows(IllegalArgumentException.class, () -> new Tarea("", "D", Duration.ofHours(1)));
        assertThrows(IllegalArgumentException.class, () -> new Tarea("N", "", Duration.ofHours(1)));
        assertThrows(IllegalArgumentException.class, () -> new Tarea("N", "D", null));
        assertThrows(IllegalArgumentException.class, () -> new Tarea("N", "D", Duration.ofHours(-1)));
    }

    @Test
    public void testValidacionesConstructorSpike() {
        assertThrows(IllegalArgumentException.class, () -> new Spike("", "D", Duration.ofHours(1)));
        assertThrows(IllegalArgumentException.class, () -> new Spike("N", "", Duration.ofHours(1)));
        assertThrows(IllegalArgumentException.class, () -> new Spike("N", "D", null));
    }

    @Test
    public void testMostrarFormato() {
        String resultado = tarea.mostrar();
        assertTrue(resultado.contains("[Tarea] Tarea Test — 2 hs"));

        historia.agregarItem(tarea);
        String resHU = historia.mostrar();
        assertTrue(resHU.contains("[Historia] HU Test — 2 hs totales"));
    }
}
