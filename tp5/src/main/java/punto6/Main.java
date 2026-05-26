package punto6;

/**
 * La siguiente clase Producto calcula el precio de un producto teniendo en cuenta impuestos,
 * descuentos y envío. Luego se presenta un Main para mostrar cómo se utiliza. Se pide:
 * 1. Refactorizar aplicando polimorfismo.
 * 2. Aplique Template method para quitar código duplicado.
 * 3. Modifique el main para que funcione de acuerdo al refactor realizado.
 */
public class Main {
    public static void main(String[] args) {
        var p1 = new Libro(30);
        var p2 = new Medicina(330);
        var p3 = new Alimento(130);
        var p4 = new Otro(130);
        System.out.println(p1.precioFinal());
        System.out.println(p2.precioFinal());
        System.out.println(p3.precioFinal());
        System.out.println(p4.precioFinal());
    }
}
