package mail.core;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import mail.dto.Mensaje;

import java.util.Properties;

public class MailtrapNotificador implements Notificador {

    private final Session session;
    private final String from;

    public MailtrapNotificador(String host, String port, String username, String password, String from) {
        this.from = from;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @Override
    public void enviar(Mensaje mensajeCorreo) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mensajeCorreo.destinatario()));
            message.setSubject(mensajeCorreo.asunto());
            message.setText(mensajeCorreo.cuerpo());

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error fatal al enviar correo electrónico: " + e.getMessage(), e);
        }
    }
}