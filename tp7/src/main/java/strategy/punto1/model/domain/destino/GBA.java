package strategy.punto1.model.domain.destino;

import strategy.punto1.model.strategy.Enviar;

public class GBA implements Destino {
    @Override
    public float calcularCostoCon(Enviar strategy, float pesoTotal) {
        return strategy.calcularCostoAGBA(pesoTotal);
    }

    @Override
    public String nombre() {
        return "Gran Buenos Aires";
    }
}
