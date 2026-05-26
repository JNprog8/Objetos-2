package punto5.model.decorator;

import punto5.model.component.Concurso;

public abstract class ConcursoNotificadorDecorator implements Concurso {
    protected Concurso inner;

    protected ConcursoNotificadorDecorator(Concurso inner) {
        this.inner = inner;
    }

}
