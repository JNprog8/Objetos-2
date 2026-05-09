package ejercicio2.main;

import ejercicio2.models.*;
import ejercicio2.services.ImportarArchivoTexto;
import ejercicio2.services.NotificadorMail;
import ejercicio2.services.ServicioCumpleanios;

public class Main {

    public static void main(String[] args) {

        Importar importador = new ImportarArchivoTexto("empleados.txt");

        Notificador notificador = new NotificadorMail(new MailConfig());

        MensajeCumpleanios mensaje = new MensajeSimple();

        Reloj reloj = new RelojSistema();

        ServicioCumpleanios servicio =
                new ServicioCumpleanios(importador, notificador, mensaje, reloj);

        servicio.ejecutar();
    }
}
