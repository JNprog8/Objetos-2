package punto4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestOrden {

    private OrdenDeCompra orden;
    private Producto p1;
    private Producto p2;

    @BeforeEach
    public void setUp() {
        orden = new OrdenDeCompra();
        p1 = new Producto("Pepsi", 100.0);
        p2 = new Producto("Papitas", 200.0);
    }

    @Test
    public void testProductoGetters() {
        assertEquals("Pepsi", p1.descripcion());
        assertEquals(100.0, p1.precio());
    }

    @Test
    public void testOrdenInicializacion() {
        assertNotNull(orden.estado());
        assertEquals(EstadoIniciada.class, orden.estado().getClass());
        assertEquals("Iniciada", orden.estado().toString());
        assertTrue(orden.productos().isEmpty());
        assertEquals(0.0, orden.monto());
        assertNull(orden.numeroSeguimiento());
    }

    @Test
    public void testAgregarProductoEnEstadoIniciada() {
        orden.agregarProducto(p1);
        
        // Verifica transición a EstadoEnPreparacion
        assertEquals(EstadoEnPreparacion.class, orden.estado().getClass());
        assertEquals("En Preparacion", orden.estado().toString());
        assertEquals(1, orden.productos().size());
        assertTrue(orden.productos().contains(p1));
    }

    @Test
    public void testAgregarProductoEnEstadoEnPreparacion() {
        orden.agregarProducto(p1);
        assertEquals(EstadoEnPreparacion.class, orden.estado().getClass());
        
        // Agregar otro producto en preparación no cambia el estado pero sí añade el producto
        orden.agregarProducto(p2);
        assertEquals(EstadoEnPreparacion.class, orden.estado().getClass());
        assertEquals(2, orden.productos().size());
        assertTrue(orden.productos().contains(p2));
    }

    @Test
    public void testConfirmarCompraDesdeEnPreparacion() {
        orden.agregarProducto(p1);
        orden.confirmarCompra();
        
        // Verifica transición a EstadoEnviada y generación de número de seguimiento
        assertEquals(EstadoEnviada.class, orden.estado().getClass());
        assertEquals("Enviada", orden.estado().toString());
        assertNotNull(orden.numeroSeguimiento());
        assertTrue(orden.numeroSeguimiento().startsWith("SEG-"));
        
        // El monto se calcula correctamente sumando los precios de los productos
        assertEquals(100.0, orden.monto());
    }

    @Test
    public void testCancelarDesdeIniciada() {
        orden.cancelar();
        assertEquals(EstadoCancelada.class, orden.estado().getClass());
        assertEquals("Cancelada", orden.estado().toString());
    }

    @Test
    public void testCancelarDesdeEnPreparacion() {
        orden.agregarProducto(p1);
        orden.cancelar();
        assertEquals(EstadoCancelada.class, orden.estado().getClass());
        assertEquals("Cancelada", orden.estado().toString());
    }

    @Test
    public void testAccionesInvalidasEnEstadoIniciada() {
        // No se puede enviar desde Iniciada
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            orden.confirmarCompra();
        });
        assertEquals("La orden solo puede enviarse desde en preparacion", exception.getMessage());
    }

    @Test
    public void testAccionesInvalidasEnEstadoEnviada() {
        orden.agregarProducto(p1);
        orden.confirmarCompra();
        assertEquals(EstadoEnviada.class, orden.estado().getClass());

        // No se pueden agregar productos
        IllegalStateException exAdd = assertThrows(IllegalStateException.class, () -> {
            orden.agregarProducto(p2);
        });
        assertEquals("No se pueden agregar productos en este estado", exAdd.getMessage());

        // No se puede volver a enviar
        IllegalStateException exSend = assertThrows(IllegalStateException.class, () -> {
            orden.confirmarCompra();
        });
        assertEquals("La orden ya se envio", exSend.getMessage());

        // No se puede cancelar
        IllegalStateException exCancel = assertThrows(IllegalStateException.class, () -> {
            orden.cancelar();
        });
        assertEquals("La orden no puede cancelarse en este estado", exCancel.getMessage());
    }

    @Test
    public void testAccionesInvalidasEnEstadoCancelada() {
        orden.cancelar();
        assertEquals(EstadoCancelada.class, orden.estado().getClass());

        // No se pueden agregar productos
        IllegalStateException exAdd = assertThrows(IllegalStateException.class, () -> {
            orden.agregarProducto(p1);
        });
        assertEquals("No se pueden agregar productos en este estado", exAdd.getMessage());

        // No se puede enviar
        IllegalStateException exSend = assertThrows(IllegalStateException.class, () -> {
            orden.confirmarCompra();
        });
        assertEquals("La orden solo puede enviarse desde en preparacion", exSend.getMessage());

        // No se puede volver a cancelar
        IllegalStateException exCancel = assertThrows(IllegalStateException.class, () -> {
            orden.cancelar();
        });
        assertEquals("La orden no puede cancelarse en este estado", exCancel.getMessage());
    }
}
