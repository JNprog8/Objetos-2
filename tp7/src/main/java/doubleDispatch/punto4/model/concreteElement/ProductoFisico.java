package doubleDispatch.punto4.model.concreteElement;

import doubleDispatch.punto4.model.element.Producto;
import doubleDispatch.punto4.model.visitor.Cliente;

public class ProductoFisico implements Producto {
    private static final float IVA = 0.21f;
    private float precio;
    private float iva;
    private String nombre;
    private int peso;

    public ProductoFisico(String nombre, float precio, int peso) {
        this.precio = precio;
        this.nombre = nombre;
        this.peso = peso;
        this.iva = IVA;
    }

    @Override
    public float precio() {
        return precio;
    }

    public float getIva() {
        return iva;
    }

    public int getPeso() {
        return peso;
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
