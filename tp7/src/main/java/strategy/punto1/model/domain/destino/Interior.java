package strategy.punto1.model.domain.destino;

import strategy.punto1.model.strategy.Enviar;

public class Interior implements Destino {
    private final String ciudad;

    public Interior(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public float calcularCostoCon(Enviar strategy, float pesoTotal) {
        return strategy.calcularCostoAlInterior(pesoTotal, ciudad);
    }

    @Override
    public String nombre() {
        return ciudad;
    }
}
