package facturacion;

import facturacion.tarjeta.TarjetaCredito;
import pedido.Pedido;

public class Factura {

    private final Pedido pedido;
    private final TarjetaCredito tarjeta;
    private final Propina propina;

    public Factura(Pedido pedido, TarjetaCredito tarjeta, Propina propina) {
        validarPedidoNoNulo(pedido);
        validarPedidoConfirmado(pedido);
        validarTarjeta(tarjeta);
        validarPropina(propina);

        this.pedido  = pedido;
        this.tarjeta = tarjeta;
        this.propina = propina;
    }

    public double calcularTotal() {
        double subtotalBruto = this.pedido.calcularSubtotal();
        double descuento = this.pedido.calcularDescuento(this.tarjeta);
        double montoPropina = this.propina.calcularSobre(subtotalBruto);
        return (subtotalBruto - descuento) + montoPropina;
    }

    // ---------------------------------------------------------------
    // Validaciones
    // ---------------------------------------------------------------

    private static void validarPedidoNoNulo(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
    }

    private static void validarPedidoConfirmado(Pedido pedido) {
        if (!pedido.estaConfirmado()) {
            throw new IllegalStateException("No se puede facturar un pedido sin confirmar.");
        }
    }

    private static void validarTarjeta(TarjetaCredito tarjeta) {
        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta no puede ser nula.");
        }
    }

    private static void validarPropina(Propina propina) {
        if (propina == null) {
            throw new IllegalArgumentException("Debe seleccionar una propina.");
        }
    }
}