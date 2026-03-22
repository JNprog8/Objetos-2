import catalogo.*;
import catalogo.categoria.*;
import facturacion.*;
import facturacion.tarjeta.*;
import mesa.Mesa;
import pedido.Pedido;
import restaurante.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRestaurante {

    private Producto bebida, comida;

    @BeforeEach
    public void setUp() {
        bebida = new Producto("Agua", 100.0, new Bebida());
        comida = new Producto("Pizza", 100.0, new Comida());
    }

    /**
     * Caso 1: Cálculo de costo con tarjeta Visa.
     * Descuento: 3% sobre bebidas.
     */
    @Test
    public void testPedidoConFacturacionVisa() {
        // Agua (100) + Pizza (100) = 200 [bruto]
        // Visa: 3% sobre 100 (Bebida) = 3 descuento
        // Subtotal con descuento: 197
        // Propina 2% sobre bruto (200 * 0.02) = 4
        // Total esperado: 197 + 4 = 201

        var pedido = new Pedido();
        pedido.agregarItem(bebida, 1);
        pedido.agregarItem(comida, 1);
        pedido.confirmar();

        var factura = new Factura(pedido, new TarjetaVisa(), Propina.DOS_PORCIENTO);
        assertEquals(201.0, factura.calcularTotal(), 0.001);
    }

    /**
     * Caso 2: Cálculo de costo con tarjeta Mastercard.
     * Descuento: 2% sobre platos principales.
     */
    @Test
    public void testPedidoConFacturacionMastercard() {
        // Agua + Pizza = 200 [bruto]
        // Mastercard: 2% sobre 100 (Comida) = 2 descuento
        // Subtotal con descuento: 198
        // Propina 3% sobre bruto (200 * 0.03) = 6
        // Total esperado: 198 + 6 = 204

        var pedido = new Pedido();
        pedido.agregarItem(bebida, 1);
        pedido.agregarItem(comida, 1);
        pedido.confirmar();

        var factura = new Factura(pedido, new TarjetaMastercard(), Propina.TRES_PORCIENTO);
        assertEquals(204.0, factura.calcularTotal(), 0.001);
    }

    /**
     * Caso 3: Cálculo de costo con tarjeta Comarca Plus.
     * Descuento: 2% sobre el costo total (bebidas + comida).
     */
    @Test
    public void testPedidoConFacturacionComarcaPlus() {
        // Agua (100) + Pizza (100) = 200 bruto
        // Comarca Plus: 2% sobre 200 = 4 descuento
        // Subtotal con descuento: 196
        // Propina 5% sobre bruto (200 * 0.05) = 10
        // Total esperado: 196 + 10 = 206

        var pedido = new Pedido();
        pedido.agregarItem(bebida, 1);
        pedido.agregarItem(comida, 1);
        pedido.confirmar();

        var factura = new Factura(pedido, new TarjetaComarcaPlus(), Propina.CINCO_PORCIENTO);
        assertEquals(206.0, factura.calcularTotal(), 0.001);
    }

    /**
     * Caso 4: Cálculo de costo con tarjeta Viedma (representada por TarjetaGenerica).
     * Descuento: 0% sobre el total.
     */
    @Test
    public void testPedidoConFacturacionViedma() {
        // Agua (100) + Pizza (100) = 200 bruto
        // Generica (Viedma): 0% descuento
        // Subtotal con descuento: 200
        // Propina 2% sobre bruto (200 * 0.02) = 4
        // Total esperado: 200 + 4 = 204

        var pedido = new Pedido();
        pedido.agregarItem(bebida, 1);
        pedido.agregarItem(comida, 1);
        pedido.confirmar();

        var tarjetaViedma = new TarjetaGenerica(); // Tarjeta Viedma como Tarjeta Generica
        var factura = new Factura(pedido, tarjetaViedma, Propina.DOS_PORCIENTO);
        assertEquals(204.0, factura.calcularTotal(), 0.001);
    }

    /**
     * Test Cobertura: Validaciones de Pedido
     */
    @Test
    public void testValidacionesDePedidoYSuEstado() {
        var pedidoVacio = new Pedido();

        assertFalse(pedidoVacio.estaConfirmado());

        // No se puede confirmar un pedido vacío
        assertThrows(IllegalStateException.class, pedidoVacio::confirmar);

        pedidoVacio.agregarItem(bebida, 2); // 200
        pedidoVacio.confirmar();
        assertTrue(pedidoVacio.estaConfirmado());

        // No se puede agregar más ítems tras confirmar
        assertThrows(IllegalStateException.class, () -> pedidoVacio.agregarItem(comida, 1));
        
        assertEquals(200.0, pedidoVacio.calcularSubtotal(), 0.001);
    }

    /**
     * Test de Cobertura: Validaciones de Mesa y Restaurante.
     */
    @Test
    public void testValidacionesMesaYRestaurante() {
        var restoLasLilas = new Restaurante("Las Lilas");
        restoLasLilas.agregarMesa(new Mesa(1, 4));
        restoLasLilas.agregarMesa(new Mesa(2, 6));

        assertEquals("Las Lilas", restoLasLilas.obtenerNombre());
        assertEquals(2, restoLasLilas.cantidadMesas());

        var mesa1 = restoLasLilas.buscarMesa(1);
        mesa1.nuevoPedido();
        assertNotNull(mesa1.obtenerPedido());

        assertThrows(IllegalArgumentException.class, () -> restoLasLilas.buscarMesa(99));
        assertThrows(IllegalStateException.class, () -> mesa1.nuevoPedido()); // Ya tiene pedido activo
    }

    /**
     * Test de Cobertura: Validaciones de Producto.
     */
    @Test
    public void testExcepcionesDeProducto() {
        assertThrows(IllegalArgumentException.class, () -> new Producto(null, 100, new Bebida()));
        assertThrows(IllegalArgumentException.class, () -> new Producto("Agua", 0, new Bebida()));
        assertThrows(IllegalArgumentException.class, () -> new Producto("Agua", 100, null));
    }
}