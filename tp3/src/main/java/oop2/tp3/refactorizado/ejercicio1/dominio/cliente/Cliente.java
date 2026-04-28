package oop2.tp3.refactorizado.ejercicio1.dominio.cliente;

import oop2.tp3.refactorizado.ejercicio1.dominio.alquiler.Alquiler;
import oop2.tp3.refactorizado.ejercicio1.dominio.producto.CopiaLibro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {
    private String nombre;
    private List<Alquiler> alquileres;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.alquileres = new ArrayList<>();
    }

    public void alquilar(CopiaLibro copia, int dias) {
        copia.reservar();
        alquileres.add(new Alquiler(copia, dias));
    }

    public double totalMonto() {
        return alquileres.stream().mapToDouble(Alquiler::calcularMonto).sum();
    }

    public int totalPuntos() {
        return alquileres.stream().mapToInt(Alquiler::calcularPuntos).sum();
    }

    public String nombre() {
        return nombre;
    }

    public List<Alquiler> alquileres() {
        return Collections.unmodifiableList(this.alquileres);
    }
}