package observer.punto4.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetUpDatabase {
    private static final Logger LOGGER = Logger.getLogger(SetUpDatabase.class.getName());
    private final String url;
    private final String user;
    private final String password;

    public SetUpDatabase(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void inicializar() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            
            ensureCleanState(stmt);
            createSchema(stmt);
            
            LOGGER.info("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fatal al inicializar la base de datos", e);
            throw new RuntimeException("No se pudo configurar el entorno de datos.", e);
        }
    }

    private void ensureCleanState(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE participantes");
        } catch (SQLException e) {
            // SQLState 42Y55: Table/View does not exist in Derby
            if (!"42Y55".equals(e.getSQLState())) {
                LOGGER.warning("Aviso al intentar limpiar tabla: " + e.getMessage());
            }
        }
    }

    private void createSchema(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE participantes ("
                + "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "nombre VARCHAR(255), "
                + "telefono VARCHAR(255), "
                + "region VARCHAR(255), "
                + "email VARCHAR(255))";
        stmt.executeUpdate(sql);
    }
}
