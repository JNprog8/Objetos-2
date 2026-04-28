package oop2.tp3.refactorizado.ejercicio3;

import oop2.tp3.refactorizado.ejercicio3.tipoGastos.Gasto;

import java.time.LocalDate;
import java.util.List;

public class ReporteDeGastos {
    private List<Gasto> gastos;

    public ReporteDeGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public String imprimir() {
        StringBuilder resultado = new StringBuilder();

        resultado.append("Expenses ").append(LocalDate.now()).append(System.lineSeparator());

        for (Gasto gasto : gastos) {
            String marcaExceso = gasto.tieneExceso() ? "X" : " ";

            resultado.append(gasto.nombre()).append("\t")
                    .append(gasto.monto()).append("\t")
                    .append(marcaExceso).append("\t")
                    .append(System.lineSeparator());
        }

        resultado.append("Gastos de comida: ").append(calcularGastosDeComida()).append(System.lineSeparator());
        resultado.append("Total de gastos: ").append(calcularTotal()).append(System.lineSeparator());

        return resultado.toString();
    }

    private int calcularGastosDeComida() {
        return gastos.stream().mapToInt(Gasto::montoComida).sum();
    }

    private int calcularTotal() {
        return gastos.stream().mapToInt(Gasto::monto).sum();
    }
}
