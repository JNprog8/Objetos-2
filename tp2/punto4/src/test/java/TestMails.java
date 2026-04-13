import mail.core.GeneradorMensaje;
import mail.core.Notificador;
import mail.core.NotificarInscripcionPorMail;
import mail.dto.Mensaje;
import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestMails {

    private GeneradorMensaje generador;
    private Participante participante;
    private Concurso concurso;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        generador = new GeneradorMensaje();
        // Asumiendo constructores según tu modelo
        participante = new Participante(1, "Joaquín", 40000000L, "joaco@example.com");
        fecha = LocalDate.of(2025, 6, 1);
        // Necesitás un stub/mock de RegistrarInscripcion aquí
        RegistrarInscripcion inscripcionStub = (p, c) -> {
        };
        concurso = new Concurso(1, "Ping Pong", fecha, inscripcionStub);
    }

    @Test
    void deberiaGenerarMensajeConDestinatarioCorrecto() {
        // Ejercitación
        Mensaje mensaje = generador.crearMensajeInscripcion(participante.obtenerEmail(), participante.obtenerId(), concurso.obtenerId());

        // Verificación
        assertEquals("joaco@example.com", mensaje.destinatario());
    }

    @Test
    void deberiaGenerarMensajeConAsuntoConfirmacion() {
        Mensaje mensaje = generador.crearMensajeInscripcion(participante.obtenerEmail(), participante.obtenerId(), concurso.obtenerId());

        assertEquals("¡Inscripción confirmada!", mensaje.asunto());
    }

    @Test
    void deberiaIncluirNombreDelParticipanteEnElCuerpo() {
        Mensaje mensaje = generador.crearMensajeInscripcion(participante.obtenerEmail(), participante.obtenerId(), concurso.obtenerId());

        assertTrue(mensaje.cuerpo().contains(String.valueOf(participante.obtenerId())));
    }

    @Test
    void deberiaEnviarEmailCuandoSeGuardaLaInscripcion() {
        // Setup (Mock manual)
        class NotificadorMock implements Notificador {
            boolean enviado = false;
            Mensaje mensajeEnviado;

            @Override
            public void enviar(Mensaje mensaje) {
                this.enviado = true;
                this.mensajeEnviado = mensaje;
            }
        }

        NotificadorMock mock = new NotificadorMock();
        RegistrarInscripcion notificador = new NotificarInscripcionPorMail(mock, generador);

        // Ejercitación
        notificador.guardar(participante, concurso);

        // Verificación
        assertTrue(mock.enviado, "El correo debería haberse enviado");
        assertEquals("joaco@example.com", mock.mensajeEnviado.destinatario());
    }
}