package doubleDispatch.punto4.solucion2.model;

public interface Producto {

    float precio();

    float costoEnvio(Cliente cliente);

    float impuesto(Cliente cliente);
}
