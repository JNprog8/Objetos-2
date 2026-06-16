package strategy.punto1.model.infrastructure;

public interface ServicioDistancia {
    /**
     * Retorna la distancia en km desde
     * Capital Federal al destino especificado.
     */
    float distanciaDesdeCapital(String destinoNombre);
}
