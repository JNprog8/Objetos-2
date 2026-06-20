package ejercicio2.database;

import ejercicio2.model.Concurso;
import ejercicio2.model.ConcursoRepository;
import ejercicio2.model.Inscripto;
import ejercicio2.model.InscriptosRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAdapter implements ConcursoRepository, InscriptosRepository {
    private static final String CONCURSOS_QUERY = "SELECT id, nombre, fecha_inicio, fecha_fin FROM concursos";
    private static final String INSCRIPTO_INSERT = "INSERT INTO inscriptos(apellido, nombre, telefono, email, id_concurso) VALUES(?,?,?,?,?)";
    private final String url;
    private final String username;
    private final String password;

    public JdbcAdapter(String url) {
        this(url, "", "");
    }

    public JdbcAdapter(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Concurso> todosLosConcursos() {
        List<Concurso> lista = new ArrayList<>();
        try (Connection conn = nuevaConexion();
             PreparedStatement ps = conn.prepareStatement(CONCURSOS_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Concurso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        toLocalDate(rs.getDate("fecha_inicio")),
                        toLocalDate(rs.getDate("fecha_fin"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer concursos desde la base de datos", e);
        }
        return lista;
    }

    @Override
    public void guardarInscripcion(Inscripto inscripto) {
        try (Connection conn = nuevaConexion();
             PreparedStatement ps = conn.prepareStatement(INSCRIPTO_INSERT)) {
            ps.setString(1, inscripto.apellido());
            ps.setString(2, inscripto.nombre());
            ps.setString(3, inscripto.telefono());
            ps.setString(4, inscripto.email());
            ps.setInt(5, inscripto.idConcurso());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar inscripcion", e);
        }
    }

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private java.time.LocalDate toLocalDate(Date date) {
        return date.toLocalDate();
    }
}
