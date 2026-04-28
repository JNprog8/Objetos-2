package oop2.tp3.ejercicio1;

import oop2.tp3.ejercicios.ejercicio1.Alquiler;
import oop2.tp3.ejercicios.ejercicio1.Cliente;
import oop2.tp3.ejercicios.ejercicio1.CopiaLibro;
import oop2.tp3.ejercicios.ejercicio1.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlquilerSinRefactor {

    private Cliente cliente;
    private Libro elQuijote;
    private Libro elPrincipito;
    private Libro milNovecientosOchentaYCuatro;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Luis");
        elQuijote = new Libro("El Quijote", Libro.REGULARES);
        elPrincipito = new Libro("El Principito", Libro.INFANTILES);
        milNovecientosOchentaYCuatro = new Libro("1984", Libro.NUEVO_LANZAMIENTO);
    }

    @Test
    void testAlquilerRegularBajoLimite() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(elQuijote), 2));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        assertEquals(2.0, (double) resultado[0], "Deuda para 2 días regulares debería ser 2.0");
        assertEquals(1, (int) resultado[1], "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerRegularSobreLimite() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(elQuijote), 3));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        // 2 + (3-2)*1.5 = 3.5
        assertEquals(3.5, (double) resultado[0], "Deuda para 3 días regulares debería ser 3.5");
        assertEquals(1, (int) resultado[1], "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerNuevoLanzamientoUnDia() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(milNovecientosOchentaYCuatro), 1));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        // 1 * 3 = 3
        assertEquals(3.0, (double) resultado[0], "Deuda para 1 día de nuevo lanzamiento debería ser 3.0");
        assertEquals(1, (int) resultado[1], "Puntos para 1 día de nuevo lanzamiento deberían ser 1");
    }

    @Test
    void testAlquilerNuevoLanzamientoDosDiasConBonus() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(milNovecientosOchentaYCuatro), 2));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        // 2 * 3 = 6
        assertEquals(6.0, (double) resultado[0], "Deuda para 2 días de nuevo lanzamiento debería ser 6.0");
        assertEquals(2, (int) resultado[1], "Puntos para 2 días de nuevo lanzamiento deberían ser 2 (incluye bonus)");
    }

    @Test
    void testAlquilerInfantilBajoLimite() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(elPrincipito), 3));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        assertEquals(1.5, (double) resultado[0], "Deuda para 3 días infantiles debería ser 1.5");
        assertEquals(1, (int) resultado[1], "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerInfantilSobreLimite() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(elPrincipito), 4));
        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        // 1.5 + (4-3)*1.5 = 3.0
        assertEquals(3.0, (double) resultado[0], "Deuda para 4 días infantiles debería ser 3.0");
        assertEquals(1, (int) resultado[1], "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testMultiplesAlquileres() {
        // Ejercitación
        cliente.alquilar(new Alquiler(new CopiaLibro(elQuijote), 3)); // 3.5 pesos, 1 punto
        cliente.alquilar(new Alquiler(new CopiaLibro(elPrincipito), 4)); // 3.0 pesos, 1 punto
        cliente.alquilar(new Alquiler(new CopiaLibro(milNovecientosOchentaYCuatro), 2)); // 6.0 pesos, 2 puntos

        Object[] resultado = cliente.calcularDeudaYPuntosObtenidos();

        // Verificación
        // Total = 3.5 + 3.0 + 6.0 = 12.5
        // Puntos = 1 + 1 + 2 = 4
        assertEquals(12.5, (double) resultado[0], "Deuda total incorrecta para múltiples alquileres");
        assertEquals(4, (int) resultado[1], "Puntos totales incorrectos para múltiples alquileres");
    }
}