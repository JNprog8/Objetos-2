package resolucion.punto4;

import java.sql.PreparedStatement;

/**
 * Responsable de las operaciones de definición de datos (DDL).
 */
public class UsuariosDDL {

    private static final String SQL_CREATE_TABLE = """
            CREATE TABLE usuarios (
                id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL UNIQUE
            )
            """;

    private EjecutadorOperaciones ejecutador;

    public UsuariosDDL(EjecutadorOperaciones ejecutador) {
        this.ejecutador = ejecutador;
    }

    public void crearTablaUsuarios() {
        ejecutador.ejecutarTransaccion(SQL_CREATE_TABLE, PreparedStatement::executeUpdate);
    }
}
