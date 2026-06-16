package doubleDispatch.punto4.solucion2.model;

public class ServicioDigital implements Producto {
    private final float precio;
    private final String nombre;
    private final float iva;

    public ServicioDigital(String nombre, float precio, float iva) {
        this.precio = precio;
        this.nombre = nombre;
        this.iva = iva;
    }

    @Override
    public float precio() {
        return this.precio;
    }

    @Override
    public float costoEnvio(Cliente cliente) {
        return 0;
    }

    @Override
    public float impuesto(Cliente cliente) {
        return cliente.impuestoParaServicioDigital(this.precio);
    }
}
