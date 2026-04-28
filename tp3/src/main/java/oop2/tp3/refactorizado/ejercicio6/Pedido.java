package oop2.tp3.refactorizado.ejercicio6;

import oop2.tp3.refactorizado.ejercicio6.descuentos.Descuento;

public class Pedido {
    private double subtotal;
    private Descuento descuento;

    public Pedido(double subtotal, Descuento descuento) {
        this.subtotal = subtotal;
        this.descuento = descuento;
    }

    public double total() {
        return this.subtotal - descuento.aplicar(this.subtotal);
    }
}
