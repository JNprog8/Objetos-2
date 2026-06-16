package doubleDispatch.punto5.main;

import doubleDispatch.punto5.model.concreteElement.Disco;
import doubleDispatch.punto5.model.concreteElement.Libro;
import doubleDispatch.punto5.model.concreteElement.Revista;
import doubleDispatch.punto5.model.concreteVisitor.Deteriorado;
import doubleDispatch.punto5.model.concreteVisitor.Nuevo;
import doubleDispatch.punto5.model.element.Articulo;
import doubleDispatch.punto5.model.objectStructure.Prestamo;
import doubleDispatch.punto5.model.visitor.EstadoArticulo;

import java.time.LocalDate;

/**
 * Implementar un sistema de biblioteca/multimedia para gestionar préstamos de
 * artículos. El sistema debe calcular la duración del préstamo según:
 * a. el tipo de artículo (Libro, Disco, Revista),
 * b. su condición del artículo (Nuevo, Usado, Deteriorado).
 * Los artículos se pueden prestar y se debe conocer la persona a la que se presta la
 * fecha de inicio y la fecha de devolución calculada automáticamente. La cantidad de
 * días de préstamo varía según el tipo de artículo:
 * Libro: 1 día cada 100 páginas, redondeando hacia arriba.
 * Disco: Si su condición es deteriorada y la banda musical es menor a 1980, no
 * se puede prestar. Si su condición es deteriorada y la banda musical es mayor o igual
 * a 1980, se le resta 1 día al cálculo de días ( mínimo 1 día). En otra condición 3 días
 * si el año de creación de la banda musical es menor a 1980, 5 días en otro caso.
 * Revista: Sí su condición es deteriorada y si la fecha de publicación tiene más
 * de 10 años reduce 3 días y si no reduce 1 día (mínimo 1 día). En otra condición, 2
 * días si la cantidad de páginas es menor a 100, 3 días si es mayor a 100 y menor a
 * 2000 y 5 días si páginas es mayor a 2000.
 */
public class Main {
    public static void main(String[] args) {
        EstadoArticulo nuevo = new Nuevo();
        EstadoArticulo deteriorado = new Deteriorado();

        // 1. Libro 350 paginas -> 4 dias
        Articulo libro = new Libro("Clean Code", 350, nuevo);
        Prestamo p1 = new Prestamo("Juan", libro, LocalDate.now());
        System.out.println("Libro (350p, Nuevo): " + p1.fechaDevolucion().isEqual(p1.fechaInicio().plusDays(4)));

        // 2. Disco 1975, Nuevo -> 3 dias
        Articulo disco1 = new Disco("Pink Floyd", 1975, nuevo);
        Prestamo p2 = new Prestamo("Pedro", disco1, LocalDate.now());
        System.out.println("Disco (1975, Nuevo): " + p2.fechaDevolucion().isEqual(p2.fechaInicio().plusDays(3)));

        // 3. Disco 1985, Nuevo -> 5 dias
        Articulo disco2 = new Disco("Madonna", 1985, nuevo);
        Prestamo p3 = new Prestamo("Maria", disco2, LocalDate.now());
        System.out.println("Disco (1985, Nuevo): " + p3.fechaDevolucion().isEqual(p3.fechaInicio().plusDays(5)));

        // 4. Disco 1985, Deteriorado -> 4 dias
        Articulo disco3 = new Disco("Madonna", 1985, deteriorado);
        Prestamo p4 = new Prestamo("Jose", disco3, LocalDate.now());
        System.out.println("Disco (1985, Deteriorado): " + p4.fechaDevolucion().isEqual(p4.fechaInicio().plusDays(4)));

        // 5. Disco 1975, Deteriorado -> Exception
        try {
            Articulo disco4 = new Disco("Pink Floyd", 1975, deteriorado);
            new Prestamo("Luis", disco4, LocalDate.now());
            System.out.println("Disco (1975, Deteriorado): FAIL (should have thrown exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Disco (1975, Deteriorado): SUCCESS (threw exception)");
        }

        // 6. Revista 50 paginas, Nuevo -> 2 dias
        Articulo revista1 = new Revista("Poder", 50, LocalDate.now(), nuevo);
        Prestamo p5 = new Prestamo("Ana", revista1, LocalDate.now());
        System.out.println("Revista (50p, Nuevo): " + p5.fechaDevolucion().isEqual(p5.fechaInicio().plusDays(2)));

        // 7. Revista 150 paginas, Nuevo -> 3 dias
        Articulo revista2 = new Revista("National Geographic", 150, LocalDate.now(), nuevo);
        Prestamo p6 = new Prestamo("Rosa", revista2, LocalDate.now());
        System.out.println("Revista (150p, Nuevo): " + p6.fechaDevolucion().isEqual(p6.fechaInicio().plusDays(3)));

        // 8. Revista 2500 paginas, Nuevo -> 5 dias
        Articulo revista3 = new Revista("Encyclopedia", 2500, LocalDate.now(), nuevo);
        Prestamo p7 = new Prestamo("Beto", revista3, LocalDate.now());
        System.out.println("Revista (2500p, Nuevo): " + p7.fechaDevolucion().isEqual(p7.fechaInicio().plusDays(5)));

        // 9. Revista 150 paginas, 15 años antiguedad, Deteriorado -> 1 dia (3 - 3 = 0, min 1)
        Articulo revista4 = new Revista("Old Mag", 150, LocalDate.now().minusYears(15), deteriorado);
        Prestamo p8 = new Prestamo("Gaby", revista4, LocalDate.now());
        System.out.println("Revista (150p, 15y, Deteriorado): " + p8.fechaDevolucion().isEqual(p8.fechaInicio().plusDays(1)));

        // 10. Revista 150 paginas, 5 años antiguedad, Deteriorado -> 2 dias (3 - 1 = 2)
        Articulo revista5 = new Revista("Recent Mag", 150, LocalDate.now().minusYears(5), deteriorado);
        Prestamo p9 = new Prestamo("Dani", revista5, LocalDate.now());
        System.out.println("Revista (150p, 5y, Deteriorado): " + p9.fechaDevolucion().isEqual(p9.fechaInicio().plusDays(2)));
    }
}
