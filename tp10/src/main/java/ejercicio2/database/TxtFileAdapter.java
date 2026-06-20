package ejercicio2.database;

import ejercicio2.model.Concurso;
import ejercicio2.model.ConcursoRepository;
import ejercicio2.model.Inscripto;
import ejercicio2.model.InscriptosRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TxtFileAdapter implements ConcursoRepository, InscriptosRepository {
    private static final String SHORT_FORMAT = "yyyy/MM/dd";
    private String pathConcursos;
    private String pathInscriptos;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SHORT_FORMAT);

    public TxtFileAdapter(String concursosPath, String inscriptosPath) {
        this.pathConcursos = concursosPath;
        this.pathInscriptos = inscriptosPath;
    }

    @Override
    public List<Concurso> todosLosConcursos() {
        List<Concurso> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathConcursos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",\\s*");
                lista.add(new Concurso(Integer.parseInt(p[0]), p[1], LocalDate.parse(p[2], formatter), LocalDate.parse(p[3], formatter)));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer concursos", e);
        }
        return lista;
    }

    @Override
    public void guardarInscripcion(Inscripto in) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathInscriptos, true))) {
            String registro = String.format("%s, %s, %s, %s, %d", in.apellido(), in.nombre(), in.telefono(), in.email(), in.idConcurso());
            writer.write(registro);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar inscripto", e);
        }
    }

}
