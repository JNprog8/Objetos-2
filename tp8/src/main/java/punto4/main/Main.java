package punto4.main;

import punto4.model.subsystemClasses.DBConnection;
import punto4.model.subsystemClasses.JdbcFacade;

/**
 *
 * Implemente una Fachada para permitir realizar consultas SQL sin tener que lidiar
 * con la API JDBC de Java. La Fachada debe implementar la siguiente interfaz                                                                                                                                             │ │
 * Escriba un Main para probar la Fachada,
 * utilizando el modelo relacional creado para el ejercicio anterior
 */
public class Main {
    public static void main(String[] args) {
        DBConnection.inicializarBD();

        var facade = new JdbcFacade();

        try {
            facade.open();

            System.out.println("--- Consulta como Asociación ---");
            String query1 = "SELECT p.nombre, t.numero FROM personas p JOIN telefonos t ON p.id = t.persona_id";
            var associationResult = facade.queryResultAsAsociation(query1);

            for (var row : associationResult) {
                System.out.println("Fila: " + row);
            }

            System.out.println("\n--- Consulta como Arreglo ---");
            String query2 = "SELECT id, nombre FROM personas";
            var arrayResult = facade.queryResultAsArray(query2);

            for (var row : arrayResult) {
                System.out.println("Fila: [" + String.join(", ", row) + "]");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            facade.close();
        }
    }
}
