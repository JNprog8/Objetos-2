package doubleDispatch.punto4.solucion3.model;

public interface CalculoCostosCategoriaVisitor extends CategoriaVisitor {
    float impuestos();

    float envio();
}
