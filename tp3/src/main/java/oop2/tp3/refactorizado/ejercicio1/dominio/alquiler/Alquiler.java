package oop2.tp3.refactorizado.ejercicio1.dominio.alquiler;

import oop2.tp3.refactorizado.ejercicio1.dominio.producto.CopiaLibro;

public class Alquiler {
    private CopiaLibro copia;
    private int dias;

    public Alquiler(CopiaLibro copia, int dias) {
        this.copia = copia;
        this.dias  = dias;
    }

    public double calcularMonto() {
        return copia.calcularMonto(dias);
    }

    public int calcularPuntos() {
        return copia.calcularPuntos(dias);
    }

    public String nombreLibro() {
        return copia.nombreLibro();
    }
}