package observer.punto4.model.concreteObserver;

import observer.punto4.model.Participante;
import observer.punto4.model.observer.Observer;

public class MailtrapEmailObserver implements Observer {
    private String username;
    private String password;

    public MailtrapEmailObserver(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void update(Participante participante) {
        enviarEmail(participante);
    }

    private void enviarEmail(Participante participante) {
        System.out.println("Enviando email a " + participante.getEmail() + " por la inscripción del participante: " + participante.getNombre());
    }
}
