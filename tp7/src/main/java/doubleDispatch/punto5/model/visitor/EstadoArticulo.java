package doubleDispatch.punto5.model.visitor;

import doubleDispatch.punto5.model.concreteElement.Disco;
import doubleDispatch.punto5.model.concreteElement.Libro;
import doubleDispatch.punto5.model.concreteElement.Revista;

public interface EstadoArticulo {
    int diasLibro(Libro libro);

    int diasDisco(Disco disco);

    int diasRevista(Revista revista);
}
