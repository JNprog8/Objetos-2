package punto4;

public class EstadoEnPreparacion implements EstadoOrden {
    private static final String MSG_ERROR_ENVIAR = "La orden solo puede enviarse desde en preparacion";

    private OrdenDeCompra orden;

    public EstadoEnPreparacion(OrdenDeCompra orden) {
        this.orden = orden;
    }

    @Override
    public void agregarProducto(Producto producto) {
        this.orden.agregarProductoALista(producto);
    }

    @Override
    public void enviar() {
        this.orden.nuevoEstado(new EstadoEnviada(this.orden));
    }

    @Override
    public void cancelar() {
        this.orden.nuevoEstado(new EstadoCancelada(this.orden));
    }

    @Override
    public String toString() {
        return "En Preparacion";
    }
}
