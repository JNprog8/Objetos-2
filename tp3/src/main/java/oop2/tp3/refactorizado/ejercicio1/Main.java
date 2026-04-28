package oop2.tp3.refactorizado.ejercicio1;

import oop2.tp3.refactorizado.ejercicio1.dominio.alquiler.Reporte;
import oop2.tp3.refactorizado.ejercicio1.dominio.categoria.CategoriaInfantil;
import oop2.tp3.refactorizado.ejercicio1.dominio.categoria.CategoriaNuevoLanzamiento;
import oop2.tp3.refactorizado.ejercicio1.dominio.categoria.CategoriaRegular;
import oop2.tp3.refactorizado.ejercicio1.dominio.cliente.Cliente;
import oop2.tp3.refactorizado.ejercicio1.dominio.producto.CopiaLibro;
import oop2.tp3.refactorizado.ejercicio1.dominio.producto.Libro;

public class Main {
    public static void main(String[] args) {
        // 1. Crear Libros con sus categorías
        Libro elQuijote = new Libro("El Quijote", new CategoriaRegular());
        Libro elPrincipito = new Libro("El Principito", new CategoriaInfantil());
        Libro duna = new Libro("Dune: Parte 2", new CategoriaNuevoLanzamiento());

        // 2. Crear Copias con stock inicial
        CopiaLibro copiaQuijote = new CopiaLibro(elQuijote, 2);
        CopiaLibro copiaPrincipito = new CopiaLibro(elPrincipito, 1);
        CopiaLibro copiaDuna = new CopiaLibro(duna, 5);

        // 3. Crear Cliente
        Cliente cliente = new Cliente("Joaquín");

        // 4. Realizar Alquileres
        try {
            System.out.println("Realizando alquileres...");
            cliente.alquilar(copiaQuijote, 3);    // Regular: 2 + (3-2)*1.5 = 3.5
            cliente.alquilar(copiaPrincipito, 5); // Infantil: 1.5 + (5-3)*1.5 = 4.5
            cliente.alquilar(copiaDuna, 2);       // Nuevo: 2 * 3 = 6.0 (Bonus: 2 puntos)
            
            // 5. Generar y mostrar el Reporte
            Reporte reporte = new Reporte();
            System.out.println("\n" + reporte.generarResumen(cliente));

        } catch (IllegalStateException e) {
            System.err.println("Error en el alquiler: " + e.getMessage());
        }
    }
}