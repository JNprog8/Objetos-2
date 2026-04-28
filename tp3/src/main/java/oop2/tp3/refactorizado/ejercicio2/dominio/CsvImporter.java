package oop2.tp3.refactorizado.ejercicio2.dominio;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvImporter implements Importer {
    private static final String ERROR_AL_LEER_EL_ARCHIVO_CSV = "Error al leer el archivo CSV: ";
    private String path;

    public CsvImporter(String path) {
        this.path = path;
    }

    @Override
    public List<Investment> importar() {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> allRows = reader.readAll();
            if (allRows.isEmpty()) return List.of();

            String[] header = allRows.get(0); //cabecera
            List<Investment> investments = new ArrayList<>();//inversiones

            // Procesa a partir de la segunda fila
            for (int i = 1; i < allRows.size(); i++) {
                investments.add(new Investment(mapearFila(header, allRows.get(i))));
            }
            return investments;
        } catch (IOException e) {
            throw new RuntimeException(ERROR_AL_LEER_EL_ARCHIVO_CSV + path, e);
        }
    }

    private Map<String, String> mapearFila(String[] cabecera, String[] fila) {
        Map<String, String> atributos = new HashMap<>();
        for (int i = 0; i < cabecera.length; i++) {
            // Si la fila es más corta que la cabecera, evitamos IndexOutOfBounds con vacio
            String valor = (i < fila.length) ? fila[i] : "";
            atributos.put(cabecera[i], valor);
        }
        return atributos;
    }
}