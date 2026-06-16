package doubleDispatch.punto4.solucion3.model;

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

    public float precio() {
        return precio;
    }

    public int peso() {
        return this.peso;
    }

    public float iva() {
        return iva;
    }

    @Override
    public void accept(Cliente cliente) {
        cliente.visit(this);
    }
}
