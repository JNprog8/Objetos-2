package doubleDispatch.punto5.model.element;

import doubleDispatch.punto5.model.visitor.EstadoArticulo;

public abstract class ArticuloDefault implements Articulo {

    private final String titulo;
    private final EstadoArticulo estado;

    protected ArticuloDefault(String titulo, EstadoArticulo estado) {
        this.titulo = titulo;
        this.estado = estado;
    }

    protected EstadoArticulo estado() {
        return estado;
    }

    protected String titulo() {
        return titulo;
    }

    @Override
    public abstract int calcularDias();

}
