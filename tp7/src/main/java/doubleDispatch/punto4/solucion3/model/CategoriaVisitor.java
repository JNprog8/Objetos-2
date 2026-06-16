package doubleDispatch.punto4.solucion3.model;

public interface CategoriaVisitor {
    void visit(ProductoFisico productoFisico);

    void visit(ServicioDigital servicioDigital);
}
