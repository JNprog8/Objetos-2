package inscripcion;

import mail.FakeProveedorCorreo;
import mail.core.GeneradorMensaje;
import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InscripcionTest {

    private FakeArchivo fakeArchivo;
    private FakeBaseDatos fakeBaseDatos;
    private FakeProveedorCorreo fakeNotificadorEmail;
    private RegistrarInscripcion composite;
    private Participante participante;
    private ServicioInscripcion servicio;
    private int idConcurso = 10;
    private String nombreConcurso = "Concurso Senior Objetos";
    private LocalDate hoy = LocalDate.now();

    @BeforeEach
    void setUp() {
        fakeArchivo = new FakeArchivo();
        fakeBaseDatos = new FakeBaseDatos();
        fakeNotificadorEmail = new FakeProveedorCorreo();

        RegistrarInscripcion notificadorEmail = new NotificarInscripcionPorMail(
                fakeNotificadorEmail,
                new GeneradorMensaje()
        );

        composite = new Inscripcion(List.of(
                fakeArchivo,
                fakeBaseDatos,
                notificadorEmail
        ));

        servicio = new ServicioInscripcion();
        participante = new Participante(1, "Joaquín", 12345678, "joaquin@example.com");
    }

    @Test
    @DisplayName("Debería persistir en archivo al inscribir")
    void FakeRegistroTexto() {
        Concurso concurso = new Concurso(idConcurso, nombreConcurso, hoy, composite);
        servicio.inscribir(participante, concurso, hoy);

        assertTrue(fakeArchivo.fueInscripto(participante.obtenerId(), idConcurso));
    }

    @Test
    @DisplayName("Debería persistir en base de datos al inscribir")
    void FakeBaseDatos() {
        Concurso concurso = new Concurso(idConcurso, nombreConcurso, hoy, composite);
        servicio.inscribir(participante, concurso, hoy);

        assertTrue(fakeBaseDatos.fueInscripto(participante.obtenerId(), idConcurso));
    }

    @Test
    @DisplayName("Debería notificar por mail al inscribir")
    void FakeNotificarMail() {
        Concurso concurso = new Concurso(idConcurso, nombreConcurso, hoy, composite);
        servicio.inscribir(participante, concurso, hoy);

        assertEquals(1, fakeNotificadorEmail.cantidadDeCorreosEnviados());
        assertTrue(fakeNotificadorEmail.fueEnviadoA("joaquin@example.com"));
    }

    private static class FakeArchivo implements RegistrarInscripcion {
        private final List<String> registros = new ArrayList<>();

        @Override
        public void guardar(Participante p, Concurso c) {
            registros.add(p.obtenerId() + "-" + c.obtenerId());
        }

        public boolean fueInscripto(int idPart, int idConc) {
            return registros.contains(idPart + "-" + idConc);
        }
    }

    private static class FakeBaseDatos implements RegistrarInscripcion {
        private final List<String> registros = new ArrayList<>();

        @Override
        public void guardar(Participante p, Concurso c) {
            registros.add(p.obtenerId() + "-" + c.obtenerId());
        }

        public boolean fueInscripto(int idPart, int idConc) {
            return registros.contains(idPart + "-" + idConc);
        }
    }
}
