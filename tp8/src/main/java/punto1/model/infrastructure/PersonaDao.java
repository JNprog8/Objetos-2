package punto1.model.infrastructure;

import punto1.model.client.Persona;
import punto1.model.proxy.TelefonosProxy;
import punto1.model.realsubject.Telefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class PersonaDao {
    private static final String SQL_SELECT_PERSONA = "SELECT nombre FROM personas WHERE id = ?";

    public Persona personaPorId(int id) {
        try (Connection conn = DBConnection.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(SQL_SELECT_PERSONA)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                return null;
            }

            String nombre = rs.getString("nombre");
            Set<Telefono> telefonos = new TelefonosProxy(id);

            return new Persona(id, nombre, telefonos);

        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar la persona con ID: " + id, e);
        }
    }
}
