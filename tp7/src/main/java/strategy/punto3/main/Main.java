package strategy.punto3.main;

import strategy.punto3.model.concreteStrategy.Alimento;
import strategy.punto3.model.concreteStrategy.Libro;
import strategy.punto3.model.concreteStrategy.Medicina;
import strategy.punto3.model.concreteStrategy.Otro;
import strategy.punto3.model.context.Producto;

/**
 * La siguiente clase Producto calcula el precio de un producto teniendo en cuenta impuestos,
 * descuentos y envío. Luego se presenta un Main para mostrar cómo se utiliza. Se pide:
 * 1. Refactorizar para remover los IFs sobre los tipos de producto aplicando el patrón Strategy
 * (creando la jerarquía polimórfica con un CalculadorDePrecios, no sobre Producto. Producto
 * delega en la estrategia de forma polimorfica).
 * 2. Modifique el Main para que funcione de acuerdo al refactor realizado.
 */
public class Main {
    public static void main(String[] args) {
        var p1 = new Producto(30, new Libro());
        var p2 = new Producto(330, new Medicina());
        var p3 = new Producto(130, new Alimento());
        var p4 = new Producto(130, new Otro());

        System.out.println("Precio final Libro (30): " + p1.precioFinal());
        System.out.println("Precio final Medicina (330): " + p2.precioFinal());
        System.out.println("Precio final Alimento (130): " + p3.precioFinal());
        System.out.println("Precio final Otro (130): " + p4.precioFinal());
    }
}
