package punto5.model.concreteDecorator;

import punto5.model.Participante;
import punto5.model.component.Concurso;
import punto5.model.component.Notificador;
import punto5.model.decorator.ConcursoNotificadorDecorator;

public class MailtrapDecorator extends ConcursoNotificadorDecorator {
    private Notificador notificador;

    public MailtrapDecorator(Concurso inner, Notificador notificador) {
        super(inner);
        this.notificador = notificador;
    }

    @Override
    public void inscribir(Participante p) {
        inner.inscribir(p);
        enviarEmail(p);
    }

    @Override
    public String nombre() {
        return inner.nombre();
    }

    private void enviarEmail(Participante p) {
        String asunto = "Inscripción exitosa a " + nombre();
        String cuerpo = "Hola, te has inscripto correctamente al concurso: " + nombre();
        notificador.enviar(p.email(), asunto, cuerpo);
    }
}
