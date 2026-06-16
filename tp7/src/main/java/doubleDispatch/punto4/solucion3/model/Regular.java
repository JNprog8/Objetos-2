package doubleDispatch.punto4.solucion3.model;

public class Regular implements CalculoCostosCategoriaVisitor {
    private float impuestos;
    private float costoEnvio;

    @Override
    public void visit(ProductoFisico productoFisico) {
        this.impuestos += productoFisico.precio() + (productoFisico.precio() * productoFisico.iva());
        this.costoEnvio += productoFisico.precio() * productoFisico.peso();
    }

    @Override
    public void visit(ServicioDigital servicioDigital) {
        this.impuestos += servicioDigital.precio() + (servicioDigital.precio() * 0.10f);
        this.costoEnvio += 0;
    }

    @Override
    public float impuestos() {
        return this.impuestos;
    }

    @Override
    public float envio() {
        return this.costoEnvio;
    }
}
