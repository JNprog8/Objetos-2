package mesa;

import pedido.Pedido;

public class Mesa {
    private static final String ERROR_NUMERO_CAPACIDAD = "Número o capacidad inválidos.";
    private static final String ERROR_PEDIDO_EN_CURSO   = "La mesa ya tiene un pedido en curso.";
    private static final String ERROR_MESA_SIN_PEDIDO   = "La mesa está libre, no tiene pedidos.";

    private int numero;
    private int capacidad;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad) {
        validarNumeroYCapacidad(numero, capacidad);
        this.numero   = numero;
        this.capacidad = capacidad;
    }

    // Comportamiento

    public void nuevoPedido() {
        validarSinPedidoActivo();
        this.pedidoActual = new Pedido();
    }

    public Pedido obtenerPedido() {
        validarConPedido();
        return this.pedidoActual;
    }

    /**
     * metodo para preguntar por mesa, consume Restaurante
     */
    public boolean tieneNumero(int numero) {
        return this.numero == numero;
    }

    // Validaciones

    private static void validarNumeroYCapacidad(int numero, int capacidad) {
        if (numero <= 0 || capacidad <= 0) {
            throw new IllegalArgumentException(ERROR_NUMERO_CAPACIDAD);
        }
    }

    private void validarSinPedidoActivo() {
        if (this.pedidoActual != null && !this.pedidoActual.estaConfirmado()) {
            throw new IllegalStateException(ERROR_PEDIDO_EN_CURSO);
        }
    }

    private void validarConPedido() {
        if (this.pedidoActual == null) {
            throw new IllegalStateException(ERROR_MESA_SIN_PEDIDO);
        }
    }
}