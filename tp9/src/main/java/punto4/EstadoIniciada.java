package punto4;

public class EstadoIniciada implements EstadoOrden {
    private static final String MSG_ERROR_ENVIAR = "La orden solo puede enviarse desde en preparacion";

    private OrdenDeCompra orden;

    public EstadoIniciada(OrdenDeCompra ordenDeCompra) {
        this.orden = ordenDeCompra;
    }

    @Override
    public void agregarProducto(Producto producto) {
        this.orden.nuevoEstado(new EstadoEnPreparacion(this.orden));
        this.orden.agregarProductoALista(producto);
    }

    @Override
    public void enviar() {
        throw new IllegalStateException(MSG_ERROR_ENVIAR);
    }

    @Override
    public void cancelar() {
        this.orden.nuevoEstado(new EstadoCancelada(this.orden));
    }

    @Override
    public String toString() {
        return "Iniciada";
    }
}
