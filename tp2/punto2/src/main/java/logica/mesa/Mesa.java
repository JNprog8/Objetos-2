package logica.mesa;

import logica.catalogo.Producto;
import logica.facturacion.Factura;
import logica.facturacion.RegistrarFactura;
import logica.facturacion.propina.Propina;
import logica.facturacion.tarjetas.TarjetaCredito;
import logica.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private static final String ERROR_CAPACIDAD = "La capacidad debe ser mayor a cero";
    private static final String ERROR_NUMERO_MESA = "El número de mesa debe ser positivo";
    private static final String ERROR_PEDIDO_EN_CURSO = "Ya existe un pedido en curso, sin confirmar";
    private static final String ERROR_MESA_SIN_PEDIDO = "La mesa está libre, no tiene pedidos.";

    private int numero;
    private int capacidad;
    private List<RegistrarFactura> registroFacturas;
    private Pedido pedidoActual;

    public Mesa(int numero, int capacidad) {
        validarNumero(numero);
        validarCapacidad(capacidad);
        this.numero = numero;
        this.capacidad = capacidad;
        this.registroFacturas = new ArrayList<>();
        this.pedidoActual = null;
    }

    private static void validarRegistro(RegistrarFactura registro) {
        if (registro == null) {
            throw new IllegalArgumentException("El registro de factura no puede ser nulo");
        }
    }

    public void agregarRegistroFactura(RegistrarFactura registro) {
        validarRegistro(registro);
        this.registroFacturas.add(registro);
    }

    public Factura cerrarMesa(TarjetaCredito tarjeta, Propina propina) {
        validarPedido();
        confirmarPedido();

        var factura = new Factura(this.pedidoActual, tarjeta, propina);
        this.registroFacturas.forEach(factura::agregarRegistro);
        factura.emitir();

        this.pedidoActual = null;// Liberar mesa
        return factura;
    }

    public void nuevoPedido() {
        this.pedidoActual = new Pedido();
    }

    public void agregarAlPedido(Producto producto, int cantidad) {
        validarPedido();
        this.pedidoActual.agregarItem(producto, cantidad);
    }

    private void confirmarPedido() {
        validarPedido();
        this.pedidoActual.confirmar();
    }

    //metodo para preguntar por mesa, consume Restaurante
    public boolean tieneNumero(int numero) {
        return this.numero == numero;
    }

    public Pedido obtenerPedido() {
        validarPedido();
        return pedidoActual;
    }

    private void validarNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException(ERROR_NUMERO_MESA);
        }
    }

    private void validarCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(ERROR_CAPACIDAD);
        }
    }

    private void validarPedido() {
        if (this.pedidoActual == null) {
            throw new IllegalStateException(ERROR_MESA_SIN_PEDIDO);
        }
    }
}
