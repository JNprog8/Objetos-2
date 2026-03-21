/**
 * MAIN - Versión Anémica
 *
 * El cliente (Main) necesita:
 * 1. Crear el objeto Tiempo (solo datos)
 * 2. Crear el TiempoService (toda la lógica)
 * 3. Pasarle el objeto Tiempo al servicio para hacer cualquier operación
 *
 * Esto es el síntoma del modelo anémico: el objeto no puede hacer
 * nada por sí mismo, siempre necesita un servicio externo que lo "opere".
 */

public class Main {
      public static void main(String[] args) {
          var tiempo = new Tiempo(2020, 4, 3);
          var tiempoService = new TiempoService();

          String formatoLargo = tiempoService.formatoLargo(tiempo);
          String formatoCorto = tiempoService.formatoCorto(tiempo);

          System.out.println("Formato Largo: " + formatoLargo);
          System.out.println("Formato Corto: " + formatoCorto);
      }
 }