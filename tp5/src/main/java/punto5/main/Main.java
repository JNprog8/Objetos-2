package punto5.main;

import punto5.model.Importadas;
import punto5.model.Nacional;
import punto5.model.Remera;

/**
 * 5. Un negocio comercializa diferentes tipos de remeras. Las remeras pueden ser importadas o
 * nacionales. Para el cálculo del precio de venta se deben tener en cuenta que ambos tipos de
 * remeras poseen un precio unitario. Además las remeras importadas sobre el precio unitario
 * tienen un 3% de recargo más un 5% de impuesto aduanero. Por último el comercio aplica un
 * 25% para determinar el precio final.
 * Las remeras nacionales tienen un recargo de 1,5 % del costo de transporte y una bonificación
 * del 20%. Por último el comercio aplica un 15% para determinar el precio final.
 * a) Aplicando el patrón Template Method, diseñe un modelo de clases que represente el
 * problema descripto, donde se detalle el proceso de cálculo del precio de venta de las remeras.
 * b) Implemente la solución en Java y escriba dos casos de test.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Remera remeraNacional = new Nacional(100.0f);
            Remera remeraImportada = new Importadas(100.0f);

            System.out.println("--- Cálculo de Precios de Remeras ---");
            System.out.printf("Remera Nacional (Precio base 100): %.2f%n", remeraNacional.calcularPrecio());
            System.out.printf("Remera Importada (Precio base 100): %.2f%n", remeraImportada.calcularPrecio());

            // Demostración de validación
            System.out.println("\nIntentando crear una remera con precio inválido...");
            new Nacional(-50.0f);

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        }
    }
}
