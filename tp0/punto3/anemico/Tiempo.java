import java.time.LocalDate;

/**
 * MODELO ANÉMICO
 *
 * Esta clase es ejemplo de "Anemic Domain Model"
 * El Anti-patrón descrito por Martin Fowler:
 *
 * - Solo contiene datos (un LocalDate)
 * - No tiene NINGÚN comportamiento ni lógica de negocio
 * - Es una "bolsa de getters y setters" -> Simple
 * - Toda la lógica de formateo vive en TiempoService (clase externa)
 *
 * Problema: No cumple el principio fundamental de OOP de
 * combinar datos + comportamiento en un mismo objeto.
 */

public class Tiempo {
    private LocalDate fecha;

    public Tiempo(int anio, int mes, int dia){
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    public LocalDate getFecha(){return this.fecha;}

    public void setFecha(int anio, int mes, int dia) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }
}