package oop2.tp3.refactorizado.ejercicio4;

import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaRepository {

    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_APELLIDO = "apellido";

    private final Jdbi jdbi;

    public PersonaRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    // Busca por nombre a parte
    public List<Persona> buscarPorNombre(String nombreOParte) {
        return jdbi.withHandle(handle ->
            handle
                .select("select nombre, apellido from persona where nombre like ?")
                .bind(0, "%" + nombreOParte + "%")
                .mapToMap(String.class)
                .list()
                .stream()
                .map(this::mapToPersona)
                .toList()
        );
    }

    // busca por id
    public Optional<Persona> buscarId(Long id) {
        return jdbi.withHandle(handle -> handle
                .select("select nombre, apellido from persona where id_persona = ?")
                .bind(0, id)
                .mapToMap(String.class)
                .findFirst()
                .map(this::mapToPersona));
    }

    private Persona mapToPersona(Map<String, String> row) {
        return new Persona(row.get(COLUMNA_NOMBRE), row.get(COLUMNA_APELLIDO));
    }
}
