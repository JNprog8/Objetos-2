package doubleDispatch.punto4.solucion3.model;

public class Cliente {
    private String nombre;
    private CalculoCostosCategoriaVisitor categoriaVisitor;

    public Cliente(String nombre, CalculoCostosCategoriaVisitor categoriaVisitor) {
        this.nombre = nombre;
        this.categoriaVisitor = categoriaVisitor;
    }

    public void visit(ProductoFisico productoFisico) {
        this.categoriaVisitor.visit(productoFisico);
    }

    public void visit(ServicioDigital servicioDigital) {
        this.categoriaVisitor.visit(servicioDigital);
    }

    float totalImpuesto() {
        return categoriaVisitor.impuestos();
    }

    float totalEnvio() {
        return categoriaVisitor.envio();
    }
}
