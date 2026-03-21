/**
 * MAIN NO ANEMICO
 *
 * Diferencias con el modelo anémico:
 * - No se necesita ningún servicio externo.
 * - El objeto Tiempo hace todo por sí mismo.
 * - Más expresivo, limpio y sigue paradigma OOP
 *
 * El 'cliente' le "dice" al objeto qué hacer (Tell, don't ask),
 * en lugar de pedir datos y procesarlos afuera.
 */

public class Main {
    public static void main(String[] args) {
        var tiempo = new Tiempo(2020, 4, 3);

        String formatoLargo = tiempo.formatoLargo();
        String formatoCorto = tiempo.formatoCorto();

        System.out.println("Formato Largo: " + formatoLargo);
        System.out.println("Formato Corto: " + formatoCorto);
    }
}