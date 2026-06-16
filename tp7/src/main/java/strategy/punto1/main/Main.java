package strategy.punto1.main;

import strategy.punto1.model.concreteStrategy.ColectivosSur;
import strategy.punto1.model.concreteStrategy.CorreoArgentino;
import strategy.punto1.model.context.CarritoDeCompras;
import strategy.punto1.model.domain.Producto;
import strategy.punto1.model.domain.destino.Capital;
import strategy.punto1.model.domain.destino.GBA;
import strategy.punto1.model.domain.destino.Interior;
import strategy.punto1.model.infrastructure.MockServicioDistancia;

/**
 * Una empresa que se dedica a la comercialización de productos informáticos a través de
 * internet ofrece a sus clientes la posibilidad de optar entre diferentes formas de envío de los
 * productos. El cliente va almacenando productos en su carrito de compras y finalmente el sistema
 * calcula el costo total incluyendo el envío. El costo total será la suma de precio de cada producto
 * del carrito, más el envío que cada compañía ofrece su forma de cálculo específica.
 * Las posibilidades de envío que ofrece son a través de la empresa de
 * - Colectivos Sur: Si el destino es Capital Federal hay un costo fijo de 1000 pesos. Si el destino es
 * gran buenos aires el monto fijo es de 1500 pesos. Cualquier otro destino el monto fijo es 3000
 * pesos. Además, si el peso total de los productos superan los 5kg (hasta 30kg), se le agrega un
 * adicional de 500 pesos. Pasados los 30kg el adicional es de 2000 pesos.
 * - Correo Argentino: Si el destino es Capital Federal se cobra un monto fijo de 500 pesos.
 * Cualquier otro destino, se cobra un fijo de 800, más un monto que sale de calcular 5$
 * multiplicado por la cantidad de kilómetros entre Capital Federal y el destino. Ésta distancia la
 * brinda un servicio externo Web: http://distancia.ar?orgen=capital&destino=xxx (Este servicio no
 * existe, es simplemente para ilustrar el ejercicio). El sistema debe permitir al cliente optar por
 * cualquier forma de envío e informarle el costo asociado a la opción elegida.
 * a) Aplicando el patrón Strategy diseñe una posible solución al problema planteado. Implemente
 * en Java la solución propuesta y dos casos de test
 */
public class Main {
    public static void main(String[] args) {
        var carrito = new CarritoDeCompras();
        carrito.agregarProducto(new Producto("Pantalla", 20000, 4));
        carrito.agregarProducto(new Producto("Teclado", 5000, 2)); // Total peso: 6kg

        // Via Colectivos Sur hasta GBA (1500 + 500 adicional peso) = 2000
        var colectivos = new ColectivosSur();
        var gba = new GBA();
        var totalSur = carrito.calcularCostoTotal(colectivos, gba);
        System.out.println("Colectivos Sur (GBA, 6kg): " + (totalSur == 27000 ? "[SUCCESS]" : "[FAIL] (" + totalSur + ")"));

        // Via Correo Argentino hasta Rosario (800 + 5 * 300) = 2300
        var correo = new CorreoArgentino(new MockServicioDistancia());
        var rosario = new Interior("Rosario");
        var totalCorreo = carrito.calcularCostoTotal(correo, rosario);
        System.out.println("Correo Argentino (Rosario): " + (totalCorreo == 27300 ? "[SUCCESS]" : "[FAIL] (" + totalCorreo + ")"));

        // Via Correo Argentino hasta Capital Federal (500).
        var capital = new Capital();
        var totalCorreoCap = carrito.calcularCostoTotal(correo, capital);
        System.out.println("Correo Argentino (Capital): " + (totalCorreoCap == 25500 ? "[SUCCESS]" : "[FAIL] (" + totalCorreoCap + ")"));
    }
}
