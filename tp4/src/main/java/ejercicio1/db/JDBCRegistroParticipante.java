package ejercicio1.db;

import ejercicio1.models.Participante;
import ejercicio1.models.RegistrarParticipante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCRegistroParticipante implements RegistrarParticipante {
    private String url;
    private String user;
    private String password;

    public JDBCRegistroParticipante(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void guardar(Participante participante) {
        String query = "insert into participantes(nombre, telefono, region) values(?,?,?)";
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement st = connection.prepareStatement(query);
        ) {
            st.setString(1, participante.getNombre());
            st.setString(2, participante.getTelefono());
            st.setString(3, participante.getRegion());
            int res = st.executeUpdate();
            if (res != 0) {
                System.out.println("Se registro correctamente al participante: " + participante.getNombre());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
