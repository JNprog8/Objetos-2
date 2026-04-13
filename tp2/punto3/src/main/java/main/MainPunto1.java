package main;

import models.Concurso;
import models.Participante;
import models.RegistrarInscripcion;
import persistence.JDBCRegistrarInscripcion;
import persistence.RegistrarInscripcionATexto;

import java.time.LocalDate;

//Inscripciones
public class MainPunto1 {
    public static void main(String[] args) {
        String folder = "inscripcion_historico/";
        String dbUrl = "jdbc:sqlite:" + folder + "inscripciones.db";
        String txtPath = folder + "inscripciones.txt";
        LocalDate hoy = LocalDate.now();

        // Registro en Base de Datos
        RegistrarInscripcion registroDB = new JDBCRegistrarInscripcion(dbUrl);
        Concurso concursoDB = new Concurso(1, "Olimpíadas de Matemática",
                hoy, registroDB);

        Participante juan = new Participante(101, "Juan Perez", 30123456L, "juan@example.com");
        concursoDB.inscribirA(juan, hoy);
        System.out.println("La inscripción se guardo en: " + dbUrl);

        // Registro de Archivo a Texto
        RegistrarInscripcion registroTxt = new RegistrarInscripcionATexto(txtPath);
        Concurso concursoTxt = new Concurso(2, "Torneo de Ajedrez",
                hoy, registroTxt);

        Participante ana = new Participante(102, "Ana Garcia", 40123456L, "ana@example.com");
        concursoTxt.inscribirA(ana, hoy);
        System.out.println("Inscripción guardada en: " + txtPath);
    }
}
