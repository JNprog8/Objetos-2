package logica.pedido;

import logica.catalogo.Producto;
import logica.facturacion.tarjetas.TarjetaCredito;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static final String ERROR_PEDIDO_CONFIRMADO = "El pedido ya fue confirmado y no puede modificarse";
    private static final String ERROR_PEDIDO_VACIO = "No se puede confirmar un pedido vacío.";

    private List<ItemMenu> items;
    private boolean confirmado;

    public Pedido() {
        this.items = new ArrayList<>();
        this.confirmado = false;
    }

    public void agregarItem(Producto producto, int cantidad) {
        validarNoConfirmado();
        this.items.add(new ItemMenu(producto, cantidad));
    }

    private void validarNoConfirmado() {
        if (estaConfirmado()) {
            throw new IllegalStateException(ERROR_PEDIDO_CONFIRMADO);
        }
    }

    public void confirmar() {
        validarNoVacio();
        this.confirmado = true;
    }

    private void validarNoVacio() {
        if (this.items.isEmpty()) {
            throw new IllegalStateException(ERROR_PEDIDO_VACIO);
        }
    }

    public double calcularPrecioTotalBruto() {
        return this.items.stream()
                .mapToDouble(ItemMenu::calcularSubtotal)
                .sum();
    }

    public double calcularDescuentoTotal(TarjetaCredito tarjeta) {
        return this.items.stream()
                .mapToDouble(item -> item.calcularDescuento(tarjeta))
                .sum();
    }

    public double calcularMontoFinal(TarjetaCredito tarjeta, Propina propina) {
        double bruto = calcularPrecioTotalBruto();
        double descuento = calcularDescuentoTotal(tarjeta);
        double montoPropina = propina.calcularSobre(bruto);

        return (bruto - descuento) + montoPropina;
    }

    public boolean estaConfirmado() {
        return this.confirmado;
    }
}
