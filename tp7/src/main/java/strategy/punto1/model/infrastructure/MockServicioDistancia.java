package strategy.punto1.model.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class MockServicioDistancia implements ServicioDistancia {
    private final Map<String, Float> distancias;

    public MockServicioDistancia() {
        this.distancias = new HashMap<>();
        this.distancias.put("La Plata", 60f);
        this.distancias.put("Rosario", 300f);
        this.distancias.put("Cordoba", 700f);
        this.distancias.put("Gran Buenos Aires", 30f);
    }

    @Override
    public float distanciaDesdeCapital(String destinoNombre) {
        return distancias.getOrDefault(destinoNombre, 1000f);
    }
}
