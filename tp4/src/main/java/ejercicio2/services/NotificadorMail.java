package ejercicio2.services;

import ejercicio2.main.MailConfig;
import ejercicio2.models.Notificador;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificadorMail implements Notificador {
    private final MailConfig config;

    public NotificadorMail(MailConfig config) {
        this.config = config;
    }

    @Override
    public void enviar(String destino, String asunto, String mensajeTexto) {
        try {
            Session session = Session.getInstance(config.properties(), config.auth());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.from()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            message.setSubject(asunto);
            message.setText(mensajeTexto);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
