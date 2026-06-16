package doubleDispatch.punto5.model.concreteVisitor;

import doubleDispatch.punto5.model.concreteElement.Disco;
import doubleDispatch.punto5.model.concreteElement.Libro;
import doubleDispatch.punto5.model.concreteElement.Revista;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;

public class Nuevo implements EstadoArticulo {

    @Override
    public int diasLibro(Libro libro) {
        return (int) Math.ceil(libro.paginas() / 100.0);
    }

    @Override
    public int diasDisco(Disco disco) {
        return disco.anioDeBanda() < 1980 ? 3 : 5;
    }

    @Override
    public int diasRevista(Revista revista) {
        int paginas = revista.paginas();
        if (paginas < 100) {
            return 2;
        }
        if (paginas < 2000) {
            return 3;
        }
        return 5;
    }
}
