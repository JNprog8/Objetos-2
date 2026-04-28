package oop2.tp3.refactorizado.ejercicio4;

import org.jdbi.v3.core.Jdbi;

public class Main {

    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:hsqldb:mem;create=true");

        new SetUpDatabase(jdbi).setUp();

        var repo = new PersonaRepository(jdbi);

        repo.buscarPorNombre("Vla").forEach(Main::imprimirPersona);

        repo.buscarId(1L).ifPresent(Main::imprimirPersona);
    }

    private static void imprimirPersona(Persona p) {
        System.out.println(p.nombre() + " " + p.apellido());
    }
}
