package repo.enrique.main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDatabase {
    private final String username;
    private final String pwd;
    private String conn;

    public SetUpDatabase(String conn, String username, String pwd) {
        this.conn = conn;
        this.username = username;
        this.pwd = pwd;
    }

    public void inicializar() {
        try (var connection = DriverManager.getConnection(conn, username, pwd)) {
            var stmt = connection.createStatement();
            dropTableVentas(stmt);
            createTableVentas(stmt);
            insertSampleData(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTableVentas(Statement stmt) {
        try {
            //hago esto porque drop table falla si la tabla no existe
            //y Derby no soporta drop table if exists
            //try/catch sin lanzar la exception solo en inicializaciones como esta
            //no es una buena práctica hacer esto
            stmt.executeUpdate("DROP TABLE ventas");
        } catch (Exception e) {
            //no hagamos nada, creamos la tabla
        }
    }

    private void createTableVentas(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE ventas (id_venta INT NOT NULL "
                + " primary key generated always as identity (start with 1, increment by 1), "
                + " fecha_venta timestamp, total float, litros_cargados float, tipo_combustible char(10))");
    }

    private void insertSampleData(Statement stmt) throws SQLException {
        stmt.executeUpdate("insert into ventas(fecha_venta, total, litros_cargados, tipo_combustible) "
                + "values('2024-04-06 12:10:00', 20000, 55, 'RESUPER')");
        stmt.executeUpdate("insert into ventas(fecha_venta, total, litros_cargados, tipo_combustible) "
                + "values('2024-03-02 09:03:00', 12000, 22, 'RESUPER')");
        stmt.executeUpdate("insert into ventas(fecha_venta, total, litros_cargados, tipo_combustible) "
                + "values('2024-02-01 21:30:00', 22000, 75, 'SUPER')");
    }
}
