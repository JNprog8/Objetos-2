package mail.core;

import logica.facturacion.RegistrarFactura;
import mail.dto.Mensaje;

public class NotificarFacturacionPorMail implements RegistrarFactura {
    private final Notificador proveedor;
    private final GeneradorMensaje generador;
    private final String emailRestaurante;
    private final int numeroMesa;

    public NotificarFacturacionPorMail(Notificador proveedor,
                                       GeneradorMensaje generador,
                                       String emailRestaurante,
                                       int numeroMesa) {
        this.proveedor = proveedor;
        this.generador = generador;
        this.emailRestaurante = emailRestaurante;
        this.numeroMesa = numeroMesa;
    }

    @Override
    public void registrar(double monto) {
        Mensaje mensaje = generador.crearMensajeFacturacionRestaurante(emailRestaurante, numeroMesa, monto);
        proveedor.enviar(mensaje);
    }
}
