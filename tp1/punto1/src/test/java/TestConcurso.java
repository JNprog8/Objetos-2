import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestConcurso {

    // CASO 1: Un participante se inscribe en un concurso (no el primer día).
    @Test
    void testInscripcionNormal() {
        // Set Up
        var fechaInicio = LocalDate.now();
        var concurso = new Concurso("Hackathon Java", fechaInicio);
        var participante = new Participante("James Gosling");

        var fechaInscripcion = fechaInicio.plusDays(5);

        // Ejercitación
        participante.participarEn(concurso, fechaInscripcion);

        // Verificación
        assertTrue(participante.estaInscripto(concurso), "El participante debería estar inscripto");
        assertEquals(0, participante.puntos(), "No debería sumar puntos por inscribirse después del primer día");
    }

    // CASO 2: Participante se inscribe en concurso el primer día de la inscripción.
    @Test
    void testInscripcionPrimerDia() {
        // Set Up
        var fechaInicio = LocalDate.now();
        var concurso = new Concurso("Patrones GoF", fechaInicio);
        var participante = new Participante("Kent Beck");

        var fechaInscripcion = fechaInicio;

        // Ejercitación
        participante.participarEn(concurso, fechaInscripcion);

        // Verificación
        assertTrue(participante.estaInscripto(concurso), "Debe quedar inscripto");
        assertEquals(10, participante.puntos(), "Debe ganar 10 puntos por inscripción temprana");
    }

    // CASO 3: Participante intenta inscribirse fuera del rango de inscripción.
    @Test
    void testInscripcionFueraDeRango() {
        // Set Up
        var fechaInicio = LocalDate.now();
        var concurso = new Concurso("Refactoring Challenge", fechaInicio);
        var participante = new Participante("Martin Fowler");

        var fechaInscripcionTarde = fechaInicio.plusDays(31); // el concurso dura 30

        // Ejercitación
        IllegalStateException excepcion =
                assertThrows(IllegalStateException.class, () -> {
                    participante.participarEn(concurso, fechaInscripcionTarde);
                });

        // Verificación
        assertEquals("El concurso no acepta inscripciones en esta fecha.", excepcion.getMessage());
        assertFalse(participante.estaInscripto(concurso), "No debe haber quedado inscripto en la lista");
    }

    // TESTS EXTRA DE DE COBERTURA

    @Test
    void testConcursoConNombreVacio() {
        IllegalArgumentException excepcion =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Concurso("", LocalDate.now());
                });
        assertEquals("El nombre no puede estar vacío.", excepcion.getMessage());
    }

    @Test
    void testConcursoConFechaFinAnteriorAInicio() {
        // Set Up
        var inicio = LocalDate.now();
        var fin = inicio.minusDays(5); // Fecha fin en pasado

        // Ejercitación
        IllegalArgumentException excepcion =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Concurso("Torneo Erróneo", inicio, fin);
                });
        // Verificación
        assertEquals("La fecha de inicio debe ser antes de la fecha de fin.", excepcion.getMessage());
    }

    @Test
    void testParticipanteDobleInscripcionAlMismoConcurso() {
        // Set Up
        var fechaInicio = LocalDate.now();
        var concurso = new Concurso("Torneo Java", fechaInicio);
        var participante = new Participante("Ada Lovelace");

        // Se inscribe la primera vez exitosamente
        participante.participarEn(concurso, fechaInicio);

        // Ejercitación (segunda vez)
        IllegalStateException excepcion =
                assertThrows(IllegalStateException.class, () -> {
                    participante.participarEn(concurso, fechaInicio.plusDays(1));
                });
        // Verificación
        assertEquals("El participante ya pertenece al concurso", excepcion.getMessage());
    }

    @Test
    void testParticipanteConPuntosNegativos() {
        // Set Up
        var participante = new Participante("Alan Turing");

        // Ejercitación
        IllegalArgumentException excepcion =
                assertThrows(IllegalArgumentException.class, () -> {
                    participante.sumarPuntos(-5);
                });

        // Verificación
        assertEquals("La cantidad de puntos debe ser un valor positivo.", excepcion.getMessage());
    }
}