package punto1.main;

import punto1.model.client.Persona;
import punto1.model.infrastructure.DBConnection;
import punto1.model.infrastructure.PersonaDao;
import punto1.model.realsubject.Telefono;

/**
 * El siguiente programa consta de una clase Persona y una clase Teléfono, donde una persona
 * puede tener uno o varios teléfonos. La clase PersonaDao que permite obtener de una base de
 * datos una instancia de Persona dado su identificador, y un Main que obtiene una persona e
 * imprime su nombre y los teléfonos que posee. Para poder ejecutar este programa, en su base de
 * datos preferida, genere las siguientes tablas (que permitirán modelar a personas y su relación
 * uno a muchos con teléfonos):
 * - personas (id: int, nombre: varchar(100));
 * - telefonos (id: int, numero: varchar(20), idPersona: int); idPersona es foraña de personas.
 * <p>
 * Además, implemente el metodo privado PersonasDao#obtenerConexion() a su gusto.
 * Finalmente, inserte una persona con varios teléfonos y ejecute el metodo Main#main para
 * comprobar su funcionamiento.
 * <p>
 * Como puede observar, el metodo PersonaDao#personaPorId realiza una consulta SQL
 * para obtener la persona y todos sus teléfonos, y devuelve una instancia de Persona, con su
 * colección de teléfonos.
 * Usted advierte que hay otros clientes (además de Main#main) de PersonaDao que no
 * necesitan tener la colección de teléfonos, porque no invocan el metodo Persona#telefonos.
 * Con lo cual podría evitarse realizar una consulta SQL de junta (join) entre dos tablas para
 * la mayoría de los casos.
 * Utilice el patrón proxy y modifique el metodo PersonaDao#personaPorId de modo tal que
 * la colección de teléfonos en Persona se popule únicamente si se invoca al metodo
 * Persona#telefonos. Las clases Main, Persona y Telefono no deben modificarse.
 * Indique que clases del código entregado son el Cliente, el Proxy y el SujetoReal.
 * Ayuda: La interfaz Set definida en Persona corresponde al Sujeto del patrón Proxy.
 */
public class Main {

    public static void main(String[] args) {
        DBConnection.inicializarBD();
        PersonaDao dao = new PersonaDao();
        Persona persona = dao.personaPorId(1);

        if (persona == null) {
            System.out.println("No se encontró la persona.");
            return;
        }

        System.out.println("Nombre: " + persona.nombre());

        System.out.println("Consultando teléfonos (esto debería disparar la carga)...");
        for (Telefono telefono : persona.telefonos()) {
            System.out.println("Teléfono: " + telefono);
        }
    }
}
