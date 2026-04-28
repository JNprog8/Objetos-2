package oop2.tp3.refactorizado.ejercicio1.dominio.alquiler;

import oop2.tp3.refactorizado.ejercicio1.dominio.cliente.Cliente;

public class Reporte {
    public String generarResumen(Cliente cliente) {
        StringBuilder resultado = new StringBuilder("Resumen de alquileres para " + cliente.nombre() + "\n");
        
        for (Alquiler alquiler : cliente.alquileres()) {
            resultado.append("\t")
                    .append(alquiler.nombreLibro())
                    .append("\t")
                    .append(alquiler.calcularMonto())
                    .append("\n");
        }
        
        resultado.append("Monto total: ")
                .append(cliente.totalMonto())
                .append("\n");
        resultado.append("Puntos obtenidos: ")
                .append(cliente.totalPuntos());
        
        return resultado.toString();
    }
}