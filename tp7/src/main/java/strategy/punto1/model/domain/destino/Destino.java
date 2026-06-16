package strategy.punto1.model.domain.destino;

import strategy.punto1.model.strategy.Enviar;

public interface Destino {
    float calcularCostoCon(Enviar strategy, float pesoTotal);

    String nombre();
}
