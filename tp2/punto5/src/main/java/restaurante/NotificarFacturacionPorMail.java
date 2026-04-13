package restaurante;

import logica.facturacion.RegistrarFactura;
import mail.core.GeneradorMensaje;
import mail.core.Notificador;
import mail.dto.Mensaje;

public class NotificarFacturacionPorMail implements RegistrarFactura {
    private final Notificador notificador;
    private final GeneradorMensaje generador;
    private final String emailRestaurante;
    private final int numeroMesa;

    public NotificarFacturacionPorMail(Notificador notificador,
                                       GeneradorMensaje generador,
                                       String emailRestaurante,
                                       int numeroMesa) {
        this.notificador = notificador;
        this.generador = generador;
        this.emailRestaurante = emailRestaurante;
        this.numeroMesa = numeroMesa;
    }

    @Override
    public void registrar(double monto) {
        Mensaje mensaje = generador.crearMensajeFacturacionRestaurante(emailRestaurante, numeroMesa, monto);
        notificador.enviar(mensaje);
    }
}
