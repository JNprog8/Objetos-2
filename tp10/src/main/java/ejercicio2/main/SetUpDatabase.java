package ejercicio2.main;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SetUpDatabase {
    private final String connection;
    private final String username;
    private final String password;

    public SetUpDatabase(String connection, String username, String password) {
        this.connection = connection;
        this.username = username;
        this.password = password;
    }

    public void inicializar() {
        try (var connection = DriverManager.getConnection(this.connection, username, password)) {
            var stmt = connection.createStatement();
            dropTableInscriptos(stmt);
            dropTableConcursos(stmt);
            createTableConcursos(stmt);
            createTableInscriptos(stmt);
            insertSampleData(connection);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inicializar la base de datos de concursos", e);
        }
    }

    private void dropTableInscriptos(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE inscriptos");
        } catch (Exception ignored) {
        }
    }

    private void dropTableConcursos(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE concursos");
        } catch (Exception ignored) {
        }
    }

    private void createTableConcursos(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE concursos ("
                + "id INT NOT NULL PRIMARY KEY, "
                + "nombre VARCHAR(255) NOT NULL, "
                + "fecha_inicio DATE NOT NULL, "
                + "fecha_fin DATE NOT NULL)");
    }

    private void createTableInscriptos(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE inscriptos ("
                + "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "apellido VARCHAR(255) NOT NULL, "
                + "nombre VARCHAR(255) NOT NULL, "
                + "telefono VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) NOT NULL, "
                + "id_concurso INT NOT NULL, "
                + "CONSTRAINT fk_concurso FOREIGN KEY (id_concurso) REFERENCES concursos(id))");
    }

    private void insertSampleData(java.sql.Connection connection) throws SQLException {
        String sql = "INSERT INTO concursos(id, nombre, fecha_inicio, fecha_fin) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            LocalDate hoy = LocalDate.now();
            insertarConcurso(ps, 1, "Concurso vigente", hoy.minusDays(2), hoy.plusDays(10));
            insertarConcurso(ps, 2, "Concurso cerrado", hoy.minusDays(30), hoy.minusDays(1));
            insertarConcurso(ps, 3, "Maraton de Programacion Java", hoy.minusMonths(1), hoy.plusMonths(2));
            insertarConcurso(ps, 4, "Concurso de Fotografia Urbana", hoy.minusDays(10), hoy.plusDays(20));
            insertarConcurso(ps, 5, "Hackathon Smart Cities", hoy.minusWeeks(2), hoy.plusWeeks(4));
            insertarConcurso(ps, 6, "Feria de Ciencias 2027", hoy.plusMonths(6), hoy.plusMonths(12));
        }
    }

    private void insertarConcurso(
            PreparedStatement ps,
            int id,
            String nombre,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setDate(3, java.sql.Date.valueOf(fechaInicio));
        ps.setDate(4, java.sql.Date.valueOf(fechaFin));
        ps.executeUpdate();
    }
}
