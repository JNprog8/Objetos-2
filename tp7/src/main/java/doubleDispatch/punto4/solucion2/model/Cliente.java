package doubleDispatch.punto4.solucion2.model;

public class Cliente {
    private String nombre;
    private Categoria categoria;

    public Cliente(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public float impuestoParaServicioDigital(float precio) {
        return categoria.impuestoServicioDigital(precio);
    }

    public float costoEnvioProductoFisico(float costo) {
        return categoria.costoEnvioProductoFisico(costo);
    }
}