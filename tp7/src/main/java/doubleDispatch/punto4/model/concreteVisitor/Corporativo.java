package doubleDispatch.punto4.model.concreteVisitor;

import doubleDispatch.punto4.model.visitor.Cliente;
import doubleDispatch.punto4.model.concreteElement.ProductoFisico;
import doubleDispatch.punto4.model.concreteElement.ServicioDigital;

public class Corporativo extends Cliente {
    public Corporativo(String nombre) {
        super(nombre);
    }

    @Override
    public float calcularImpuesto(ProductoFisico producto) {
        return producto.precio() * producto.getIva();
    }

    @Override
    public float calcularImpuesto(ServicioDigital producto) {
        return 0;
    }

    @Override
    public float calcularCostoEnvio(ProductoFisico producto) {
        return (producto.precio() * producto.getPeso()) * 0.5f;
    }

    @Override
    public float calcularCostoEnvio(ServicioDigital producto) {
        return 0;
    }
}
