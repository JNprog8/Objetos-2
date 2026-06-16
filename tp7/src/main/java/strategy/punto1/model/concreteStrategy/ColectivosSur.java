package strategy.punto1.model.concreteStrategy;

import strategy.punto1.model.strategy.Enviar;

public class ColectivosSur implements Enviar {

    private float adicionalPorPeso(float pesoTotal) {
        if (pesoTotal > 5 && pesoTotal <= 30) {
            return 500;
        } else if (pesoTotal > 30) {
            return 2000;
        }
        return 0;
    }

    @Override
    public float calcularCostoACapital(float pesoTotal) {
        return 1000 + adicionalPorPeso(pesoTotal);
    }

    @Override
    public float calcularCostoAGBA(float pesoTotal) {
        return 1500 + adicionalPorPeso(pesoTotal);
    }

    @Override
    public float calcularCostoAlInterior(float pesoTotal, String ciudad) {
        return 3000 + adicionalPorPeso(pesoTotal);
    }
}
