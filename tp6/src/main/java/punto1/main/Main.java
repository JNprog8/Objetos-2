package punto1.main;

import punto1.model.adaptee.MotorElectrico;
import punto1.model.adapter.MotorElectricoAdapter;
import punto1.model.target.MotorComun;
import punto1.model.target.MotorEconomico;

/**
 * Un sistema trabaja con diferentes tipos de motores (Común, Económico) que comparten
 * características comunes así como su funcionamiento, que está dado por operaciones como
 * arrancar, acelerar y apagar.
 * Se desea incorporar al sistema una clase ya existente de tipo motor eléctrico con un
 * funcionamiento diferente al de los demás. Estos motores pueden realizar operaciones como
 * conectar y activar, moverMasRapido, detener y desconectar.
 * Se debe adaptar la nueva clase de forma que no se vea afectada la lógica de la aplicación
 * cliente que interactúa con las clases que representan los motores.
 * a) Aplique el patrón Adapter, utilizando un adaptador de objetos, para diseñar el modelo de
 * clases que de solución al problema planteado.
 * b) Implemente la solución en Java, utilizando mensajes informativos por pantalla de la
 * operación que se está realizando.
 */

public class Main {

    public static void main(String[] args) {

        var cliente = new Cliente();

        var motorComun = new MotorComun();
        var motorEconomico = new MotorEconomico();
        var motorElectrico = new MotorElectricoAdapter(new MotorElectrico());

        System.out.println("USANDO MOTOR COMÚN");
        cliente.probarMotor(motorComun);

        System.out.println("USANDO MOTOR ECONÓMICO");
        cliente.probarMotor(motorEconomico);

        System.out.println("USANDO MOTOR ELÉCTRICO ADAPTADO");
        cliente.probarMotor(motorElectrico);
    }
}
