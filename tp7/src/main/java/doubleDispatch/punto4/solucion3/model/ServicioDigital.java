package doubleDispatch.punto4.solucion3.model;

public class ServicioDigital implements Producto {
    private final float precio;
    private final String nombre;
    private final float iva;

    public ServicioDigital(String nombre, float precio, float iva) {
        this.precio = precio;
        this.nombre = nombre;
        this.iva = iva;
    }

    public float precio() {
        return this.precio;
    }

    @Override
    public void accept(Cliente cliente) {
        cliente.visit(this);
    }
}
