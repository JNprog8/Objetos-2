package strategy.punto2.main;

import strategy.punto2.model.concreteStrategy.FormatoCorto;
import strategy.punto2.model.concreteStrategy.FormatoLargo;
import strategy.punto2.model.context.Persona;

import java.time.LocalDate;

/**
 * Implemente en Java una clase Persona que responda al mensaje fechaNacimiento(). Este
 * mensaje devuelve un String con la fecha de nacimiento de la persona. La fecha de nacimiento
 * puede ser:
 *  Corta: 3-06-1986
 *  Larga: 3 de Junio de 1986
 * Implemente utilizando el patrón Strategy. Implemente dos casos de test.
 */
public class Main {
    public static void main(String[] args) {

        var p1 = new Persona(LocalDate.of(1986, 6, 3), new FormatoCorto());

        var p2 = new Persona(LocalDate.of(1986, 6, 3), new FormatoLargo());

        System.out.println("Formato Corto: " + p1.fechaNacimiento()); // Corta: 3-06-1986
        System.out.println("Formato Largo: " + p2.fechaNacimiento()); // Larga: 3 de Junio de 1986
    }
}
