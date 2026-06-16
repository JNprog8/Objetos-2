package doubleDispatch.punto5.model.concreteElement;

import doubleDispatch.punto5.model.element.ArticuloDefault;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;
import java.time.LocalDate;

public class Revista extends ArticuloDefault {
    private final int paginas;
    private final LocalDate fechaPublicacion;

    public Revista(String titulo, int paginas, LocalDate fechaPublicacion, EstadoArticulo estado) {
        super(titulo, estado);
        this.paginas = paginas;
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public int calcularDias() {
        return estado().diasRevista(this);
    }

    public int paginas() {
        return paginas;
    }

    public LocalDate fechaPublicacion() {
        return fechaPublicacion;
    }
}
