package ejercicio1.main;

import ejercicio1.controller.ParticipanteController;
import ejercicio1.db.JDBCRegistroParticipante;
import ejercicio1.models.Concurso;
import ejercicio1.ui.VentanaPrincipal;

import java.awt.EventQueue;
import java.sql.SQLException;

public class Main {
    public static final String CONNECTION_STRING = "jdbc:derby:memory:ventas;create=true";
    public static final String USERNAME = "app";
    public static final String PASSWORD = "app";

    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inicializarDatabase();
                    var registroParticipante = new JDBCRegistroParticipante(CONNECTION_STRING, USERNAME, PASSWORD);
                    var concurso = new Concurso(registroParticipante);
                    var controller = new ParticipanteController(concurso);
                    new VentanaPrincipal(controller).agregarParticipante();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private static void inicializarDatabase() {
        var jdbc = new SetUpDatabase(CONNECTION_STRING, USERNAME, PASSWORD);
        jdbc.inicializar();
    }
}
