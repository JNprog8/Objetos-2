package oop2.tp3.refactorizado.ejercicio5;

import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Comedia;
import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Drama;
import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Evento;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var eventos = cargarEventos();
        var factura = crearFactura(eventos);

        System.out.println(new Calculador().reporte(factura));
    }

    private static List<Evento> cargarEventos() {
        return List.of(
                new Comedia("Escuela de Rock"),
                new Drama("Hamlet"),
                new Drama("El Avion"),
                new Comedia("Cantando en la playa"),
                new Drama("El Perfume")
        );
    }

    private static Factura crearFactura(List<Evento> eventos) {

        var escuelaDeRock = buscarEvento("Escuela de Rock", eventos);
        var hamlet = buscarEvento("Hamlet", eventos);
        var perfume = buscarEvento("El Perfume", eventos);

        return new Factura("c1", List.of(
                new Actuacion(escuelaDeRock, 158),
                new Actuacion(hamlet, 103),
                new Actuacion(perfume, 8)
        ));
    }

    private static Evento buscarEvento(String nombre, List<Evento> eventos) {
        return eventos.stream()
                .filter(e -> e.nombre().equals(nombre))
                .findFirst()
                .orElseThrow();
    }
}