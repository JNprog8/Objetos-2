package doubleDispatch.punto5.model.concreteElement;

import doubleDispatch.punto5.model.element.ArticuloDefault;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;

public class Libro extends ArticuloDefault {
    private final int paginas;

    public Libro(String titulo, int paginas, EstadoArticulo estado) {
        super(titulo, estado);
        this.paginas = paginas;
    }

    @Override
    public int calcularDias() {
        return estado().diasLibro(this);
    }

    public int paginas() {
        return paginas;
    }
}
