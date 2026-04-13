package logica.facturacion;

import logica.facturacion.propina.Propina;
import logica.facturacion.tarjetas.TarjetaCredito;
import logica.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Pedido pedido;
    private TarjetaCredito tarjeta;
    private Propina propina;
    private List<RegistrarFactura> registros;

    public Factura(Pedido pedido, TarjetaCredito tarjeta, Propina propina) {
        validarPedidoNoNulo(pedido);
        validarPedidoConfirmado(pedido);
        validarTarjeta(tarjeta);
        validarPropina(propina);

        this.pedido = pedido;
        this.tarjeta = tarjeta;
        this.propina = propina;
        this.registros = new ArrayList<>();
    }

    private void validarPedidoNoNulo(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
    }

    private void validarPedidoConfirmado(Pedido pedido) {
        if (!pedido.estaConfirmado()) {
            throw new IllegalStateException("No se puede facturar un pedido no confirmado.");
        }
    }

    private void validarTarjeta(TarjetaCredito tarjeta) {
        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta no puede ser nula.");
        }
    }

    private void validarPropina(Propina propina) {
        if (propina == null) {
            throw new IllegalArgumentException("La propina no puede ser nula.");
        }
    }

    public void agregarRegistro(RegistrarFactura registro) {
        if (registro != null) {
            this.registros.add(registro);
        }
    }

    public double calcularMontoTotal() {
        return this.pedido.calcularMontoFinal(this.tarjeta, this.propina);
    }

    public void emitir() {
        double montoFinal = this.calcularMontoTotal();
        for (RegistrarFactura observador : registros) {
            observador.registrar(montoFinal);
        }
    }
}
