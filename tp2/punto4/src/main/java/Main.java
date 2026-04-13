import mail.core.*;
import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        try {
            Properties props = cargarConfiguracion();

            Notificador mailtrap = new MailtrapNotificador(
                    props.getProperty(MailConfigKeys.HOST),
                    props.getProperty(MailConfigKeys.PORT),
                    props.getProperty(MailConfigKeys.USERNAME),
                    props.getProperty(MailConfigKeys.PASSWORD),
                    props.getProperty(MailConfigKeys.FROM)
            );

            GeneradorMensaje generador = new GeneradorMensaje();
            RegistrarInscripcion notificadorEmail = new NotificarInscripcionPorMail(mailtrap, generador);

            Concurso concurso = new Concurso(1, "Concurso de ping pong", LocalDate.now(), notificadorEmail);
            Participante participante = new Participante(101, "Joaquín", 40000000L, "joaco@example.com");

            System.out.println("Realizando inscripción...");
            concurso.inscribirA(participante, LocalDate.now());

            System.out.println("Inscripción finalizada.");
            System.out.println("Puntos del participante: " + participante.puntos());

        } catch (Exception e) {
            System.err.println("Error en ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Properties cargarConfiguracion() {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo mail.properties en resources");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar la configuración de correo", ex);
        }
        return props;
    }
}