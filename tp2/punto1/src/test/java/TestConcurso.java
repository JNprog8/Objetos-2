import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConcurso {
    private RegistrarInscripcion registro;
    private Participante p1;

    @BeforeEach
    void setUp() {
        // Implementación anónima, no escribe a disco
        this.registro = (participante, concurso) -> {
        };
        this.p1 = new Participante(101, "Joaquin", 42, "joaco@example.com");
    }

    @Test
    void sumaPuntosSiEsElPrimerDia() {
        var concurso = new Concurso(1, "Concurso Puntos", LocalDate.now(), registro);

        concurso.inscribirA(p1, LocalDate.now());

        assertEquals(10, p1.puntos(), "Debería tener 10 puntos por inscribirse el primer día.");
    }

    @Test
    void noSumarPuntosSiNoEsElPrimerDia() {
        var inicio = LocalDate.now().minusDays(1);
        var hoy = LocalDate.now();
        var concurso = new Concurso(1, "Concurso Puntos", inicio, registro);

        concurso.inscribirA(p1, hoy);

        assertEquals(0, p1.puntos(), "No debería tener puntos extra si no es el primer día.");
    }

    @Test
    void fallarSiElConcursoEstaCerrado() {
        var inicio = LocalDate.now().plusDays(1);
        var hoy = LocalDate.now();
        var concurso = new Concurso(1, "Concurso Cerrado", inicio, registro);

        assertThrows(IllegalStateException.class, () -> {
            concurso.inscribirA(p1, hoy);
        }, "Debería lanzar IllegalStateException porque el concurso aún no abrió.");
    }

    @Test
    void fallarSiElParticipanteYaEstaInscripto() {
        var hoy = LocalDate.now();
        var concurso = new Concurso(1, "Concurso Unico", hoy, registro);

        concurso.inscribirA(p1, hoy);

        assertThrows(IllegalStateException.class, () -> {
            concurso.inscribirA(p1, hoy);
        }, "No se puede inscribir dos veces al mismo concurso.");
    }

    @Test
    void validarExcepcionesConstructorConcurso() {
        var hoy = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso(-1, "Nombre", hoy, registro);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso(1, "", hoy, registro);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso(1, "Nombre", null, registro);
        });
    }

    @Test
    void validarConstructorConcursoConFechaFinExplicita() {
        var inicio = LocalDate.now();
        var fin = inicio.plusDays(10);
        var concurso = new Concurso(1, "Concurso Corto", inicio, fin, registro);

        concurso.inscribirA(p1, inicio.plusDays(5));
        assertEquals(0, p1.puntos());
    }

    @Test
    void fallarSiFechaInicioEsPosteriorAFin() {
        var inicio = LocalDate.now().plusDays(10);
        var fin = LocalDate.now();
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso(1, "Invalido", inicio, fin, registro);
        }, "La fecha de inicio debe ser antes de la fecha de fin.");
    }

    @Test
    void fallarSiSeSumanPuntosNegativosOCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            p1.sumarPuntos(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            p1.sumarPuntos(0);
        });
    }

    @Test
    void fallarSiElParticipanteEsNuloAlInscribir() {
        var concurso = new Concurso(1, "Concurso", LocalDate.now(), registro);
        assertThrows(IllegalArgumentException.class, () -> {
            concurso.inscribirA(null, LocalDate.now());
        });
    }
}
