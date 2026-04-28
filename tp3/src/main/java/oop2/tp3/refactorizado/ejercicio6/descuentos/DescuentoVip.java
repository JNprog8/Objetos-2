package oop2.tp3.refactorizado.ejercicio6.descuentos;

public class DescuentoVip implements Descuento {

    @Override
    public double aplicar(double subtotal) {
        return subtotal * 0.15;
    }
}
