package oop2.tp3.ejercicio5;

import java.util.List;

import oop2.tp3.refactorizado.ejercicio5.*;
import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Comedia;
import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Drama;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadorTest {

    @Test
    public void test01() {

        var escuela = new Comedia("Escuela de Rock");
        var hamlet = new Drama("Hamlet");
        var perfume = new Drama("El Perfume");

        var factura = new Factura("c1", List.of(
                new Actuacion(escuela, 158),
                new Actuacion(hamlet, 103),
                new Actuacion(perfume, 8)
        ));

        String esperado = """
                Facturación para c1
                Escuela de Rock: 156400.0. Asientos: 158
                Hamlet: 113000.0. Asientos: 103
                El Perfume: 40000.0. Asientos: 8
                Monto ganado: 309400.0
                Créditos ganados: 232.0
                """;

        assertEquals(esperado, new Calculador().reporte(factura));
    }
}
