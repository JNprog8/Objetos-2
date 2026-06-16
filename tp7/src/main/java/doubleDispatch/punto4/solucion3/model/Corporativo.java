package doubleDispatch.punto4.solucion3.model;

public class Corporativo implements CalculoCostosCategoriaVisitor {
    private float impuestos;
    private float costoEnvio;

    @Override
    public void visit(ProductoFisico productoFisico) {
        this.impuestos += productoFisico.precio() + (productoFisico.precio() * productoFisico.iva());
        float base = productoFisico.precio() * productoFisico.peso();
        this.costoEnvio += base - (base * 0.5f);
    }

    @Override
    public void visit(ServicioDigital servicioDigital) {
        this.impuestos += servicioDigital.precio();
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
