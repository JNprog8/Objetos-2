package restaurante;

import logica.catalogo.Producto;
import logica.catalogo.categorias.Bebida;
import logica.facturacion.RegistrarFactura;
import logica.facturacion.propina.PorcentajePropina;
import logica.facturacion.tarjetas.Visa;
import logica.mesa.Mesa;
import mail.FakeProveedorCorreo;
import mail.core.GeneradorMensaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestauranteTest {

    private final String EMAIL_RESTAURANTE = "gerencia@restaurante.com";
    private FakeArchivo fakeArchivo;
    private FakeBaseDatos fakeBaseDatos;
    private FakeProveedorCorreo fakeNotificadorEmail;
    private RegistrarFactura composite;
    private Mesa mesa;
    private int numeroMesa = 1;
    private ServicioFacturacion servicio;

    @BeforeEach
    void setUp() {
        fakeArchivo = new FakeArchivo();
        fakeBaseDatos = new FakeBaseDatos();
        fakeNotificadorEmail = new FakeProveedorCorreo();

        RegistrarFactura notificadorEmail = new NotificarFacturacionPorMail(
                fakeNotificadorEmail,
                new GeneradorMensaje(),
                EMAIL_RESTAURANTE,
                numeroMesa
        );

        composite = new Factura(List.of(
                fakeArchivo,
                fakeBaseDatos,
                notificadorEmail
        ));

        mesa = new Mesa(numeroMesa, 4);
        mesa.agregarRegistroFactura(composite);
        mesa.nuevoPedido();
        servicio = new ServicioFacturacion();
    }

    @Test
    @DisplayName("Debería registrar el monto en archivo al facturar")
    void FakeRegistroTexto() {
        double montoEsperado = prepararPedidoYCobrar();
        assertTrue(fakeArchivo.fueRegistradoMonto(montoEsperado));
    }

    @Test
    @DisplayName("Debería registrar el monto en base de datos al facturar")
    void FakeBaseDatos() {
        double montoEsperado = prepararPedidoYCobrar();
        assertTrue(fakeBaseDatos.fueRegistradoMonto(montoEsperado));
    }

    @Test
    @DisplayName("Debería notificar por email al facturar")
    void FakeNotificarMail() {
        prepararPedidoYCobrar();
        assertEquals(1, fakeNotificadorEmail.cantidadDeCorreosEnviados());
        assertTrue(fakeNotificadorEmail.fueEnviadoA(EMAIL_RESTAURANTE));
    }

    private double prepararPedidoYCobrar() {
        Producto coca = new Producto("Coca Cola", 1000, new Bebida());
        mesa.agregarAlPedido(coca, 2);
        double montoEsperado = 2040.0;
        servicio.cerrarMesaYFacturar(mesa, new Visa(), PorcentajePropina.CINCO_PORCIENTO);
        return montoEsperado;
    }

    private static class FakeArchivo implements RegistrarFactura {
        private final List<Double> montos = new ArrayList<>();

        @Override
        public void registrar(double monto) {
            montos.add(monto);
        }

        public boolean fueRegistradoMonto(double monto) {
            return montos.stream().anyMatch(m -> Math.abs(m - monto) < 0.001);
        }
    }

    private static class FakeBaseDatos implements RegistrarFactura {
        private final List<Double> montos = new ArrayList<>();

        @Override
        public void registrar(double monto) {
            montos.add(monto);
        }

        public boolean fueRegistradoMonto(double monto) {
            return montos.stream().anyMatch(m -> Math.abs(m - monto) < 0.001);
        }
    }
}
