package punto3.main;

import punto3.model.component.Exportable;
import punto3.model.concreteComponent.Report;
import punto3.model.concreteDecorator.ConSobreescritura;
import punto3.model.concreteDecorator.SinSobreescritura;

import java.io.File;

/**
 * Supongamos la siguiente clase Reporte
 * a. Implemente la exportación.
 * b. Utilice el pattern Decorador para reescribir la funcionalidad de Reporte, de modo tal que le
 * permita escribir Reportes que exporten sin verificar si el archivo existe (o sea, lo sobreescriba)
 * y Reportes que no permitan sobrescribir el archivo.
 */
public class Main {

    private static final String NOMBRE_DEL_ARCHIVO_REPORTE = "src/main/java/punto3/reporte.txt";
    private static final String CONTENIDO = "Este es el contenido del reporte.";

    public static void main(String[] args) {
        var contenido = CONTENIDO;
        var file = new File(NOMBRE_DEL_ARCHIVO_REPORTE);

        // 1. Reporte que permite sobrescribir
        System.out.println("Exportando con sobrescritura...");
        var reporteConSobreescritura = new ConSobreescritura(new Report(contenido));
        reporteConSobreescritura.export(file);

        // 2. Reporte que NO permite sobrescribir
        System.out.println("Intentando exportar sin sobrescritura (debe fallar si el archivo existe)...");
        Exportable reporteSinSobreescritura = new SinSobreescritura(new Report(contenido));
        try {
            reporteSinSobreescritura.export(file);
        } catch (IllegalArgumentException e) {
            System.err.println("Error esperado: " + e.getMessage());
        }

        // Limpieza (para pruebas futuras), descomentar
        // file.delete();
    }
}
