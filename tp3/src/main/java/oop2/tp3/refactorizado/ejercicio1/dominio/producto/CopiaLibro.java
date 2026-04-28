package oop2.tp3.refactorizado.ejercicio1.dominio.producto;

public class CopiaLibro {
    private final Libro libro;
    private int stock;

    public CopiaLibro(Libro libro, int stock) {
        this.libro = libro;
        this.stock = stock;
    }

    public void reservar() {
        validarStock();
        stock--;
    }

    public void devolver() {
        stock++;
    }

    public double calcularMonto(int dias) {
        return libro.calcularMonto(dias);
    }

    public int calcularPuntos(int dias) {
        return libro.calcularPuntos(dias);
    }

    public String nombreLibro() {
        return libro.nombre();
    }

    private void validarStock() {
        if (stock <= 0) throw new IllegalStateException("Sin stock: " + libro.nombre());
    }
}