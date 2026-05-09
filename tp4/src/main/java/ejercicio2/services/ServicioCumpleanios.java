package ejercicio2.services;

import ejercicio2.models.*;

public class ServicioCumpleanios {

    private final Importar importador;
    private final Notificador notificador;
    private final MensajeCumpleanios mensaje;
    private final Reloj reloj;

    public ServicioCumpleanios(Importar importador,
                               Notificador notificador,
                               MensajeCumpleanios mensaje,
                               Reloj reloj) {
        this.importador = importador;
        this.notificador = notificador;
        this.mensaje = mensaje;
        this.reloj = reloj;
    }

    public void ejecutar() {
        importador.importarEmpleados()
                .stream()
                .filter(e -> e.cumpleAniosHoy(reloj.hoy()))
                .forEach(e -> e.celebrarCumple(notificador, mensaje));
    }
}