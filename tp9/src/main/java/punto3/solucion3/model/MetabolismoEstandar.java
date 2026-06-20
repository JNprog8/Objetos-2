package punto3.solucion3.model;

public class MetabolismoEstandar implements Metabolismo {
    private static final int COSTO_FIJO_DESPEGUE = 10;
    private static final int COSTO_POR_KILOMETRO = 1;
    private static final int ENERGIA_POR_GRAMO = 5;

    @Override
    public int gastoVolar(int kilometros) {
        return COSTO_FIJO_DESPEGUE + (kilometros * COSTO_POR_KILOMETRO);
    }

    @Override
    public int beneficioComer(int gramos) {
        return gramos * ENERGIA_POR_GRAMO;
    }
}
