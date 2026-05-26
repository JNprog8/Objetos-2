package punto5.model.component;

import punto5.model.Participante;

public interface Concurso {
    void inscribir(Participante p);

    String nombre();
}
