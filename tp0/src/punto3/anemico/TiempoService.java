import java.time.format.DateTimeFormatter;

/**
 * SERVICIO - MODELO ANÉMICO
 *
 * Toda la lógica de negocio se extrae de los objetos de dominio, se maneja aquí.
 *
 * Resultado: Un diseño PROCEDURAL disfrazado de OOP:
 * - Los datos están en la clase Tiempo.
 * - El comportamiento está aca.
 * - Ambas cosas deberían estar JUNTAS, en el mismo objeto.
 */

public class TiempoService {
    public String formatoLargo(Tiempo tiempo){
        var fecha = tiempo.getFecha();
        var formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        return fecha.format(formatter);
    }

    public String formatoCorto(Tiempo tiempo){
        var fecha = tiempo.getFecha();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
}