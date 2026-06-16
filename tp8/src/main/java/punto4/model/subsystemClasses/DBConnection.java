package punto4.model.subsystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:personas.db";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBD() {
        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement()) {

            //tablas
            stmt.execute("CREATE TABLE IF NOT EXISTS personas (id INTEGER PRIMARY KEY, nombre VARCHAR(100))");
            stmt.execute("CREATE TABLE IF NOT EXISTS telefonos (id INTEGER PRIMARY KEY, numero VARCHAR(20), persona_id INTEGER, FOREIGN KEY(persona_id) REFERENCES personas(id))");

            stmt.execute("INSERT OR IGNORE INTO personas (id, nombre) VALUES (1, 'Lionel Messi')");
            stmt.execute("INSERT OR IGNORE INTO telefonos (id, numero, persona_id) VALUES (1, '123-456', 1)");
            stmt.execute("INSERT OR IGNORE INTO telefonos (id, numero, persona_id) VALUES (2, '789-012', 1)");

        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }
}
