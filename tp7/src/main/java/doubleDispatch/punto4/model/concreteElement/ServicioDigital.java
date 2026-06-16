package doubleDispatch.punto4.model.concreteElement;

import doubleDispatch.punto4.model.element.Producto;
import doubleDispatch.punto4.model.visitor.Cliente;

public class ServicioDigital implements Producto {
    private float precio;
    private String nombre;
    private float iva;

    public ServicioDigital(String nombre, float precio, float iva) {
        this.precio = precio;
        this.nombre = nombre;
        this.iva = iva;
    }

    @Override
    public float precio() {
        return this.precio;
    }

    public float getIva() {
        return iva;
    }

    @Override
    public float costoEnvio(Cliente cliente) {
        return cliente.calcularCostoEnvio(this);
    }

    @Override
    public float impuesto(Cliente cliente) {
        return cliente.calcularImpuesto(this);
    }
}
