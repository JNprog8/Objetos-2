package strategy.punto1.model.concreteStrategy;

import strategy.punto1.model.infrastructure.ServicioDistancia;
import strategy.punto1.model.strategy.Enviar;

public class CorreoArgentino implements Enviar {
    private final ServicioDistancia servicioDistancia;

    public CorreoArgentino(ServicioDistancia servicioDistancia) {
        this.servicioDistancia = servicioDistancia;
    }

    @Override
    public float calcularCostoACapital(float pesoTotal) {
        return 500;
    }

    @Override
    public float calcularCostoAGBA(float pesoTotal) {
        // LN: GBA se considera "Otro destino" para Correo Argentino segun enunciado
        return calcularCostoAlInterior(pesoTotal, "Gran Buenos Aires");
    }

    @Override
    public float calcularCostoAlInterior(float pesoTotal, String ciudad) {
        float distancia = servicioDistancia.distanciaDesdeCapital(ciudad);
        return 800 + (5 * distancia);
    }
}
