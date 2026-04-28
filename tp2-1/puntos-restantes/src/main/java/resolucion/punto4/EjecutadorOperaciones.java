package resolucion.punto4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Clase responsable de la infraestructura con JDBC.
// Centraliza la gestión de conexiones, el ciclo de vida de las sentencias
// y la estrategia de transacciones para evitar código duplicado
public class EjecutadorOperaciones {

    private String jdbcUrl;

    public EjecutadorOperaciones(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    // Ejecuta una operación SQL dentro de una transacción.
    // Se encarga de abrir la conexión, preparar la sentencia,
    // manejar el commit y realizar el rollback en caso de error
    public void ejecutarTransaccion(String sql, OperacionesBaseDatos operacion) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            operacion.ejecutar(statement);
            connection.commit();
        } catch (SQLException e) {
            // El rollback es implícito al cerrar la conexión en el try-with-resources 
            // si no se invocó commit(), pero lanzamos una excepción descriptiva.
            throw new RuntimeException("Error en la operación de base de datos. Se ha realizado rollback.", e);
        }
    }
}