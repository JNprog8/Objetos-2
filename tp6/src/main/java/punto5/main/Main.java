package punto5.main;

import punto5.model.Participante;
import punto5.model.component.Concurso;
import punto5.model.concreteComponent.ConcursoConcreto;
import punto5.model.concreteDecorator.MailtrapDecorator;
import punto5.model.concreteDecorator.MailtrapNotificador;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Aplique decorador al ejercicio 4 del TP 2. Es decir, el email que se envía cada vez que un
 * participante se inscribe en un concurso ahora realizelo implementado un decorador del
 * concurso.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Main app = new Main();
            var props = app.cargarConfiguracion();

            // Configuración de Mailtrap
            var token = props.getProperty("mail.token", "TU_TOKEN_AQUI");
            var from = props.getProperty("mail.from", "info@concursos.com");

            // Servicio de Email (DIP)
            var servicioEmail = new MailtrapNotificador(token, from);

            Concurso concurso = new ConcursoConcreto("Gran Concurso de Objetos 2",
                    LocalDate.now().minusDays(1),
                    LocalDate.now().plusDays(10));

            // concurso decorado
            Concurso concursoConEmail = new MailtrapDecorator(concurso, servicioEmail);

            var participante = new Participante(12345678L, "Juan", "Perez", "juan.perez@example.com");

            System.out.println("Inscribiendo al participante...");
            concursoConEmail.inscribir(participante);

            System.out.println("Proceso finalizado.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Properties cargarConfiguracion() {
        Properties props = new Properties();
        // busca el archivo dentro de la carpeta model (punto5/mail.properties)
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("punto5/mail.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo cargar el archivo en punto5/mail.properties, se usarán valores por defecto.");
        }
        return props;
    }
}
