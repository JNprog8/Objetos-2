package punto4;

public class EstadoCancelada implements EstadoOrden {
    private static final String MSG_ERROR_AGREGAR_PRODUCTO = "No se pueden agregar productos en este estado";
    private static final String MSG_ERROR_ENVIAR = "La orden solo puede enviarse desde en preparacion";
    private static final String MSG_ERROR_CANCELAR = "La orden no puede cancelarse en este estado";

    private OrdenDeCompra orden;

    public EstadoCancelada(OrdenDeCompra orden) {
        this.orden = orden;
    }

    @Override
    public void agregarProducto(Producto producto) {
        throw new IllegalStateException(MSG_ERROR_AGREGAR_PRODUCTO);
    }

    @Override
    public void enviar() {
        throw new IllegalStateException(MSG_ERROR_ENVIAR);
    }

    @Override
    public void cancelar() {
        throw new IllegalStateException(MSG_ERROR_CANCELAR);
    }

    @Override
    public String toString() {
        return "Cancelada";
    }
}
