package ejercicio3.main;

import ejercicio3.controller.InscripcionController;
import ejercicio3.database.JdbcAdapter;
import ejercicio3.database.TxtFileAdapter;
import ejercicio3.model.ConcursoRepository;
import ejercicio3.model.InscripcionService;
import ejercicio3.model.InscriptosRepository;
import ejercicio3.ui.VentanaPrincipal;

import javax.swing.SwingUtilities;
import java.nio.file.Path;

public class Main {
    private static final String JDBC_FLAG = "--jdbc";
    private static final Path BASE_PATH = Path.of("src/main/java/ejercicio3");
    private static final String CONCURSOS_TXT = BASE_PATH.resolve("concursos.txt").toString();
    private static final String INSCRIPTOS_TXT = BASE_PATH.resolve("inscriptos.txt").toString();
    private static final String JDBC_URL = "jdbc:derby:memory:concursos;create=true";
    private static final String JDBC_USERNAME = "";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start(args);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private void start(String[] args) {
        boolean usarJdbc = args.length > 0 && JDBC_FLAG.equalsIgnoreCase(args[0]);
        var repositorios = crearRepositorios(usarJdbc);
        var service = new InscripcionService(repositorios.concursos(), repositorios.inscriptos());
        var controller = new InscripcionController(service);
        new VentanaPrincipal(controller);
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
