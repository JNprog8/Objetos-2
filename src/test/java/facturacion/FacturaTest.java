package facturacion;

import catalogo.Categoria;
import catalogo.Producto;
import facturacion.tarjetas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedidos.Pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FacturaTest {

    private Pedido pedidoBase;

    @BeforeEach
        // Set Up en común para todas las pruebas
    void inicializar() {
        // productos
        var agua = new Producto("Agua Mineral", 1000.0, Categoria.BEBIDA); // $1000
        var pasta = new Producto("Fideos con Tuco", 8000.0, Categoria.PLATO_PRINCIPAL); // $8000

        // pedido: 2 Aguas ($2000) + 1 Pasta ($8000) = Total Bruto $10000
        this.pedidoBase = new Pedido();
        this.pedidoBase.agregarItem(agua, 2);
        this.pedidoBase.agregarItem(pasta, 1);

        this.pedidoBase.confirmar();
    }

    @Test
    void testCalculoCostoConTarjetaVisaYPropinaCincoPorCiento() {
        // 1. Set Up
        var tarjetaVisa = new TarjetaVisa();
        var propina = Propina.CINCO_POR_CIENTO;
        var factura = new Factura(pedidoBase, tarjetaVisa, propina);

        // 2. Ejercitación
        double totalCalculado = factura.calcularTotal();

        // 3. Verificación
        // Subtotal Bruto: 10000
        // Descuento Visa: 3% sobre bebidas (2000 * 0.03 = 60)
        // Propina: 5% sobre bruto (10000 * 0.05 = 500)
        // Total esperado: 10000 - 60 + 500 = 10440.0
        assertEquals(10440.0, totalCalculado, 0.01, "El cálculo con Visa falló.");
    }

    @Test
    void testCalculoCostoConTarjetaMastercardYPropinaDosPorCiento() {
        // 1. Set Up
        TarjetaCredito tarjetaMaster = new TarjetaMastercard();
        Propina propina = Propina.DOS_POR_CIENTO;
        Factura factura = new Factura(pedidoBase, tarjetaMaster, propina);

        // 2. Ejercitación
        double totalCalculado = factura.calcularTotal();

        // 3. Verificación
        // Subtotal Bruto: 10000
        // Descuento Master: 2% sobre platos (8000 * 0.02 = 160)
        // Propina: 2% sobre bruto (10000 * 0.02 = 200)
        // Total esperado: 10000 - 160 + 200 = 10040.0
        assertEquals(10040.0, totalCalculado, 0.01, "El cálculo con Mastercard falló.");
    }

    @Test
    void testCalculoCostoConTarjetaComarcaPlusYPropinaTresPorCiento() {
        // 1. Set Up
        TarjetaCredito tarjetaComarca = new TarjetaComarcaPlus();
        Propina propina = Propina.TRES_POR_CIENTO;
        Factura factura = new Factura(pedidoBase, tarjetaComarca, propina);

        // 2. Ejercitación
        double totalCalculado = factura.calcularTotal();

        // 3. Verificación
        // Subtotal Bruto: 10000
        // Descuento Comarca: 2% sobre el total bruto (10000 * 0.02 = 200)
        // Propina: 3% sobre bruto (10000 * 0.03 = 300)
        // Total esperado: 10000 - 200 + 300 = 10100.0
        assertEquals(10100.0, totalCalculado, 0.01, "El cálculo con Comarca Plus falló.");
    }

    @Test
    void testCalculoCostoConTarjetaViedmaGenericaYPropinaDosPorCiento() {
        // 1. Set Up
        // La tarjeta Viedma entra en la categoría "Cualquier otro tipo de tarjeta"
        TarjetaCredito tarjetaViedma = new TarjetaGenerica();
        Propina propina = Propina.DOS_POR_CIENTO;
        Factura factura = new Factura(pedidoBase, tarjetaViedma, propina);

        // 2. Ejercitación
        double totalCalculado = factura.calcularTotal();

        // 3. Verificación
        // Subtotal Bruto: 10000
        // Descuento Genérica: 0% (0)
        // Propina: 2% sobre bruto (10000 * 0.02 = 200)
        // Total esperado: 10000 - 0 + 200 = 10200.0
        assertEquals(10200.0, totalCalculado, 0.01, "El cálculo con Tarjeta Viedma (Genérica) falló.");
    }

    @Test
    void testFalloAlIntentarFacturarUnPedidoSinConfirmar() {
        // 1. Set Up
        var pedidoSinConfirmar = new Pedido(); // Instanciado pero sin confirmado
        var tarjeta = new TarjetaVisa();

        // 2 & 3. Ejercitación y Verificación (Validación de comportamiento / Fail-fast)
        assertThrows(IllegalStateException.class, () -> {
            new Factura(pedidoSinConfirmar, tarjeta, Propina.DOS_POR_CIENTO);
        }, "Se esperaba una excepción al facturar un pedido sin confirmar.");
    }

    @Test
    void testFalloAlIntentarFacturarConPedidoNulo() {
        // 1. Set Up
        var tarjeta = new TarjetaVisa();

        // 2 & 3. Ejercitación y Verificación
        assertThrows(IllegalArgumentException.class, () -> {
            new Factura(null, tarjeta, Propina.CINCO_POR_CIENTO);
        }, "Se esperaba una excepción al pasar un pedido nulo.");
    }

    @Test
    void testFalloAlIntentarFacturarConTarjetaNula() {
        // 1. Set Up
        // 2 & 3. Ejercitación y Verificación
        assertThrows(IllegalArgumentException.class, () -> {
            new Factura(pedidoBase, null, Propina.TRES_POR_CIENTO);
        }, "Se esperaba una excepción al pasar una tarjeta nula.");
    }

    @Test
    void testFalloAlIntentarFacturarConPropinaNula() {
        // 1. Set Up
        TarjetaCredito tarjeta = new TarjetaComarcaPlus();

        // 2 & 3. Ejercitación y Verificación
        assertThrows(IllegalArgumentException.class, () -> {
            new Factura(pedidoBase, tarjeta, null);
        }, "Se esperaba una excepción al pasar una propina nula.");
    }
}