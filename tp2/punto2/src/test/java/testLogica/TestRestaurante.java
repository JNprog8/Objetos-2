package testLogica;

import logica.catalogo.Producto;
import logica.catalogo.categorias.Bebida;
import logica.catalogo.categorias.PlatoPrincipal;
import logica.facturacion.Factura;
import logica.facturacion.propina.PorcentajePropina;
import logica.facturacion.tarjetas.ComarcaPlus;
import logica.facturacion.tarjetas.Mastercard;
import logica.facturacion.tarjetas.Visa;
import logica.mesa.Mesa;
import logica.pedido.Pedido;
import logica.restaurante.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestRestaurante {

    private Producto agua, pizza;

    @BeforeEach
    public void setUp() {
        agua = new Producto("Agua", 100.0, new Bebida());
        pizza = new Producto("Pizza", 100.0, new PlatoPrincipal());
    }

    @Test
    public void testFlujoDeMesaConCalculoTotal() {
        // set up
        Mesa mesa = new Mesa(1, 4);
        mesa.nuevoPedido();
        mesa.agregarAlPedido(agua, 2); // 200
        mesa.agregarAlPedido(pizza, 1); // 100 -> Total 300

        // ejercitacion
        Factura factura = mesa.cerrarMesa(new Visa(), PorcentajePropina.DOS_PORCIENTO);

        // Visa: 3% sobre 200 (Bebidas) = 6.0
        // Propina: 2% sobre 300 = 6.0
        // Total: (300 - 6.0) + 6.0 = 300.0

        // verificacion
        assertEquals(300.0, factura.calcularMontoTotal(), 0.001);
        assertThrows(IllegalStateException.class, mesa::obtenerPedido, "La mesa debería estar libre tras cerrar");
    }

    @Test
    public void testFacturacionComarcaPlus() {
        // set up
        Pedido pedido = new Pedido();
        pedido.agregarItem(agua, 1); // 100
        pedido.confirmar();

        // ejercitacion
        Factura factura = new Factura(pedido, new ComarcaPlus(), PorcentajePropina.CINCO_PORCIENTO);

        // 100 - 2% (2.0) + 5% (5.0) = 103.0
        // verificacion
        assertEquals(103.0, factura.calcularMontoTotal(), 0.001);
    }

    @Test
    public void testFacturacionMastercard() {
        // set up
        Pedido pedido = new Pedido();
        pedido.agregarItem(pizza, 2); // 200
        pedido.confirmar();

        // ejercitacion
        Factura factura = new Factura(pedido, new Mastercard(), PorcentajePropina.TRES_PORCIENTO);
        // 200 - 2% (4.0) + 3% (6.0) = 202.0
        // verificacion
        assertEquals(202.0, factura.calcularMontoTotal(), 0.001);
    }

    @Test
    public void testGestionRestauranteYMesas() {
        // set up
        Restaurante resto = new Restaurante("Imperial Food");
        Mesa m1 = new Mesa(1, 2);
        Mesa m2 = new Mesa(2, 4);

        // ejercitacion
        resto.agregarMesa(m1, new ArrayList<>());
        resto.agregarMesa(m2, new ArrayList<>());

        // verificacion
        assertEquals(2, resto.cantidadMesas());

        assertNotNull(resto.buscarMesa(1).orElseThrow(), "La mesa 1 debería encontrarse");
        assertNotNull(resto.buscarMesa(2).orElseThrow(), "La mesa 2 debería encontrarse");
        assertTrue(resto.buscarMesa(3).isEmpty(), "La mesa 3 NO debería existir");

        assertEquals("Imperial Food", resto.obtenerNombre());
    }

    @Test
    public void testValidacionesDeMesa() {
        // set up
        Mesa mesa = new Mesa(1, 2);

        // Validaciones de estado
        assertThrows(IllegalStateException.class, () -> mesa.agregarAlPedido(agua, 1), "No se puede agregar sin pedido abierto");
        assertThrows(IllegalStateException.class, () -> mesa.cerrarMesa(new Visa(), PorcentajePropina.DOS_PORCIENTO), "No se puede cerrar sin pedido");

        // Validaciones de construcción
        assertThrows(IllegalArgumentException.class, () -> new Mesa(0, 4));
        assertThrows(IllegalArgumentException.class, () -> new Mesa(1, 0));
    }

    @Test
    public void testValidacionesDeRestaurante() {
        assertThrows(IllegalArgumentException.class, () -> new Restaurante(""));
        Restaurante resto = new Restaurante("Test");
        assertThrows(IllegalArgumentException.class, () -> resto.agregarMesa(null, new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> resto.agregarMesa(new Mesa(1, 2), null));
    }
}
