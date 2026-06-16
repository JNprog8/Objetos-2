package strategy.punto1.model.strategy;

public interface Enviar {
    float calcularCostoACapital(float pesoTotal);

    float calcularCostoAGBA(float pesoTotal);

    float calcularCostoAlInterior(float pesoTotal, String ciudad);
}
