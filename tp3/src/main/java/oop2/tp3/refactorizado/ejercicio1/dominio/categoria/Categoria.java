package oop2.tp3.refactorizado.ejercicio1.dominio.categoria;

public interface Categoria {
    double calcularMontoCategoria(int diasAlquilados);
    int calcularPuntos(int diasAlquilados);
    String nombre();
}