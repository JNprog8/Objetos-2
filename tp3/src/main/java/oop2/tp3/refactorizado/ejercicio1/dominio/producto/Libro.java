package oop2.tp3.refactorizado.ejercicio1.dominio.producto;

import oop2.tp3.refactorizado.ejercicio1.dominio.categoria.Categoria;

public class Libro {
    private final String nombre;
    private final Categoria categoria;

    public Libro(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public double calcularMonto(int diasAlquilados) {
        return categoria.calcularMontoCategoria(diasAlquilados);
    }

    public int calcularPuntos(int diasAlquilados) {
        return categoria.calcularPuntos(diasAlquilados);
    }

    public String nombre() {
        return nombre;
    }

    public String nombreCategoria() {
        return categoria.nombre();
    }
}