package resolucion.punto4;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface OperacionesBaseDatos {
    void ejecutar(PreparedStatement statement) throws SQLException;
}