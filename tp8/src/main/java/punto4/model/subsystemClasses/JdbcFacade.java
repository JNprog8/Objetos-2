package punto4.model.subsystemClasses;

import punto4.model.facade.DBFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcFacade implements DBFacade {
    private Connection connection;

    @Override
    public void open() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DBConnection.obtenerConexion();
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo abrir la conexión a la base de datos", e);
        }
    }

    @Override
    public List<Map<String, String>> queryResultAsAsociation(String sql) {
        ensureOpen();
        List<Map<String, String>> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getString(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + sql, e);
        }
        return results;
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        ensureOpen();
        List<String[]> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + sql, e);
        }
        return results;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión", e);
        }
    }

    private void ensureOpen() {
        try {
            if (connection == null || connection.isClosed()) {
                throw new RuntimeException("La conexión no está abierta.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el estado de la conexión", e);
        }
    }
}
