package oop2.tp3.refactorizado.ejercicio6;

import oop2.tp3.refactorizado.ejercicio6.descuentos.DescuentoPrioritario;
import oop2.tp3.refactorizado.ejercicio6.descuentos.DescuentoVip;
import oop2.tp3.refactorizado.ejercicio6.descuentos.SinDescuento;

public class MainCalculadoraPedido {
    public static void main(String[] args) {
        Pedido pedidoSinDescuento = new Pedido(10000, new SinDescuento());
        System.out.println("Total sin descuento: " + pedidoSinDescuento.total());

        Pedido pedidoPrioritario = new Pedido(10000, new DescuentoPrioritario());
        System.out.println("Total con descuento prioritario: " + pedidoPrioritario.total());

        Pedido pedidoVip = new Pedido(10000, new DescuentoVip());
        System.out.println("Total con descuento VIP: " + pedidoVip.total());
    }
}
