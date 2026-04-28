package resolucion.punto4;

/**
 * Usuarios es un DAO responsable de las operaciones de manipulación
 * de datos (DML) para Usuarios.
 */
public class Usuarios {

    private static final String SQL_INSERTAR = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
    private static final String SQL_ACTUALIZAR_EMAIL = "UPDATE usuarios SET email = ? WHERE id = ?";

    private EjecutadorOperaciones ejecutador;

    public Usuarios(EjecutadorOperaciones ejecutador) {
        this.ejecutador = ejecutador;
    }

    public void insertar(String nombre, String email) {
        ejecutador.ejecutarTransaccion(SQL_INSERTAR, statement -> {
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.executeUpdate();
        });
    }

    public void actualizarEmail(int id, String nuevoEmail) {
        ejecutador.ejecutarTransaccion(SQL_ACTUALIZAR_EMAIL, statement -> {
            statement.setString(1, nuevoEmail);
            statement.setInt(2, id);
            statement.executeUpdate();
        });
    }
}