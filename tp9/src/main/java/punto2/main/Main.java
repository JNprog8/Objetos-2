package punto2.main;

import punto2.model.Calculadora;

/**
 * En el repositorio en el ejercicio 2 tiene la implementación de una calculadora con la
 * siguientes responsabilidades:
 * <p>
 * > **mostrar** que retorna el valor acumulado.
 * > **borrar** vuelve a cero el valor acumulado.
 * > **valor(unValor)** asigna el valor acumulado.
 * > **mas** provoca que la calculadora espere un nuevo valor. Si a continuación se le envía el
 * mensaje valor(unValor) la calculadora sumará el valor recibido como parámetro al valor
 * actual acumulado y guardará el resultado en esta última.
 * <p>
 * Si la calculadora está esperando un valor (luego de una operación aritmética) y se le envía
 * cualquier otro mensaje, entonces pasará a un estado de error. Sólo saldrá de ahí si se le envía el
 * mensaje borrar.
 * Cuando la calculadora está en estado de error, el mensaje mostrar retorna el String “La
 * calculadora está en estado de error”.
 * Implemente las demás operaciones aritméticas: menos, dividido y por: que actúan de manera
 * similar al mensaje mas. Ahora, la calculadora también entra en estado de error si se intenta
 * dividir por cero.
 * a) Refactorice aplicando el patrón State.
 * b) Realice un diagrama de clases.
 */
public class Main {
    static void main() {
        Calculadora calculadora = new Calculadora();

        System.out.println("Estado inicial: " + calculadora.estado());

        calculadora.valor(10);

        System.out.println("Estado después de ingresar operando: " + calculadora.estado());

        calculadora.mas();
        System.out.println("Estado después de ingresar una operacion: " + calculadora.estado());
        calculadora.mas();
        System.out.println("Estado después de ingresar otra operacion: " + calculadora.estado());
        calculadora.borrar();

        System.out.println("Estado después de ingresar operando cero: " + calculadora.estado());

        calculadora.valor(10);

        System.out.println("Resultado mostrar: " + calculadora.mostrar());
    }
}
