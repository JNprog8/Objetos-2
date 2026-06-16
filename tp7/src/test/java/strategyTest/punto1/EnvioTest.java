package strategyTest.punto1;

import org.junit.jupiter.api.Test;
import strategy.punto1.model.concreteStrategy.ColectivosSur;
import strategy.punto1.model.concreteStrategy.CorreoArgentino;
import strategy.punto1.model.context.CarritoDeCompras;
import strategy.punto1.model.domain.Producto;
import strategy.punto1.model.domain.destino.Capital;
import strategy.punto1.model.domain.destino.Interior;
import strategy.punto1.model.infrastructure.MockServicioDistancia;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvioTest {

    @Test
    public void testArmarComputadora() {
        var carrito = new CarritoDeCompras();
        carrito.agregarProducto(new Producto("GPU", 50000, 2));
        carrito.agregarProducto(new Producto("Procesador", 30000, 1));
        carrito.agregarProducto(new Producto("Fuente de poder", 20000, 2));
        carrito.agregarProducto(new Producto("Motherboard", 15000, 1));
        carrito.agregarProducto(new Producto("RAM 16GB", 10000, 0.5f));
        carrito.agregarProducto(new Producto("Gabinete", 12000, 5));

        // Total productos: 50000 + 30000 + 20000 + 15000 + 10000 + 12000 = 137000
        // Peso total: 2 + 1 + 2 + 1 + 0.5 + 5 = 11.5 kg

        var strategy = new ColectivosSur();
        // ColectivosSur a Capital: 1000 + adicionalPorPeso(11.5)
        // adicionalPorPeso: 11.5 > 5 && 11.5 <= 30 -> 500
        // Costo envio: 1000 + 500 = 1500
        // Costo total: 137000 + 1500 = 138500

        var costoTotal = carrito.calcularCostoTotal(strategy, new Capital());
        assertEquals(138500, costoTotal);
    }

    @Test
    public void testArmarRackServidores() {
        var carrito = new CarritoDeCompras();
        carrito.agregarProducto(new Producto("Rack 42U", 100000, 80));
        carrito.agregarProducto(new Producto("Servidor Dell PowerEdge", 500000, 20));
        carrito.agregarProducto(new Producto("Switch 48 ports", 150000, 10));
        carrito.agregarProducto(new Producto("UPS 3kVA", 250000, 50));

        // Total productos: 100000 + 500000 + 150000 + 250000 = 1000000
        // Peso total: 80 + 20 + 10 + 50 = 160 kg

        var strategy = new CorreoArgentino(new MockServicioDistancia());
        // CorreoArgentino al Interior (Viedma - default 1000km): 800 + (5 * 1000) = 5800
        // Costo total: 1000000 + 5800 = 1005800

        var costoTotal = carrito.calcularCostoTotal(strategy, new Interior("Viedma"));
        assertEquals(1005800, costoTotal);
    }
}
