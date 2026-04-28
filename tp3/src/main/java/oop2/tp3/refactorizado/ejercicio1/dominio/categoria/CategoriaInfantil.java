package oop2.tp3.refactorizado.ejercicio1.dominio.categoria;

public class CategoriaInfantil implements Categoria {
    private static final double MONTO_BASE = 1.5;
    private static final double CARGO_EXTRA_POR_DIA = 1.5;
    private static final int DIAS_INCLUIDOS = 3;
    private static final String INFANTIL = "Infantil";

    @Override
    public double calcularMontoCategoria(int dias) {
        return dias > DIAS_INCLUIDOS
                ? MONTO_BASE + (dias - DIAS_INCLUIDOS) * CARGO_EXTRA_POR_DIA
                : MONTO_BASE;
    }

    @Override
    public int calcularPuntos(int dias) {
        return 1;
    }

    @Override
    public String nombre() { return INFANTIL; }
}