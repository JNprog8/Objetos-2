package oop2.tp3.refactorizado.ejercicio2.dominio;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Recaudacion {
    private static final String CSV_PATH = "src/main/resources/data.csv";
    private final List<Investment> inversiones;

    public Recaudacion(Importer importer) {
        this.inversiones = importer.importar();
    }

    // filtra inversiones basadas en el mapa de opciones
    public List<Map<String, String>> buscar(Map<String, String> opciones) {
        Stream<Investment> stream = inversiones.stream();

        // Aplicamos cada filtro en las opciones
        for (Map.Entry<String, String> filtro : opciones.entrySet()) {
            stream = stream.filter(inv -> inv.cumpleCon(filtro.getKey(), filtro.getValue()));
        }

        return stream
                .map(Investment::asMap)
                .collect(Collectors.toList());
    }

    // Por defecto usa el archivo original, pero lo ideal es inyectar el Importer
    public static List<Map<String, String>> where(Map<String, String> options) {
        Importer defaultImporter = new CsvImporter(CSV_PATH);
        return new Recaudacion(defaultImporter).buscar(options);
    }
}