package ejercicio2.main;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class MailConfig {
    // Configuración de MailTrap - Cambiar segun tus credenciales
    public static final String HOST = "sandbox.smtp.mailtrap.io";
    public static final String PORT = "port";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FROM = "sender@example.com";

    public Properties properties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        return props;
    }

    public Authenticator auth() {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
    }

    public String from() {
        return FROM;
    }
}