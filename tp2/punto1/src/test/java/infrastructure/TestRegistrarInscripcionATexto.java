package infrastructure;

import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;
import org.junit.jupiter.api.*;
import persistence.RegistrarInscripcionATexto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRegistrarInscripcionATexto {

    private static final String TEST_PATH = "src/test/java/infrastructure/DB/";
    private static int testCounter = 0;
    private String currentTestFile;
    private RegistrarInscripcion registro;

    @BeforeEach
    void setUp() throws IOException {
        testCounter++;
        currentTestFile = TEST_PATH + "textInscripcion_" + testCounter + ".txt";
        
        Path path = Paths.get(currentTestFile);
        Files.deleteIfExists(path);
        Files.createDirectories(Paths.get(TEST_PATH));
        this.registro = new RegistrarInscripcionATexto(currentTestFile);
    }

    @Test
    @Order(1) // -> textInscripcion_1.txt
    void guardarInscripcionUnParticipanteEnMismoConcurso() throws IOException {
        // Set Up
        var fecha = LocalDate.now();
        var participante = new Participante(105, "Test", 12345678L, "test@example.com");
        var concurso = new Concurso(10, "Concurso Test", fecha, registro);

        // Ejercitación
        concurso.inscribirA(participante, fecha);

        // Verificación
        Path path = Paths.get(currentTestFile);
        assertTrue(Files.exists(path), "El archivo no fue creado.");
        List<String> lineas = Files.readAllLines(path);
        assertEquals(1, lineas.size());
        assertTrue(lineas.get(0).contains("105 || 10"));
    }

    @Test
    @Order(2) // -> textInscripcion_2.txt
    void guardarInscripcionVariosParticipantesEnMismoConcurso() throws IOException {
        // Setup
        var hoy = LocalDate.now();
        var concurso = new Concurso(1, "Concurso de Arte", hoy, registro);

        var p1 = new Participante(101, "Joaquin", 42, "j@e.com");
        var p2 = new Participante(102, "Sofia", 33, "s@e.com");
        var p3 = new Participante(103, "Carlos", 22, "c@e.com");

        // Ejercitación
        concurso.inscribirA(p1, hoy);
        concurso.inscribirA(p2, hoy);
        concurso.inscribirA(p3, hoy);

        // Verificación
        Path path = Paths.get(currentTestFile);
        List<String> lineas = Files.readAllLines(path);
        assertEquals(3, lineas.size());
    }

    @Test
    @Order(3) // -> textInscripcion_3.txt
    void guardarInscripcionUnParticipanteEnMuchosConcursos() throws IOException {
        // Setup
        var hoy = LocalDate.now();
        var p1 = new Participante(101, "Joaquin", 42, "j@e.com");
        var c1 = new Concurso(1, "Concurso 1", hoy, registro);
        var c2 = new Concurso(2, "Concurso 2", hoy, registro);

        // Ejercitación
        c1.inscribirA(p1, hoy);
        c2.inscribirA(p1, hoy);

        // Verificación
        List<String> lineas = Files.readAllLines(Paths.get(currentTestFile));
        assertEquals(2, lineas.size());
    }

    @Test
    @Order(4) // -> textInscripcion_4.txt
    void guardarInscripcionMuchosParticipantesEnMuchosConcursos() throws IOException {
        // Setup
        var hoy = LocalDate.now();
        var p1 = new Participante(101, "Joaquin", 42, "j@e.com");
        var p2 = new Participante(102, "Oriana", 33, "o@e.com");
        var c1 = new Concurso(1, "Concurso 1", hoy, registro);
        var c2 = new Concurso(2, "Concurso 2", hoy, registro);

        // Ejercitación
        c1.inscribirA(p1, hoy);
        c1.inscribirA(p2, hoy);
        c2.inscribirA(p1, hoy);
        c2.inscribirA(p2, hoy);

        // Verificación
        List<String> lineas = Files.readAllLines(Paths.get(currentTestFile));
        assertEquals(4, lineas.size());
    }
}
