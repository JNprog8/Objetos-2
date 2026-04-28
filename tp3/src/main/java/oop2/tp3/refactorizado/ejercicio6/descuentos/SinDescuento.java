package oop2.tp3.refactorizado.ejercicio6.descuentos;

public class SinDescuento implements Descuento {
    @Override
    public double aplicar(double subtotal) {
        return 0;
    }
}
