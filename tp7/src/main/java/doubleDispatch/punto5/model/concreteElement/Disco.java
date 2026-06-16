package doubleDispatch.punto5.model.concreteElement;

import doubleDispatch.punto5.model.element.ArticuloDefault;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;

public class Disco extends ArticuloDefault {
    private final int anioDeBanda;

    public Disco(String titulo, int anioDeBanda, EstadoArticulo estado) {
        super(titulo, estado);
        this.anioDeBanda = anioDeBanda;
    }

    @Override
    public int calcularDias() {
        return estado().diasDisco(this);
    }

    public int anioDeBanda() {
        return anioDeBanda;
    }
}
