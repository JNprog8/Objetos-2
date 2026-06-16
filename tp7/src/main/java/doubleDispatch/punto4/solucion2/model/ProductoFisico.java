package doubleDispatch.punto4.solucion2.model;

public class ProductoFisico implements Producto {
    private final float precio;
    private final float iva;
    private final String nombre;
    private final int peso;

    public ProductoFisico(String nombre, float precio, int peso) {
        this.precio = precio;
        this.nombre = nombre;
        this.peso = peso;
        this.iva = 0.21f;
    }

    @Override
    public float precio() {
        return precio;
    }

    @Override
    public float costoEnvio(Cliente cliente) {
        return cliente.costoEnvioProductoFisico(this.precio * peso);
    }

    @Override
    public float impuesto(Cliente cliente) {
        return this.precio * iva;
    }
}
