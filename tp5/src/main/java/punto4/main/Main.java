package punto4.main;

import punto4.model.Calculador;
import punto4.model.Jubilado;
import punto4.model.LogTransaction;
import punto4.model.NoJubilado;

import java.time.LocalDate;

/**
 * 4. El Supermercado “Amigos del Viento” lanzó una promoción en la cual se hacen cargo ellos
 * de parte del IVA que se le cobra a los productos.
 * a) Remueva el código duplicado utilizando Template Method. Escriba dos casos de test, uno
 * para el cálculo para jubilados y otro no
 */
public class Main {
    public static void main(String[] args) {
        // Simulación del Supermercado "Amigos del Viento"
        System.out.println("=== Supermercado Amigos del Viento ===");

        LogTransaction logger = (message) -> System.out.println("[LOG]: " + message);
        int mesActual = LocalDate.now().getMonthValue();

        // El supermercado tiene una promoción este mes
        Calculador calcJubilado = new Jubilado(mesActual, logger);
        Calculador calcNoJubilado = new NoJubilado(mesActual, logger);

        double precioProducto = 1000.0;

        System.out.println("Precio Base: $" + precioProducto);
        System.out.println("------------------------------------");

        double precioFinalJubilado = calcJubilado.calcularPrecio(precioProducto);
        System.out.println("Precio Final Jubilado (En Promoción): $" + precioFinalJubilado);

        double precioFinalNoJubilado = calcNoJubilado.calcularPrecio(precioProducto);
        System.out.println("Precio Final No Jubilado (IVA reducido al 15%): $" + precioFinalNoJubilado);

        // Simulación de un mes sin promoción para el No Jubilado
        int mesSiguiente = (mesActual % 12) + 1;
        Calculador calcNoJubiladoRegular = new NoJubilado(mesSiguiente, logger);

        System.out.println("------------------------------------");
        System.out.println("Precio Final No Jubilado (Mes Regular - IVA 21%): $" + calcNoJubiladoRegular.calcularPrecio(precioProducto));
    }
}
