package punto1.main;

import punto1.model.Puerta;

/**
 * Consideremos la utilización de una puerta que permite operaciones como abrir y cerrar. De
 * acuerdo al estado de la puerta, cada acción tiene un comportamiento diferente. Refactorice
 * el código escrito en el ejercicio 1 aplicando el patrón State. Realice un diagrama de clases.
 */
public class Main {
    static void main() {
        Puerta puerta = new Puerta();

        System.out.println(">Estado inicial: [" + puerta.estado() + "]");

        puerta.abrir();

        System.out.println(">Estado después de abrir: [" + puerta.estado() + "]");

        puerta.abrir();

        System.out.println(">Estado después de intentar abrir de nuevo: [" + puerta.estado() + "]");

        puerta.cerrar();

        System.out.println(">Estado después de cerrar: [" + puerta.estado() + "]");

        puerta.cerrar();

        System.out.println(">Estado después de intentar cerrar de nuevo: [" + puerta.estado() + "]");
    }
}