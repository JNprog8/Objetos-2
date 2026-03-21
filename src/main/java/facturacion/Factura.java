package facturacion;

import facturacion.tarjetas.TarjetaCredito;
import pedidos.Pedido;

// Responsabilidad: Aplicar matemáticas de cobro.
public class Factura {
    private final Pedido pedido;
    private final TarjetaCredito tarjeta;
    private final Propina propina;

    public Factura(Pedido pedido, TarjetaCredito tarjeta, Propina propina) {
        validarPedidoNoNulo(pedido);
        validarPedidoConfirmado(pedido);
        validarTarjeta(tarjeta);
        validarPropinaSeleccionada(propina);

        this.pedido = pedido;
        this.tarjeta = tarjeta;
        this.propina = propina;
    }

    private static void validarPedidoConfirmado(Pedido pedido) {
        if (!pedido.estaConfirmado()) {
            throw new IllegalStateException("No se puede facturar un pedido que no ha sido confirmado.");
        }
    }

    private static void validarTarjeta(TarjetaCredito tarjeta) {
        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta no puede ser nula.");
        }
    }

    private static void validarPropinaSeleccionada(Propina propina) {
        if (propina == null) {
            throw new IllegalArgumentException("Debe seleccionar una propina.");
        }
    }

    private void validarPedidoNoNulo(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
    }

    public double calcularTotal() {
        double subtotalBruto = this.pedido.calcularSubtotalGeneral();
        double descuento = this.tarjeta.calcularDescuento(this.pedido);

        // Las propinas se calculan sobre el "subtotal bruto" (subTotal sin descuento),
        // no perjudica la/s promociones del banco.
        double montoPropina = this.propina.calcularMonto(subtotalBruto);
        double totalConDescuento = subtotalBruto - descuento;

        return totalConDescuento + montoPropina;
    }
}