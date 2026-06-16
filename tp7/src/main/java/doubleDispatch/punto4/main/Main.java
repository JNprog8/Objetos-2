package doubleDispatch.punto4.main;

import doubleDispatch.punto4.model.concreteElement.ProductoFisico;
import doubleDispatch.punto4.model.concreteVisitor.Regular;
import doubleDispatch.punto4.model.element.Producto;
import doubleDispatch.punto4.model.objectStructure.CarritoCompras;
import doubleDispatch.punto4.model.visitor.Cliente;

/**
 * Refactorice aplicando double dispatch el ejercicio del paquete compras del
 * repositorio https://github.com/enriquemolinari/oop2-ejercicios-doubledispatch. Los
 * tests deben seguir corriendo.
 */
public class Main {
    public static void main(String[] args) {
        Cliente regular = new Regular("Juan");
        Producto fisico = new ProductoFisico("Libro", 100f, 2);
        CarritoCompras carrito = new CarritoCompras(regular);
        carrito.agregarProducto(fisico);

        System.out.println("Punto 4 - Total Carrito: " + carrito.calcularPrecio());
    }
}
