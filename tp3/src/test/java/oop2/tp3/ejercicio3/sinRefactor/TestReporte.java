package oop2.tp3.ejercicio3.sinRefactor;

import oop2.tp3.ejercicios.ejercicio3.despues.Gasto;
import oop2.tp3.ejercicios.ejercicio3.despues.ReporteDeGastos;
import oop2.tp3.ejercicios.ejercicio3.despues.TipoDeGasto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReporte {

    @Test
    void debeImprimirUnGastoSimple() {
        Gasto gasto = new Gasto();
        gasto.tipoGasto = TipoDeGasto.CENA;
        gasto.monto = 1000;

        ReporteDeGastos reporte = new ReporteDeGastos(List.of(gasto));

        String resultado = reporte.imprimir();

        assertTrue(resultado.contains("Cena\t1000"));
    }
}
