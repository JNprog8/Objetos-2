import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MODELO NO ANEMICO
 *
 * - DATOS (la fecha) con COMPORTAMIENTO (los métodos de formateo)
 * - El objeto no necesita de un servicio externos para resolver su propia lógica de dominio
 *   realiza (por si mismo) su responsabilidad.
 * - Encapsula (correctamente) sus datos y las operaciones sobre ellos
 *
 * Respeta el Principio de OOP: "Tell, don't ask"
 * En lugar de pedirle los datos al objeto para procesarlos afuera,
 * el objeto realiza su responsabilidad por sí mismo, sin exponer su estado interno.
 */

public class Tiempo {
    private LocalDate fecha;

    public Tiempo(int anio, int mes, int dia) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    public String formatoLargo() {
        // El objeto "Tiempo" sabe cómo formatearse a sí mismo. El comportamiento pedido en el Dominio.
        var formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        return this.fecha.format(formatter);
    }

    public String formatoCorto() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fecha.format(formatter);
    }
//    //No es necesario getters ni setters
//    private LocalDate getFecha() {
//        return this.fecha;
//    }
//    private void setFecha(int anio, int mes, int dia) {
//        this.fecha = LocalDate.of(anio, mes, dia);
//    }
}