package ejercicio1.main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDatabase {
    private String connect;
    private final String username;
    private final String password;

    public SetUpDatabase(String connection, String username, String password) {
        this.connect = connection;
        this.username = username;
        this.password = password;
    }

    public void inicializar() {
        try (var connection = DriverManager.getConnection(connect, username, password)) {
            var stmt = connection.createStatement();
            dropTableConcurso(stmt);
            createTableParticipantes(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTableConcurso(Statement stmt) {
        try {
            //hago esto porque drop table falla si la tabla no existe
            //y Derby no soporta drop table if exists
            //try/catch sin lanzar la exception solo en inicializaciones como esta
            //no es una buena práctica hacer esto
            stmt.executeUpdate("DROP TABLE participantes");
        } catch (Exception e) {
            //no hagamos nada, creamos la tabla
        }
    }

    private void createTableParticipantes(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE participantes (id INT NOT NULL "
                + " primary key generated always as identity (start with 1, increment by 1), "
                + " nombre varchar(255), telefono varchar(255), region varchar(255))");
    }
}
