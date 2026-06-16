package doubleDispatch.punto4.model.visitor;

import doubleDispatch.punto4.model.element.Producto;
import doubleDispatch.punto4.model.concreteElement.ProductoFisico;
import doubleDispatch.punto4.model.concreteElement.ServicioDigital;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private List<Producto> compra;
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.compra = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.compra.add(producto);
    }

    public List<Producto> compra() {
        return List.copyOf(compra);
    }

    public abstract float calcularImpuesto(ProductoFisico producto);

    public abstract float calcularImpuesto(ServicioDigital producto);

    public abstract float calcularCostoEnvio(ProductoFisico producto);

    public abstract float calcularCostoEnvio(ServicioDigital producto);
}
