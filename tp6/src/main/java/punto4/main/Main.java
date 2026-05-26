package punto4.main;

import punto4.model.builder.PedidoBuilder;
import punto4.model.component.Combo;

/**
 * Un restaurante de comidas rápidas ofrece 3 tipos de combos (Combo Básico, Combo
 * Familiar, Combo Especial). De cada combo podemos conocer su descripción que nos detalla
 * el contenido del combo, y por otro lado podemos conocer su precio.
 * El restaurante también ofrece la posibilidad de aumentar el pedido mediante diferentes
 * porciones adicionales (Tomate, Papas, Carne, Queso). Cada porción que se agrega al combo
 * tiene un costo adicional.
 * Se desea crear un sistema de pedidos que permita al usuario seleccionar el combo deseado, así
 * como armar su propio pedido con las porciones adicionales que desee. El sistema deberá
 * informar sobre el pedido del usuario detallando su descripción y el valor total del mismo.
 * a) Aplique el patrón Decorator para diseñar el modelo de clases que de solución al problema
 * planteado.
 * b) Implemente la solución en Java, especificando en el programa principal el armado de 2
 * combos distintos con al menos dos adicionales cada uno.
 * c) Implemente la creación de los combos utilizando el patron Builder
 */
public class Main {
    public static void main(String[] args) {

        // Pedido 1: Combo Básico con Queso y Carne
        Combo pedido1 = new PedidoBuilder()
                .comboBasico()
                .conQueso()
                .conCarne()
                .build();

        System.out.println("--- Pedido 1 ---");
        System.out.println("Descripción: " + pedido1.descripcion());
        System.out.println("Total: $" + pedido1.precio());
        System.out.println();

        // Pedido 2: Combo Especial con Papas y Tomate
        Combo pedido2 = new PedidoBuilder()
                .comboEspecial()
                .conPapas()
                .conTomate()
                .build();

        System.out.println("--- Pedido 2 ---");
        System.out.println("Descripción: " + pedido2.descripcion());
        System.out.println("Total: $" + pedido2.precio());
        System.out.println();

        // Pedido 3: Combo Familiar
        Combo pedido3 = new PedidoBuilder()
                .comboFamiliar()
                .conCarne()
                .conQueso()
                .conPapas()
                .conTomate()
                .build();

        System.out.println("--- Pedido 3 ---");
        System.out.println("Descripción: " + pedido3.descripcion());
        System.out.println("Total: $" + pedido3.precio());
    }
}
