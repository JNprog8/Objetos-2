package ejercicio2.main;

import ejercicio2.controller.InscripcionController;
import ejercicio2.database.JdbcAdapter;
import ejercicio2.database.TxtFileAdapter;
import ejercicio2.model.Concurso;
import ejercicio2.model.ConcursoRepository;
import ejercicio2.model.InscripcionService;
import ejercicio2.model.InscriptosRepository;
import ejercicio2.ui.VentanaPrincipal;

import javax.swing.*;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.util.List;

// para ejecutar 'mvn compile y mvn exec:java'
public class Main {
    private static final String GUI_FLAG = "--gui";
    private static final String JDBC_FLAG = "--jdbc";
    private static final Path BASE_PATH = Path.of("src/main/java/ejercicio2");
    private static final String CONCURSOS_TXT = BASE_PATH.resolve("concursos.txt").toString();
    private static final String INSCRIPTOS_TXT = BASE_PATH.resolve("inscriptos.txt").toString();
    private static final Path LOG_TXT = Path.of("log.txt");
    private static final String JDBC_URL = "jdbc:derby:memory:concursos;create=true";
    private static final String JDBC_USERNAME = "";
    private static final String JDBC_PASSWORD = "";

    private InscripcionController controller;
    private boolean derbyApagado = false;

    public static void main(String[] args) {
        new Main().start(args);
    }

    private void start(String[] args) {
        boolean usarJdbc = contieneArgumento(args, JDBC_FLAG);
        var repositorios = crearRepositorios(usarJdbc);
        var service = new InscripcionService(repositorios.concursos(), repositorios.inscriptos());
        controller = new InscripcionController(service);

        if (usarJdbc) {
            Runtime.getRuntime().addShutdownHook(new Thread(this::apagarDerby));
        }

        if (contieneArgumento(args, GUI_FLAG)) {
            mostrarVentana(args);
            return;
        }

        try {
            ejecutarDemo(repositorios.concursos());
        } finally {
            if (usarJdbc) {
                apagarDerby();
            }
        }
    }

    private synchronized void apagarDerby() {
        if (derbyApagado) {
            return;
        }
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (java.sql.SQLException e) {
            // El SQLState 'XJ015' indica que el sistema de Derby se apago
            if ("XJ015".equals(e.getSQLState())) {
                System.out.println("Base de datos Derby en memoria apagada correctamente.");
            } else {
                System.err.println("Error al apagar Derby: " + e.getMessage());
            }
        } finally {
            derbyApagado = true;
        }
    }

    private void mostrarVentana(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new VentanaPrincipal(controller);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private void ejecutarDemo(ConcursoRepository repositorioConcursos) {
        reiniciarLog();
        List<Concurso> concursos = repositorioConcursos.todosLosConcursos().stream()
                .filter(Concurso::estaAbierto)
                .toList();
        if (concursos.isEmpty()) {
            throw new IllegalStateException("No hay concursos disponibles para ejecutar la demo");
        }

        Concurso concurso = concursos.get(0);
        controller.saveInscription("12345678", "Grace", "Hopper", "2920-505050", "grace@radio.com", concurso);
        controller.saveInscription("87654321", "Alan", "Turing", "2920-606060", "alan@radio.com", concurso);
        controller.todosLosConcursos();
    }

    private void reiniciarLog() {
        try {
            Files.deleteIfExists(LOG_TXT);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo reiniciar el archivo de log", e);
        }
    }

    private boolean contieneArgumento(String[] args, String esperado) {
        for (String arg : args) {
            if (esperado.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    private Repositorios crearRepositorios(boolean usarJdbc) {
        if (usarJdbc) {
            new SetUpDatabase(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD).inicializar();
            var adapter = new JdbcAdapter(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            return new Repositorios(adapter, adapter);
        }
        var adapter = new TxtFileAdapter(CONCURSOS_TXT, INSCRIPTOS_TXT);
        return new Repositorios(adapter, adapter);
    }

    private record Repositorios(ConcursoRepository concursos, InscriptosRepository inscriptos) {
    }
}
