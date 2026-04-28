package oop2.tp3.refactorizado.ejercicio5;

import oop2.tp3.refactorizado.ejercicio5.tipoEvento.Evento;

import java.util.List;

public class Calculador {

    public String reporte(Factura factura) {

        double totalAmount = 0;
        double creditos = 0;

        StringBuilder result = new StringBuilder();
        result.append("Facturación para ")
                .append(factura.nombreCliente())
                .append(System.lineSeparator());

        for (var actuacion : factura.actuaciones()) {

            var evento = actuacion.evento();

            double monto = evento.calcularMonto(actuacion.numeroEspectadores());
            double creditosAct = evento.calcularCreditos(actuacion.numeroEspectadores());

            result.append(evento.nombre())
                    .append(": ")
                    .append(monto)
                    .append(". Asientos: ")
                    .append(actuacion.numeroEspectadores())
                    .append(System.lineSeparator());

            totalAmount += monto;
            creditos += creditosAct;
        }

        result.append("Monto ganado: ").append(totalAmount).append(System.lineSeparator());
        result.append("Créditos ganados: ").append(creditos).append(System.lineSeparator());

        return result.toString();
    }
}
