import pedidos.Pedido;

public class Mesa {
    private static final String ERROR_NUMERO_CAPACIDAD = "Número o capacidad inválidos.";
    private static final String ERROR_PEDIDO_EN_CURSO = "La mesa ya tiene un pedido en curso.";
    private static final String ERROR_MESA_SIN_PEDIDO = "La mesa está libre, no tiene pedidos.";

    private final int numero;
    private final int capacidad;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad) {
        validarNumeroYCapacidad(numero, capacidad);
        this.numero = numero;
        this.capacidad = capacidad;
    }

    private static void validarNumeroYCapacidad(int numero, int capacidad) {
        if (numero <= 0 || capacidad <= 0) {
            throw new IllegalArgumentException(ERROR_NUMERO_CAPACIDAD);
        }
    }

    public void nuevoPedido() {
        validarPedidoPrevio();
        this.pedidoActual = new Pedido();
    }

    private void validarPedidoPrevio() {
        if (this.pedidoActual != null && !this.pedidoActual.estaConfirmado()) {
            throw new IllegalStateException(ERROR_PEDIDO_EN_CURSO);
        }
    }

    public Pedido obtenerPedido() {
        validarPedidoNoNulo();
        return this.pedidoActual;
    }

    private void validarPedidoNoNulo() {
        if (this.pedidoActual == null) {
            throw new IllegalStateException(ERROR_MESA_SIN_PEDIDO);
        }
    }
}