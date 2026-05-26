package punto5.model.concreteDecorator;

import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;
import punto5.model.component.Notificador;

import java.util.List;

public class MailtrapNotificador implements Notificador {
    private String token;
    private String fromEmail;

    public MailtrapNotificador(String token, String fromEmail) {
        this.token = token;
        this.fromEmail = fromEmail;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        try {
            var config = new MailtrapConfig.Builder()
                    .token(token)
                    .build();

            var client = MailtrapClientFactory.createMailtrapClient(config);

            var mail = MailtrapMail.builder()
                    .from(new Address(fromEmail, "Sistema de Concursos"))
                    .to(List.of(new Address(destinatario)))
                    .subject(asunto)
                    .text(cuerpo)
                    .build();

            client.send(mail);
            System.out.println("Email enviado exitosamente a: " + destinatario);
        } catch (Exception e) {
            System.err.println("No se pudo enviar el email a " + destinatario + ". Error: " + e.getMessage());
        }
    }
}
