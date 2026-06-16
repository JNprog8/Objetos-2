package doubleDispatch.punto5.model.concreteVisitor;

import doubleDispatch.punto5.model.concreteElement.Disco;
import doubleDispatch.punto5.model.concreteElement.Libro;
import doubleDispatch.punto5.model.concreteElement.Revista;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Deteriorado implements EstadoArticulo {

    @Override
    public int diasLibro(Libro libro) {
        return (int) Math.ceil(libro.paginas() / 100.0);
    }

    @Override
    public int diasDisco(Disco disco) {
        if (disco.anioDeBanda() < 1980) {
            throw new IllegalArgumentException("Disco deteriorado no se puede prestar.");
        }
        return Math.max(1, 5 - 1);
    }

    @Override
    public int diasRevista(Revista revista) {
        long anios = ChronoUnit.YEARS.between(revista.fechaPublicacion(), LocalDate.now());

        int base;
        int paginas = revista.paginas();
        if (paginas < 100) {
            base = 2;
        } else if (paginas < 2000) {
            base = 3;
        } else {
            base = 5;
        }

        int reduccion = anios > 10 ? 3 : 1;
        return Math.max(1, base - reduccion);
    }
}
