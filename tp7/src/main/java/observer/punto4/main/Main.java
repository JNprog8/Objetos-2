package observer.punto4.main;

import observer.punto4.controller.ParticipanteController;
import observer.punto4.db.JDBCRegistroParticipante;
import observer.punto4.model.concreteObserver.MailtrapEmailObserver;
import observer.punto4.model.concreteSubject.ConcursoConcreto;
import observer.punto4.ui.VentanaPrincipal;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utilizando el patrón Observer, modifique el ejercicio 1 del TP de layers para enviar un
 * email al participante cada vez que se inscriba. En la pantalla de inscripción deberá agregar un
 * input para que se pueda cargar el email.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String CONNECTION_STRING = "jdbc:derby:memory:ventas;create=true";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "app";

    private static final String CONFIG_FILE = "mail.properties";
    private static final String PROP_USER = "mail.username";
    private static final String PROP_PASS = "mail.password";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                setupDatabase();

                Properties config = loadConfiguration();

                var concurso = new ConcursoConcreto(
                        "Gran Concurso de Verano",
                        LocalDate.now(),
                        LocalDate.now().plusMonths(1)
                );

                concurso.attach(new JDBCRegistroParticipante(CONNECTION_STRING, DB_USER, DB_PASS));

                String mailUser = config.getProperty(PROP_USER);
                String mailPass = config.getProperty(PROP_PASS);

                if (isConfigValid(mailUser, mailPass)) {
                    concurso.attach(new MailtrapEmailObserver(mailUser, mailPass));
                    LOGGER.info("Servicio de Notificación por Email habilitado.");
                } else {
                    LOGGER.warning("El servicio de Email NO se activó. Verifique las credenciales en " + CONFIG_FILE);
                }

                var controller = new ParticipanteController(concurso);
                var view = new VentanaPrincipal(controller);

                view.mostrar();

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error crítico durante el arranque", e);
                showFatalError(e.getMessage());
            }
        });
    }

    private static Properties loadConfiguration() {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            props.load(input);
            LOGGER.info("Configuración cargada desde archivo local: " + CONFIG_FILE);
            return props;
        } catch (IOException ignored) {
        }

        try (InputStream input = Main.class.getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
                LOGGER.info("Configuración cargada desde el paquete: " + Main.class.getPackageName());
                return props;
            }
        } catch (IOException ignored) {
        }

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
                LOGGER.info("Configuración cargada desde la raíz del classpath.");
                return props;
            }
        } catch (IOException ignored) {
        }

        LOGGER.warning("No se encontró " + CONFIG_FILE + " en ninguna ubicación conocida.");
        LOGGER.warning("Asegúrese de que el archivo existe en el directorio raíz o en src/main/resources.");

        return props;
    }

    private static boolean isConfigValid(String user, String pass) {
        return user != null && !user.isBlank() && !user.contains("CODIGO") &&
                pass != null && !pass.isBlank() && !pass.contains("CONTRASEÑA");
    }

    private static void setupDatabase() {
        new SetUpDatabase(CONNECTION_STRING, DB_USER, DB_PASS).inicializar();
    }

    private static void showFatalError(String msg) {
        String fullMsg = "No se pudo iniciar la aplicación.\n\nDetalle: " + msg +
                "\n\nRecordatorio: Renombre 'mail.properties.example' a 'mail.properties' " +
                "en la raíz del proyecto y complete sus credenciales.";
        JOptionPane.showMessageDialog(null, fullMsg, "Error Crítico", JOptionPane.ERROR_MESSAGE);
    }
}
