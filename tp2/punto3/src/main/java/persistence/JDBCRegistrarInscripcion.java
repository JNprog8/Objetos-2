package persistence;

import models.RegistrarInscripcion;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;

public class JDBCRegistrarInscripcion implements RegistrarInscripcion {

    private final String url;

    public JDBCRegistrarInscripcion(String url) {
        this.url = url;
        asegurarCarpetaExistente(url);
        crearTablaSiNoExiste();
    }

    private void asegurarCarpetaExistente(String url) {
        // Extraemos la ruta del archivo de la URL jdbc:sqlite:ruta/archivo.db
        String path = url.replace("jdbc:sqlite:", "");
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS inscripciones (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    fecha TEXT NOT NULL,
                    id_participante INTEGER NOT NULL,
                    id_concurso INTEGER NOT NULL
                );
                """;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la tabla de inscripciones", e);
        }
    }

    @Override
    public void guardar(models.Participante participante, models.Concurso concurso) {
        String sql = "INSERT INTO inscripciones (fecha, id_participante, id_concurso) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, java.time.LocalDate.now().toString());
            pstmt.setInt(2, participante.obtenerId());
            pstmt.setInt(3, concurso.obtenerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la inscripción en la base de datos", e);
        }
    }
}
