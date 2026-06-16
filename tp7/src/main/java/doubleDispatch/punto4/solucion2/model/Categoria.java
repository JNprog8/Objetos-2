package doubleDispatch.punto4.solucion2.model;

public interface Categoria {
    float impuestoServicioDigital(float precio);

    float costoEnvioProductoFisico(float costo);
}