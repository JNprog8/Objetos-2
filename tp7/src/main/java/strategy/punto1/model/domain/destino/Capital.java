package strategy.punto1.model.domain.destino;

import strategy.punto1.model.strategy.Enviar;

public class Capital implements Destino {
    @Override
    public float calcularCostoCon(Enviar strategy, float pesoTotal) {
        return strategy.calcularCostoACapital(pesoTotal);
    }

    @Override
    public String nombre() {
        return "Capital Federal";
    }
}
