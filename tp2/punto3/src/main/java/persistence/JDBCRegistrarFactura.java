package persistence;

import logica.facturacion.RegistrarFactura;
import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JDBCRegistrarFactura implements RegistrarFactura {

    private final String url;
    private final int numeroMesa;

    public JDBCRegistrarFactura(String url, int numeroMesa) {
        this.url = url;
        this.numeroMesa = numeroMesa;
        asegurarCarpetaExistente(url);
        crearTablaSiNoExiste();
    }

    private void asegurarCarpetaExistente(String url) {
        String path = url.replace("jdbc:sqlite:", "");
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ventas (
                    id_venta INTEGER PRIMARY KEY AUTOINCREMENT,
                    fecha_hora TEXT NOT NULL,
                    monto_facturado REAL NOT NULL,
                    numero_mesa INTEGER NOT NULL
                );
                """;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la tabla de ventas", e);
        }
    }

    @Override
    public void registrar(double monto) {
        String sql = "INSERT INTO ventas (fecha_hora, monto_facturado, numero_mesa) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String ahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            pstmt.setString(1, ahora);
            pstmt.setDouble(2, monto);
            pstmt.setInt(3, numeroMesa);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar la venta en la base de datos", e);
        }
    }
}
