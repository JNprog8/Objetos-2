package observer.punto4.db;

import observer.punto4.model.Participante;
import observer.punto4.model.observer.Observer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCRegistroParticipante implements Observer {
    private static final Logger LOGGER = Logger.getLogger(JDBCRegistroParticipante.class.getName());
    private final String url;
    private final String user;
    private final String password;

    public JDBCRegistroParticipante(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void update(Participante p) {
        String sql = "INSERT INTO participantes (nombre, telefono, region, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getRegion());
            ps.setString(4, p.getEmail());

            if (ps.executeUpdate() > 0) {
                LOGGER.info(() -> "Participante persistido: " + p.getNombre());
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al persistir participante", e);
            throw new RuntimeException("Error de base de datos al registrar participante.");
        }
    }
}
