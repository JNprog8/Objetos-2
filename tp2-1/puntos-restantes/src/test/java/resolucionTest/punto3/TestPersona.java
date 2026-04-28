package resolucionTest.punto3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolucion.punto3.Persona;
import resolucion.punto3.Personas;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPersona {

    private Persona esteban;
    private Persona ana;
    private Persona enzo;
    private Personas grupo;

    @BeforeEach
    void setUp() {
        esteban = new Persona("Esteban", "Quito"); // 7 letras, empieza con E
        ana = new Persona("Ana", "García");       // 3 letras, no empieza con E (mayúscula)
        enzo = new Persona("Enzo", "Pérez");       // 4 letras, empieza con E
        grupo = new Personas();
    }

    //tests Persona

    @Test
    void testPersonaNombreEmpiezaCon() {
        // ejercitacion y verificacion
        assertTrue(esteban.nombreEmpiezaCon("E"), "Esteban debería empezar con E");
        assertFalse(ana.nombreEmpiezaCon("E"), "Ana no debería empezar con E");
    }

    @Test
    void testPersonaTieneNombrePar() {
        // ejercitacion y verificacion
        assertTrue(enzo.tieneNombrePar(), "Enzo (4 letras) debería ser par");
        assertFalse(esteban.tieneNombrePar(), "Esteban (7 letras) debería ser impar");
        assertFalse(ana.tieneNombrePar(), "Ana (3 letras) debería ser impar");
    }

    // --- Tests de Personas (Lógica de Filtrado) ---

    @Test
    void testFiltrarNombresQueEmpiezanConE() {
        // Setup
        List<Persona> listaOriginal = List.of(esteban, ana, enzo);

        // ejercitacion
        List<Persona> filtrados = grupo.nombresQueEmpiezanConE(listaOriginal);

        // verificacion
        assertEquals(2, filtrados.size(), "Deberían haber 2 personas que empiecen con E");
        assertTrue(filtrados.contains(esteban));
        assertTrue(filtrados.contains(enzo));
        assertFalse(filtrados.contains(ana));
    }

    @Test
    void testFiltrarNombresConCantidadDeLetrasPar() {
        // Setup
        List<Persona> listaOriginal = List.of(esteban, ana, enzo);

        // ejercitacion
        List<Persona> filtrados = grupo.nombresCuyaCantidadDeLetrasEsPar(listaOriginal);

        // verificacion
        assertEquals(1, filtrados.size(), "Solo Enzo debería tener nombre con longitud par");
        assertEquals(enzo, filtrados.get(0));
    }

    @Test
    void testFiltrarListaVacia() {
        // ejercitacion
        List<Persona> filtrados = grupo.nombresQueEmpiezanConE(List.of());

        // verificacion
        assertTrue(filtrados.isEmpty(), "La lista filtrada de una vacía debería estar vacía");
    }
}
