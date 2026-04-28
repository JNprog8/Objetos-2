package oop2.tp3.refactorizado.ejercicio1.dominio.categoria;

public class CategoriaNuevoLanzamiento implements Categoria {
    private static final double PRECIO_POR_DIA = 3.0;
    private static final int DIAS_MINIMOS_PARA_BONUS = 1;
    private static final String NUEVO_LANZAMIENTO = "Nuevo lanzamiento";

    @Override
    public double calcularMontoCategoria(int dias) {
        return dias * PRECIO_POR_DIA;
    }

    @Override
    public int calcularPuntos(int dias) {
        return (dias > DIAS_MINIMOS_PARA_BONUS) ? 2 : 1;
    }

    @Override
    public String nombre() { return NUEVO_LANZAMIENTO; }
}