package oop2.tp3.ejercicio1;

import oop2.tp3.refactorizado.ejercicio1.dominio.alquiler.*;
import oop2.tp3.refactorizado.ejercicio1.dominio.categoria.*;
import oop2.tp3.refactorizado.ejercicio1.dominio.cliente.Cliente;
import oop2.tp3.refactorizado.ejercicio1.dominio.producto.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlquilerRefactorizado {

    private Cliente cliente;
    private Libro elQuijote;
    private Libro elPrincipito;
    private Libro milNovecientosOchentaYCuatro;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Luis");
        elQuijote = new Libro("El Quijote", new CategoriaRegular());
        elPrincipito = new Libro("El Principito", new CategoriaInfantil());
        milNovecientosOchentaYCuatro = new Libro("1984", new CategoriaNuevoLanzamiento());
    }

    @Test
    void testAlquilerRegularBajoLimite() {
        cliente.alquilar(new CopiaLibro(elQuijote, 1), 2);
        
        assertEquals(2.0, cliente.totalMonto(), "Deuda para 2 días regulares debería ser 2.0");
        assertEquals(1, cliente.totalPuntos(), "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerRegularSobreLimite() {
        cliente.alquilar(new CopiaLibro(elQuijote, 1), 3);
        
        // 2 + (3-2)*1.5 = 3.5
        assertEquals(3.5, cliente.totalMonto(), "Deuda para 3 días regulares debería ser 3.5");
        assertEquals(1, cliente.totalPuntos(), "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerNuevoLanzamientoUnDia() {
        cliente.alquilar(new CopiaLibro(milNovecientosOchentaYCuatro, 1), 1);
        
        // 1 * 3 = 3
        assertEquals(3.0, cliente.totalMonto(), "Deuda para 1 día de nuevo lanzamiento debería ser 3.0");
        assertEquals(1, cliente.totalPuntos(), "Puntos para 1 día de nuevo lanzamiento deberían ser 1");
    }

    @Test
    void testAlquilerNuevoLanzamientoDosDiasConBonus() {
        cliente.alquilar(new CopiaLibro(milNovecientosOchentaYCuatro, 1), 2);
        
        // 2 * 3 = 6
        assertEquals(6.0, cliente.totalMonto(), "Deuda para 2 días de nuevo lanzamiento debería ser 6.0");
        assertEquals(2, cliente.totalPuntos(), "Puntos para 2 días de nuevo lanzamiento deberían ser 2 (incluye bonus)");
    }

    @Test
    void testAlquilerInfantilBajoLimite() {
        cliente.alquilar(new CopiaLibro(elPrincipito, 1), 3);
        
        assertEquals(1.5, cliente.totalMonto(), "Deuda para 3 días infantiles debería ser 1.5");
        assertEquals(1, cliente.totalPuntos(), "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testAlquilerInfantilSobreLimite() {
        cliente.alquilar(new CopiaLibro(elPrincipito, 1), 4);
        
        // 1.5 + (4-3)*1.5 = 3.0
        assertEquals(3.0, cliente.totalMonto(), "Deuda para 4 días infantiles debería ser 3.0");
        assertEquals(1, cliente.totalPuntos(), "Puntos para un alquiler deberían ser 1");
    }

    @Test
    void testReporte() {
        cliente.alquilar(new CopiaLibro(elQuijote, 1), 3); // 3.5
        cliente.alquilar(new CopiaLibro(elPrincipito, 1), 4); // 3.0
        
        Reporte reporte = new Reporte();
        String resumen = reporte.generarResumen(cliente);
        
        assertTrue(resumen.contains("Luis"), "El reporte debería contener el nombre del cliente");
        assertTrue(resumen.contains("El Quijote"), "El reporte debería contener el nombre del libro");
        assertTrue(resumen.contains("6.5"), "El reporte debería contener el monto total");
        assertTrue(resumen.contains("2"), "El reporte debería contener los puntos totales");
    }

    @Test
    void testSinStock() {
        CopiaLibro sinStock = new CopiaLibro(elQuijote, 0);
        assertThrows(IllegalStateException.class, () -> {
            cliente.alquilar(sinStock, 1);
        }, "Debería lanzar IllegalStateException si no hay stock");
    }
}